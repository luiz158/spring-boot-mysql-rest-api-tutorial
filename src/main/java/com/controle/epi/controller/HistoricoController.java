package com.controle.epi.controller;

import com.controle.epi.exception.ResourceNotFoundException;
import com.controle.epi.model.Epi;
import com.controle.epi.model.Funcionario;
import com.controle.epi.model.Historico;
import com.controle.epi.model.HistoricoEpi;
import com.controle.epi.model.HistoricoRequest;
import com.controle.epi.model.Setor;
import com.controle.epi.repository.EpiRepository;
import com.controle.epi.repository.FuncionarioRepository;
import com.controle.epi.repository.HistoricoEpiRepository;
import com.controle.epi.repository.HistoricoRepository;
import com.controle.epi.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HistoricoController {

    @Autowired
    HistoricoRepository historicoRepository;
    @Autowired
    HistoricoEpiRepository historicoEpiRepository;
    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Autowired
    SetorRepository setorRepository;
    @Autowired
    EpiRepository epiRepository;

    @GetMapping("/historico")
    public List<Historico> getAllHistorico() {
        return historicoRepository.findAll();
    }

    @PostMapping("/historico")
    public Historico createHistorico(@Valid @RequestBody HistoricoRequest historicoRequest) {
        Historico historico = new Historico();
        
        Setor setor = setorRepository.findById(historicoRequest.getSetorId())
                .orElseThrow(() -> new ResourceNotFoundException("Setor", "id", historicoRequest.getSetorId()));

        Funcionario funcionario = funcionarioRepository.findById(historicoRequest.getFuncionarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario", "id", historicoRequest.getFuncionarioId()));
        
        historico.setData(historicoRequest.getData());
        historico.setMessage(historicoRequest.getMessage());
        historico.setSetor(setor);
        historico.setFuncionario(funcionario);
        historico.setStatus(historicoRequest.getStatus());
        
        Historico result = historicoRepository.save(historico);
        
        for(Long epiId : historicoRequest.getEpis()){
            Epi epi = epiRepository.findById(epiId)
                .orElseThrow(() -> new ResourceNotFoundException("Epi", "id", epiId));
            
            HistoricoEpi historicoEpi = new HistoricoEpi();
            historicoEpi.setEpi(epi);
            historicoEpi.setIdHistorico(result.getIdHistorico());
            historicoEpiRepository.save(historicoEpi);
        }
        
        return result;
    }

    @GetMapping("/historico/{id}")
    public Historico getHistoricoById(@PathVariable(value = "id") Long historicoId) {
        return historicoRepository.findById(historicoId)
                .orElseThrow(() -> new ResourceNotFoundException("Historico", "id", historicoId));
    }
}
