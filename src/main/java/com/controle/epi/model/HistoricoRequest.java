package com.controle.epi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class HistoricoRequest {
    private String status;
    private Date data;
    private Long funcionarioId;
    private Long setorId;

    public void setStatus(String status) {
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

    public String getStatus() {
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
