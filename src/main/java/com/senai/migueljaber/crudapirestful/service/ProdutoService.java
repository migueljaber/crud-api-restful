package com.senai.migueljaber.crudapirestful.service;

import com.senai.migueljaber.crudapirestful.dto.ProdutoRequestDTO;
import com.senai.migueljaber.crudapirestful.dto.ProdutoResponseDTO;
import com.senai.migueljaber.crudapirestful.entity.Categoria;
import com.senai.migueljaber.crudapirestful.entity.Produto;
import com.senai.migueljaber.crudapirestful.exception.DadosInvalidosException;
import com.senai.migueljaber.crudapirestful.exception.RegistroNaoEncontradoException;
import com.senai.migueljaber.crudapirestful.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repo;
    private final CategoriaService categoriaService;

    public ProdutoService(ProdutoRepository repo, CategoriaService categoriaService) {
        this.repo = repo;
        this.categoriaService = categoriaService;
    }

    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new DadosInvalidosException("Campo 'nome' é obrigatório.");
        }
        if (dto.preco() == null) {
            throw new DadosInvalidosException("Campo 'preco' é obrigatório.");
        }
        if (dto.categoriaId() == null) {
            throw new DadosInvalidosException("Campo 'categoriaId' é obrigatório.");
        }
        if (dto.estoque() == null) {
            throw new DadosInvalidosException("Campo 'estoque' é obrigatório.");
        }

        Categoria categoria = categoriaService.buscarEntidadePorId(dto.categoriaId());

        Produto p = new Produto();
        p.setNome(dto.nome().trim());
        p.setPreco(dto.preco());
        p.setDescricao(dto.descricao());
        p.setEstoque(dto.estoque());
        p.setCategoria(categoria);

        Produto salvo = repo.save(p);
        return toResponse(salvo);
    }

    public List<ProdutoResponseDTO> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto p = repo.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Produto não encontrado: " + id));
        return toResponse(p);
    }

    public ProdutoResponseDTO editar(Long id, ProdutoRequestDTO dto) {
        Produto p = repo.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Produto não encontrado: " + id));

        if (dto.nome() != null && !dto.nome().isBlank()) p.setNome(dto.nome().trim());
        if (dto.preco() != null) p.setPreco(dto.preco());
        if (dto.descricao() != null && !dto.descricao().isBlank()) p.setDescricao(dto.descricao().trim());
        if (dto.estoque() != null) p.setEstoque(dto.estoque());

        if (dto.categoriaId() != null) {
            Categoria categoria = categoriaService.buscarEntidadePorId(dto.categoriaId());
            p.setCategoria(categoria);
        }

        Produto salvo = repo.save(p);
        return toResponse(salvo);
    }

    public void deletar(Long id) {
        Produto p = repo.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Produto não encontrado: " + id));
        repo.delete(p);
    }

    private ProdutoResponseDTO toResponse(Produto p) {
        return new ProdutoResponseDTO(
                p.getId(),
                p.getNome(),
                p.getPreco(),
                p.getDescricao(),
                p.getEstoque(),
                p.getCategoria().getId(),
                p.getCategoria().getNome()
        );
    }
}