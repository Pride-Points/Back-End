package com.pridepoints.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String imgEvento;

    @Column(columnDefinition = "TEXT")
    private String descricaoEvento;
    @JsonFormat(pattern = "dd/MM/yyyy")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
