package com.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estoque.model.EstoqueModel;

public interface EstoqueRepository extends JpaRepository<EstoqueModel, String>{
	EstoqueModel findById(long id);
}
