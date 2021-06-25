package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import modelBean.JavaBeans;
import modelConnection.Conexao;
import modelDao.Dao;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert","/select","/update","/delete","/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	JavaBeans contato= new JavaBeans();
	Conexao dao = new Conexao();
	Dao contDao = new Dao();
	
	
	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		}else if(action.equals("/select")) {
			listarContato(request, response);
		}else if(action.equals("/update")) {
			editarContato(request, response);
		}else if(action.equals("/delete")) {
			removerContato(request, response);
		}else if(action.equals("/report")) {
			gerarRelatorio(request, response);
		}else {
			response.sendRedirect("index.html");
		}
	}

	// lista contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.sendRedirect("agenda.jsp");
		// criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		
		//teste de recebimento
		//for(int i=0; i<lista.size(); i++) {
			//System.out.println(lista.get(i).getIdcon());
			//System.out.println(lista.get(i).getNome());
			//System.out.println(lista.get(i).getFone());
			//System.out.println(lista.get(i).getEmail());
		//}
		
		//Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd= request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}
	
	//novo contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//teste de recebimento dos dados do formulário
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));
		
		//setar as variáveis 
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		//invocar o método inserirContato passando o objeto como parametro
		//dao.inserirContato(contato);
		contDao.inserirContato(contato);
		
		//redirecionar para agenda.jsp
		response.sendRedirect("main");
	}
	
	//listar contatos
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String idcon = request.getParameter("idcon");
		
		//Recebimento do id do contato que será editado
		Integer idcon = Integer.parseInt(request.getParameter("idcon"));
		//System.out.println(idcon);
		
		//Setar a variável JavaBeans
		contato.setIdcon(idcon);
		
		//Executar o método selecionarContato
		dao.selecionarContato(contato);
		
		//imprime no console os dados do id selecionado
		//System.out.println(contato.getIdcon());
		//System.out.println(contato.getNome());
		//System.out.println(contato.getFone());
		//System.out.println(contato.getEmail());
		
		//Seta os atributos do banco no formulário
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		
		//encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	//editar contato
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println(request.getParameter("idcon"));
		//System.out.println(request.getParameter("nome"));
		//System.out.println(request.getParameter("fone"));
		//System.out.println(request.getParameter("email"));
		
		//SETAR AS VARIÁVEIS
		//contato.setIdcon(request.getParameter("idcon"));
		contato.setIdcon(Integer.parseInt(request.getParameter("idcon")));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		dao.alterarContato(contato);
		
		response.sendRedirect("main");
	}
	
	//deletar
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idcon = request.getParameter("idcon");
		//System.out.println(idcon);
		contato.setIdcon(Integer.parseInt(idcon));
		dao.excluirContato(contato);
		
		//redireciona para agenda.jsp atualizando a lista
		response.sendRedirect("main");
	}
	
	
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			//tipo de conteudo
			response.setContentType("apllication/pdf");
			//nome do documento 
			response.addHeader("Content-Disposition", "inline; filename="+"contatos.pdf");
			//criar documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			//abrir documento para gerar conteudo
			documento.open();
			documento.add(new Paragraph("Lista de contatos:"));
			documento.add(new Paragraph(""));
			
			PdfPTable tabela = new PdfPTable(3);
			
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
			
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			//
			ArrayList<JavaBeans> lista = dao.listarContatos();
			for(int i =0; i<lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
			}
			documento.add(tabela);
			documento.close();
			} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			documento.close();
		}
	}
}
