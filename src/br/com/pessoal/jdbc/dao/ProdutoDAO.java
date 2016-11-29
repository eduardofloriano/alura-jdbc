package br.com.pessoal.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.pessoal.jdbc.db.Database;
import br.com.pessoal.jdbc.vo.ProdutoVO;

public class ProdutoDAO {

	public Connection getConnection() throws SQLException {
		return Database.getConnection();
	}

	public List<ProdutoVO> listaProdutos() throws SQLException {

		List<ProdutoVO> produtos = new ArrayList<ProdutoVO>();
		
		try(Connection con = getConnection()){
			
			try(Statement statement = con.createStatement()){				
				statement.execute("select * from produto");
				try(ResultSet rs = statement.getResultSet()){
					while (rs.next()) {
						
						ProdutoVO produto = new ProdutoVO();
						produto.setId(rs.getInt("id"));
						produto.setNome(rs.getString("nome"));
						produto.setDescricao(rs.getString("descricao"));
						
						produtos.add(produto);						
					}
					
				}				
			}
		}		
		
		return produtos;
		
	}//End listaProdutos

	public void inserirProduto(ProdutoVO produto) throws SQLException {

		try(Connection con = getConnection()){
			
			String sql = "insert into produto (nome, descricao) values (?, ?)";
			try(PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				statement.setString(1, produto.getNome());
				statement.setString(2, produto.getDescricao());
				
				boolean resultado = statement.execute();
				try(ResultSet rs = statement.getGeneratedKeys()){
					while(rs.next()){
						System.out.println("Id inserido: " + rs.getString("id"));
					}					
				}
				
				con.commit();
			}catch (Exception e){
				e.printStackTrace();
				con.rollback();	
			}
		}
		
	}//End inserirProduto

	public void deletarProduto() throws SQLException{
		
		try(Connection con = getConnection()){			
			try(Statement statement = con.createStatement()){
				boolean resultado = statement.execute("delete from produto where id > 3");
				int count = statement.getUpdateCount();				
				System.out.println("Linhas removidas: " + count);				
			}
		}
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
