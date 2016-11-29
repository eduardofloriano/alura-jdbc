package br.com.pessoal.jdbc.main;

import java.sql.SQLException;

import br.com.pessoal.jdbc.dao.ProdutoDAO;
import br.com.pessoal.jdbc.vo.ProdutoVO;

public class Execute {

	private static ProdutoDAO dao = new ProdutoDAO();
	
	public static void main(String[] args) throws SQLException {
		
		inserir();
		listar();
		
	}
	
	
	private static void listar() throws SQLException{		
		dao.listaProdutos();
	}
	
	private static void inserir() throws SQLException{		
		dao.inserirProduto(new ProdutoVO("Notebook", "Notebook empresarial"));
	}

}
