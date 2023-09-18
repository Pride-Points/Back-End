package com.pridepoints.api.DTO.Avaliacao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AvaliacaoCriacaoDTO {

    private double nota;
    private String tag;
    private String comentario;

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
