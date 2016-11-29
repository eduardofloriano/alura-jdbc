package br.com.pessoal.jdbc.main;

import java.sql.SQLException;
import java.util.List;

import br.com.pessoal.jdbc.dao.ProdutoDAO;
import br.com.pessoal.jdbc.vo.ProdutoVO;

public class Execute {

	private static ProdutoDAO dao = new ProdutoDAO();
	
	public static void main(String[] args) throws SQLException {
		
			listar();
	
	}
	
	
	private static void listar() throws SQLException{		
		List<ProdutoVO> produtos = dao.listaProdutos();
		
		for(ProdutoVO produto : produtos){
			System.out.println(produto.getId());
			System.out.println(produto.getNome());
			System.out.println(produto.getDescricao());
		}
	}
	
	private static void inserir(String nome, String descricao) throws SQLException{			
		dao.inserirProduto(new ProdutoVO(nome, descricao));
	}

}
