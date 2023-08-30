package com.pridepoints.api.DTO;

import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.FuncionarioPadrao;
import com.pridepoints.api.entities.Gerente;

public class FuncionarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String cargo;
    private Empresa empresa;
    private boolean isGerente;
    private boolean isAtivo;

    public FuncionarioDTO(Gerente entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.email = entity.getEmail();
        this.cargo = entity.getCargo();
        this.empresa = entity.getEmpresa();
        this.isGerente = entity.isGerente();
        this.isAtivo = entity.isAtivo();
    }

    public FuncionarioDTO(FuncionarioPadrao entity){
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.email = entity.getEmail();
        this.cargo = entity.getCargo();
        this.empresa = entity.getEmpresa();
        this.isGerente = entity.isGerente();
        this.isAtivo = entity.isAtivo();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCargo() {
        return cargo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public boolean isGerente() {
        return isGerente;
    }

    public boolean isAtivo() {
        return isAtivo;
    }
}
