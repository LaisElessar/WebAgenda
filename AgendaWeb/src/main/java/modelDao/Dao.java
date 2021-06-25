package modelDao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import modelBean.JavaBeans;
import modelConnection.Conexao;

public class Dao {
	
	//Conexao conexao = new Conexao();
	public void inserirContato(JavaBeans contato) {
	//criar uma variavel da sql
		String sql = "insert into contatos (nome,fone,email) values (?,?,?)";
		try {
			//abrir conexao com o banco
			Connection con = new Conexao().getConnection();
			//preparar a variavel
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			//executar o comando
			pst.execute();
			//fechar conexao com o banco
			pst.close();
			
		} catch (Exception e) {
		// TODO: handle exception
			System.out.println(e);
		}
	}
}
