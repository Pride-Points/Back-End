package com.pridepoints.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "tb_evento")
public class Evento {
    private String nome;
    private String imgEvento;
    private String descricaoEvento;
    private Date dtEvento;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Evento(String nome, String imgEvento, String descricaoEvento, Date dtEvento) {
        this.nome = nome;
        this.imgEvento = imgEvento;
        this.descricaoEvento = descricaoEvento;
        this.dtEvento = dtEvento;
    }

    public Evento(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImgEvento() {
        return imgEvento;
    }

    public void setImgEvento(String imgEvento) {
        this.imgEvento = imgEvento;
    }

    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    public Date getDtEvento() {
        return dtEvento;
    }

    public void setDtEvento(Date dtEvento) {
        this.dtEvento = dtEvento;
    }
}
