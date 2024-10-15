package com.example.benchmark_ci_tools.controller;

import com.example.benchmark_ci_tools.model.Aluno;
import com.example.benchmark_ci_tools.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public Aluno criarAluno(@RequestBody Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    @GetMapping
    public List<Aluno> listarAlunos() {
        return alunoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAlunoPorID(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoService.buscarPorId(id);
        if (aluno.isPresent()) {
            return ResponseEntity.ok(aluno.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody Aluno alunoAtualizado) {
        Optional<Aluno> aluno = alunoService.buscarPorId(id);

        if (aluno.isPresent()) {
            Aluno alunoExistente = aluno.get();
            alunoExistente.setNome(alunoAtualizado.getNome());
            alunoExistente.setIdade(alunoAtualizado.getIdade());
            alunoExistente.setNota1(alunoAtualizado.getNota1());
            alunoExistente.setNota2(alunoAtualizado.getNota2());
            alunoService.salvar(alunoExistente);
            return ResponseEntity.ok(alunoExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        if (alunoService.buscarPorId(id).isPresent()) {
            alunoService.excluir(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
