package com.senai.migueljaber.crudapirestful.controller;

import com.senai.migueljaber.crudapirestful.dto.CategoriaRequestDTO;
import com.senai.migueljaber.crudapirestful.dto.CategoriaResponseDTO;
import com.senai.migueljaber.crudapirestful.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @Operation(summary = "Criar categoria", description = "Cria uma categoria para relacionar com produtos.")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criar(@RequestBody CategoriaRequestDTO dto) {
        CategoriaResponseDTO criado = service.criar(dto);
        return ResponseEntity.created(URI.create("/api/categorias/" + criado.id())).body(criado);
    }

    @Operation(summary = "Listar categorias", description = "Retorna todas as categorias cadastradas.")
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Buscar categoria por ID", description = "Retorna uma categoria pelo ID. Se não existir, retorna 404 via exceção personalizada.")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Editar categoria", description = "Atualiza apenas os campos preenchidos; mantém valores atuais se vier null/vazio.")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> editar(@PathVariable Long id, @RequestBody CategoriaRequestDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @Operation(summary = "Deletar categoria", description = "Remove uma categoria pelo ID. Se ela tiver produtos vinculados, retorna 400.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}