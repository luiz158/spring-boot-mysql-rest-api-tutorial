package com.controle.epi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "historico_epi")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class HistoricoEpi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistoricoEpi;
    
    @ManyToOne
    @JoinColumn(name = "epi")
    private Epi epi;
    
    private Long idHistorico;

    public void setIdHistoricoEpi(Long idHistoricoEpi) {
        this.idHistoricoEpi = idHistoricoEpi;
    }

    public void setEpi(Epi epi) {
        this.epi = epi;
    }

    public void setIdHistorico(Long idHistorico) {
        this.idHistorico = idHistorico;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getIdHistoricoEpi() {
        return idHistoricoEpi;
    }

    public Epi getEpi() {
        return epi;
    }

    public Long getIdHistorico() {
        return idHistorico;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

}
