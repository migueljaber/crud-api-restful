package com.senai.migueljaber.crudapirestful.repository;

import com.senai.migueljaber.crudapirestful.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}