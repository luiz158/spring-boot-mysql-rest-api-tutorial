package com.controle.epi.controller;

import com.controle.epi.exception.ResourceNotFoundException;
import com.controle.epi.model.Funcionario;
import com.controle.epi.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FuncionarioController {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @GetMapping("/funcionarios")
    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    @PostMapping("/funcionarios")
    public Funcionario createFuncionario(@Valid @RequestBody Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @GetMapping("/funcionarios/{id}")
    public Funcionario getFuncionarioById(@PathVariable(value = "id") Long funcionarioId) {
        return funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario", "idFuncionario", funcionarioId));
    }

    @PutMapping("/funcionarios/{id}")
    public Funcionario updateFuncionario(@PathVariable(value = "id") Long funcionarioId,
                                           @Valid @RequestBody Funcionario funcionarioDetails) {

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario", "idFuncionario", funcionarioId));

//        funcionario.setTitle(noteDetails.getTitle());
//        funcionario.setContent(noteDetails.getContent());

        Funcionario updatedFuncionario = funcionarioRepository.save(funcionario);
        return updatedFuncionario;
    }

    @DeleteMapping("/funcionarios/{id}")
    public ResponseEntity<?> deleteFuncionario(@PathVariable(value = "id") Long funcionarioId) {
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario", "idFuncionario", funcionarioId));

        funcionarioRepository.delete(funcionario);

        return ResponseEntity.ok().build();
    }
}
