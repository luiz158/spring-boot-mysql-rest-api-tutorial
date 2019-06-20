package com.controle.epi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@Entity
@Table(name = "historico")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorico;
    
    private String status;
    private Date data;
    
    @ManyToOne
    @JoinColumn(name = "funcionario")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "setor")
    private Setor setor;
    
    @OneToMany(mappedBy="idHistorico")
    private List<HistoricoEpi> historicoEpis;

    public List<HistoricoEpi> getHistoricoEpis() {
        return historicoEpis;
    }

    public void setHistoricoEpis(List<HistoricoEpi> historicoEpis) {
        this.historicoEpis = historicoEpis;
    }
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Long getIdHistorico() {
        return idHistorico;
    }

    public String getStatus() {
        return status;
    }

    public Date getData() {
        return data;
    }

    public void setIdHistorico(Long idHistorico) {
        this.idHistorico = idHistorico;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Setor getSetor() {
        return setor;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

}
