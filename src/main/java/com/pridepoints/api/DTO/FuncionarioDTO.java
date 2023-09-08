package com.pridepoints.api.DTO;

import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Funcionario;

public class FuncionarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String cargo;
    private Empresa empresa;
    private boolean isGerente;
    private boolean isAtivo;

    private String tipoFuncionario;

    public FuncionarioDTO(Funcionario entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.email = entity.getEmail();
        this.cargo = entity.getCargo();
        this.empresa = entity.getEmpresa();
        this.isGerente = entity.isGerente();
        this.isAtivo = entity.isAtivo();
        this.tipoFuncionario = entity.getTipoFuncionario();
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

    public String getTipoFuncionario() {
        return tipoFuncionario;
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
