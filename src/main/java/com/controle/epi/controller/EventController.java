package com.controle.epi.controller;

import com.controle.epi.exception.ResourceNotFoundException;
import com.controle.epi.model.Epi;
import com.controle.epi.model.EpiSetor;
import com.controle.epi.model.EventResponse;
import com.controle.epi.model.Funcionario;
import com.controle.epi.model.Historico;
import com.controle.epi.model.HistoricoEpi;
import com.controle.epi.model.HistoricoRequest;
import com.controle.epi.model.Setor;
import com.controle.epi.repository.EpiRepository;
import com.controle.epi.repository.EpiSetorRepository;
import com.controle.epi.repository.FuncionarioRepository;
import com.controle.epi.repository.HistoricoEpiRepository;
import com.controle.epi.repository.HistoricoRepository;
import com.controle.epi.repository.SetorRepository;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Query;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EventController {

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
    @Autowired
    EpiSetorRepository epiSetorRepository;

    @PostMapping("/registerEvent")
    public EventResponse createEvent(@Valid @RequestBody HistoricoRequest historicoRequest) {
        EventResponse response = new EventResponse(true);
        
        List<EpiSetor> epiSetorList = epiSetorRepository.findBySetor(historicoRequest.getSetorId());
        List<Epi> episObrigatorios = epiSetorList.stream().map(x -> x.getEpi()).collect(Collectors.toList());
        
        List<Long> episLidos = historicoRequest.getEpis();
        
        for(Epi epi: episObrigatorios){
            episLidos.contains(1);
            if(!episLidos.contains(epi.getIdEpi())){
                response.setSuccess(false);
                response.concatMessage(epi.getNome());
            }
        }
        
        return response;//saveHistorico(historicoRequest);
    }
    
    private Historico saveHistorico(HistoricoRequest historicoRequest){
        Historico historico = new Historico();
        
        Setor setor = setorRepository.findById(historicoRequest.getSetorId())
                .orElseThrow(() -> new ResourceNotFoundException("Setor", "id", historicoRequest.getSetorId()));

        Funcionario funcionario = funcionarioRepository.findById(historicoRequest.getFuncionarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario", "id", historicoRequest.getFuncionarioId()));
        
        historico.setData(historicoRequest.getData());
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
}
