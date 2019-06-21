package com.controle.epi.model;

import java.util.Date;
import java.util.List;

public class HistoricoRequest {
    private boolean status;
    private String message;
    private Date data;
    private Long funcionarioId;
    private Long setorId;
    private List<Long> epis;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setEpis(List<Long> epis) {
        this.epis = epis;
    }

    public List<Long> getEpis() {
        return epis;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setFuncionarioId(Long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public void setSetorId(Long setorId) {
        this.setorId = setorId;
    }

    public boolean getStatus() {
        return status;
    }

    public Date getData() {
        return data;
    }

    public Long getFuncionarioId() {
        return funcionarioId;
    }

    public Long getSetorId() {
        return setorId;
    }

}
