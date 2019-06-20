package com.controle.epi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "epi_funcionario")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class EpiFuncionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEpiFuncionario;
    
    private Date validade;

    public void setIdEpiFuncionario(Long idEpiFuncionario) {
        this.idEpiFuncionario = idEpiFuncionario;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public void setEpi(Epi epi) {
        this.epi = epi;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getIdEpiFuncionario() {
        return idEpiFuncionario;
    }

    public Date getValidade() {
        return validade;
    }

    public Epi getEpi() {
        return epi;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    @ManyToOne
    @JoinColumn(name = "epi")
    private Epi epi;

    @ManyToOne
    @JoinColumn(name = "responsavel")
    private Funcionario responsavel;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
   
}
