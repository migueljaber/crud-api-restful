package com.senai.migueljaber.crudapirestful.dto;

import java.math.BigDecimal;

public record ProdutoRequestDTO(
        String nome,
        BigDecimal preco,
        String descricao,
        Integer estoque,
        Long categoriaId
) {}