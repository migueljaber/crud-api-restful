package com.senai.migueljaber.crudapirestful.service;

import com.senai.migueljaber.crudapirestful.dto.CategoriaRequestDTO;
import com.senai.migueljaber.crudapirestful.dto.CategoriaResponseDTO;
import com.senai.migueljaber.crudapirestful.entity.Categoria;
import com.senai.migueljaber.crudapirestful.exception.DadosInvalidosException;
import com.senai.migueljaber.crudapirestful.exception.RegistroNaoEncontradoException;
import com.senai.migueljaber.crudapirestful.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repo;

    public CategoriaService(CategoriaRepository repo) {
        this.repo = repo;
    }

    public CategoriaResponseDTO criar(CategoriaRequestDTO dto) {
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new DadosInvalidosException("Campo 'nome' é obrigatório.");
        }

        Categoria c = new Categoria();
        c.setNome(dto.nome().trim());
        c.setDescricao(dto.descricao());

        Categoria salvo = repo.save(c);
        return new CategoriaResponseDTO(salvo.getId(), salvo.getNome(), salvo.getDescricao());
    }

    public List<CategoriaResponseDTO> listar() {
        return repo.findAll().stream()
                .map(c -> new CategoriaResponseDTO(c.getId(), c.getNome(), c.getDescricao()))
                .toList();
    }

    public CategoriaResponseDTO buscarPorId(Long id) {
        Categoria c = buscarEntidadePorId(id);
        return new CategoriaResponseDTO(c.getId(), c.getNome(), c.getDescricao());
    }

    public Categoria buscarEntidadePorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Categoria não encontrada: " + id));
    }

    public CategoriaResponseDTO editar(Long id, CategoriaRequestDTO dto) {
        Categoria c = buscarEntidadePorId(id);

        if (dto.nome() != null && !dto.nome().isBlank()) c.setNome(dto.nome().trim());
        if (dto.descricao() != null && !dto.descricao().isBlank()) c.setDescricao(dto.descricao().trim());

        Categoria salvo = repo.save(c);
        return new CategoriaResponseDTO(salvo.getId(), salvo.getNome(), salvo.getDescricao());
    }

    public void deletar(Long id) {
        Categoria c = buscarEntidadePorId(id);

        if (c.getProdutos() != null && !c.getProdutos().isEmpty()) {
            throw new DadosInvalidosException("Não é possível deletar uma categoria que possui produtos.");
        }

        repo.delete(c);
    }
}
