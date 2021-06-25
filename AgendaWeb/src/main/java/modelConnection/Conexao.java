package modelConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelBean.JavaBeans;

public class Conexao {

	public Connection getConnection() {
		try {

			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.out.println("banco conectado");
			return DriverManager.getConnection("jdbc:mysql://localhost/bdagenda", "root", "");

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome,fone,email) values (?,?,?)";
		try {
			// abrir conexao
			Connection con = getConnection();
			// preparar a query para execução no banco
			PreparedStatement pst = con.prepareStatement(create);

			// substituir os parametros(???) pelo conteúdo das variáveis
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// executar a query
			pst.executeUpdate();
			// encerrar a conexao com o banco
			con.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public ArrayList<JavaBeans> listarContatos() {
		// criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select *from contatos order by nome";
		try {
			Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do banco
				Integer idcon = rs.getInt(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}
	public void selecionarContato(JavaBeans contato) {
		String read2="select *from contatos where idcon=?";
		try {
			Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, contato.getIdcon());
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				//setar as variaveis JavaBeans
				contato.setIdcon(rs.getInt(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	public void alterarContato(JavaBeans contato) {
		String create="update contatos set nome=?, fone=?,email=? where idcon=?";
		try {
			Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setInt(4, contato.getIdcon());
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	public void excluirContato(JavaBeans contato) {
		String delete ="delete from contatos where idcon=?";
		try {
			Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, contato.getIdcon());
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
}

