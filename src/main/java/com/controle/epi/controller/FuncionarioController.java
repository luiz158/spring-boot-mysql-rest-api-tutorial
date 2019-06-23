package com.controle.epi.controller;

import com.controle.epi.exception.ResourceNotFoundException;
import com.controle.epi.model.Funcionario;
import com.controle.epi.model.LoginRequest;
import com.controle.epi.model.LoginResponse;
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

        funcionario.setNome(funcionarioDetails.getNome());
        funcionario.setRua(funcionarioDetails.getRua());
        funcionario.setNumero(funcionarioDetails.getNumero());
        funcionario.setCidade(funcionarioDetails.getCidade());
        funcionario.setEstado(funcionarioDetails.getEstado());
        funcionario.setPais(funcionarioDetails.getPais());
        funcionario.setCep(funcionarioDetails.getCep());
        funcionario.setCpf(funcionarioDetails.getCpf());
        funcionario.setEmail(funcionarioDetails.getEmail());
        funcionario.setTelefone(funcionarioDetails.getTelefone());
        funcionario.setCargo(funcionarioDetails.getCargo());
        funcionario.setDataNascimento(funcionarioDetails.getDataNascimento());
        funcionario.setAdministrador(funcionarioDetails.isAdministrador());

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
    
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        List<Funcionario> listFuncionario = funcionarioRepository.login(loginRequest.getEmail(), loginRequest.getPassword());
        
        LoginResponse response = new LoginResponse();
        if(listFuncionario.size() > 0){
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
            response.setMessage("Email ou senha incorretos, ou você não tem permissão para efetuar o login");
        }
        return response;
    }
}
