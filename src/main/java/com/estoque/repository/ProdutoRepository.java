package com.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estoque.model.EstoqueModel;
import com.estoque.model.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, String>{
	Iterable<ProdutoModel> findByEstoque(EstoqueModel estoque);
	ProdutoModel findByCodigo(long codigo);
}
