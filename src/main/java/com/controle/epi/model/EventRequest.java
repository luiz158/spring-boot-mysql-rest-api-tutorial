package com.controle.epi.model;

import java.util.Date;
import java.util.List;

public class EventRequest {
    private Long funcionarioId;
    private Long setorId;
    private List<Long> epis;

    public Long getFuncionarioId() {
        return funcionarioId;
    }

    public Long getSetorId() {
        return setorId;
    }

    public List<Long> getEpis() {
        return epis;
    }

    public void setFuncionarioId(Long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public void setSetorId(Long setorId) {
        this.setorId = setorId;
    }

    public void setEpis(List<Long> epis) {
        this.epis = epis;
    }
   
}
