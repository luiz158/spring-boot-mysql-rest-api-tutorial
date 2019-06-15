package com.controle.epi.controller;

import com.controle.epi.exception.ResourceNotFoundException;
import com.controle.epi.model.Epi;
import com.controle.epi.model.Historico;
import com.controle.epi.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class HistoricoController {

    @Autowired
    HistoricoRepository historicoRepository;

    @GetMapping("/historico")
    public List<Historico> getAllHistorico() {
        return historicoRepository.findAll();
    }

    @PostMapping("/historico")
    public Historico createHistorico(@Valid @RequestBody Historico historico) {
        return historicoRepository.save(historico);
    }

    @GetMapping("/historico/{id}")
    public Historico getHistoricoById(@PathVariable(value = "id") Long historicoId) {
        return historicoRepository.findById(historicoId)
                .orElseThrow(() -> new ResourceNotFoundException("Historico", "id", historicoId));
    }
}
