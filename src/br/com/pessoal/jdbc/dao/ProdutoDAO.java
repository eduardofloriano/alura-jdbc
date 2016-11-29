package br.com.pessoal.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.pessoal.jdbc.db.Database;
import br.com.pessoal.jdbc.vo.ProdutoVO;

public class ProdutoDAO {

	public Connection getConnection() throws SQLException {
		return Database.getConnection();
	}

	public void listaProdutos() throws SQLException {

		Connection con = getConnection();
		Statement statement = con.createStatement();
		boolean resultado = statement.execute("select * from produto");
		ResultSet rs = statement.getResultSet();

		while (rs.next()) {
			System.out.println(rs.getInt("id"));
			System.out.println(rs.getString("nome"));
			System.out.println(rs.getString("descricao"));
		}

		closeResource(con, statement, rs);
		
	}//End listaProdutos

	public void inserirProduto(ProdutoVO produto) throws SQLException {

		Connection con = getConnection();

		String sql = "insert into produto (nome, descricao) values (?, ?)";
		PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, produto.getNome());
		statement.setString(2, produto.getDescricao());
		
		boolean resultado = statement.execute();
		ResultSet rs = statement.getGeneratedKeys();
		
		while(rs.next()){
			System.out.println("Id inserido: " + rs.getString("id"));
		}
		
		closeResource(con, statement, rs);
		
	}//End inserirProduto

	public void deletarProduto() throws SQLException{
		
		Connection con = getConnection();
		Statement statement = con.createStatement();
		boolean resultado = statement.execute("delete from produto where id > 3");
		int count = statement.getUpdateCount();
		
		System.out.println("Linhas removidas: " + count);
		
		closeResource(con, statement, null);
		
		
	}//End deletarProduto
	
	
	
	public void closeResource(Connection con, Statement st, ResultSet rs) throws SQLException {
		if (!(con == null)) {
			con.close();
		}
		if (!(st == null)) {
			st.close();
		}
		if (!(rs == null)) {
			rs.close();
		}
	}//End closeResource
}//End class
