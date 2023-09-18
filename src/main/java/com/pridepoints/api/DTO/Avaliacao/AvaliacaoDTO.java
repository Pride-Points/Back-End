package com.pridepoints.api.DTO.Avaliacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pridepoints.api.entities.Avaliacao;

import java.util.Date;

public class AvaliacaoDTO {

    private Long id;
    private double nota;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dtAvaliacao;
    private String tag;
    private String comentario;

    public AvaliacaoDTO(){}

    public AvaliacaoDTO(Avaliacao entity) {
        this.id = entity.getId();
        this.nota = entity.getNota();
        this.dtAvaliacao = entity.getDtAvaliacao();
        this.tag = entity.getTag();
        this.comentario = entity.getComentario();
    }

    public Long getId() {
        return id;
    }

    public double getNota() {
        return nota;
    }

    public Date getDtAvaliacao() {
        return dtAvaliacao;
    }

    public String getTag() {
        return tag;
    }

    public String getComentario() {
        return comentario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public void setDtAvaliacao(Date dtAvaliacao) {
        this.dtAvaliacao = dtAvaliacao;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
