package com.pridepoints.api.DTO.Empresa;

import com.pridepoints.api.entities.Empresa;

public class EmpresaMinDTO {
    private Long id;
    private String nomeFantasia;
    private String cidade;
    private String estado;
    private String dono;

    public EmpresaMinDTO(Empresa entity) {
        this.id = entity.getId();
        this.nomeFantasia = entity.getNomeFantasia();
        this.cidade = entity.getCidade();
        this.estado = entity.getEstado();
        this.dono = entity.getFuncionarios().get(0).getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getDono() {
        return dono;
    }
}
