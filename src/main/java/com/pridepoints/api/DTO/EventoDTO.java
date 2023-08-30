package com.pridepoints.api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Evento;

import java.util.Date;

public class EventoDTO {

    private Long id;
    private String nome;
    private String imgEvento;
    private String descricaoEvento;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dtEvento;

    private Empresa empresa;

    public EventoDTO(Evento entity) {
        this.nome = entity.getNome();
        this.imgEvento = entity.getImgEvento();
        this.descricaoEvento = entity.getDescricaoEvento();
        this.dtEvento = entity.getDtEvento();
        this.empresa = entity.getEmpresa();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getImgEvento() {
        return imgEvento;
    }

    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public Date getDtEvento() {
        return dtEvento;
    }

}
