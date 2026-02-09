package com.senai.migueljaber.crudapirestful.repository;

import com.senai.migueljaber.crudapirestful.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {}