package com.controle.epi.model;

public class SetorRequest {
    private Long idSetor;
    private String descricao;
    private Long responsavelId;

    public Long getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Long idSetor) {
        this.idSetor = idSetor;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setResponsavelId(Long responsavelId) {
        this.responsavelId = responsavelId;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getResponsavelId() {
        return responsavelId;
    }

}
