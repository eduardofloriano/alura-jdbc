package br.com.pessoal.jdbc.main;

import java.sql.SQLException;
import java.util.List;

import br.com.pessoal.jdbc.dao.CategoriaDAO;
import br.com.pessoal.jdbc.dao.ProdutoDAO;
import br.com.pessoal.jdbc.vo.CategoriaVO;
import br.com.pessoal.jdbc.vo.ProdutoVO;

public class Execute {

	private static ProdutoDAO produtoDAO = new ProdutoDAO();
	private static CategoriaDAO categoriaDAO = new CategoriaDAO();
	
	public static void main(String[] args) throws SQLException {
		
		listarComProdutos();
	
	}
	
	
	private static void listar() throws SQLException{		
		List<ProdutoVO> produtos = produtoDAO.listaProdutos();
		
		for(ProdutoVO produto : produtos){
			System.out.println(produto.getId());
			System.out.println(produto.getNome());
			System.out.println(produto.getDescricao());
		}
	}
	
	private static void listarComProdutos() throws SQLException{
		
		for(CategoriaVO categoria: categoriaDAO.listarComProdutos()){
			
			for(ProdutoVO produto : categoria.getProdutos()){
				System.out.println("Categoria: " + categoria.getNome() + " - Produto: " + produto.getNome());
			}
			
			
		}
		
		
		
	}
	
	private static void inserir(String nome, String descricao) throws SQLException{			
		produtoDAO.inserirProduto(new ProdutoVO(nome, descricao));
	}

}
