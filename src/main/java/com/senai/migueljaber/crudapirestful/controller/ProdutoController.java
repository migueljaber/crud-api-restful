package com.senai.migueljaber.crudapirestful.controller;

import com.senai.migueljaber.crudapirestful.dto.ProdutoRequestDTO;
import com.senai.migueljaber.crudapirestful.dto.ProdutoResponseDTO;
import com.senai.migueljaber.crudapirestful.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @Operation(summary = "Criar produto", description = "Cria um produto validando campos obrigatórios e relacionando a uma categoria.")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(@RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO criado = service.criar(dto);
        return ResponseEntity.created(URI.create("/api/produtos/" + criado.id())).body(criado);
    }

    @Operation(summary = "Listar produtos", description = "Retorna todos os produtos cadastrados.")
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Buscar produto por ID", description = "Retorna um produto pelo ID. Se não existir, retorna 404 via exceção personalizada.")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Editar produto", description = "Atualiza apenas campos preenchidos; mantém valores atuais se vier null/vazio.")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> editar(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @Operation(summary = "Deletar produto", description = "Remove um produto pelo ID. Se não existir, retorna 404.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}