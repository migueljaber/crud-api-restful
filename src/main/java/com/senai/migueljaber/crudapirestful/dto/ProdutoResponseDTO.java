package com.senai.migueljaber.crudapirestful.dto;

import java.math.BigDecimal;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        BigDecimal preco,
        String descricao,
        Integer estoque,
        Long categoriaId,
        String categoriaNome
) {}
