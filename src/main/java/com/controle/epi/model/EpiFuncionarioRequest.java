package com.controle.epi.model;

import java.util.Date;

public class EpiFuncionarioRequest {
    private Long epiId;
    private Long funcionarioId;
    private Date validade;

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Date getValidade() {
        return validade;
    }

    public Long getEpiId() {
        return epiId;
    }

    public void setEpiId(Long epiId) {
        this.epiId = epiId;
    }

    public void setFuncionarioId(Long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public Long getFuncionarioId() {
        return funcionarioId;
    }
   
}
