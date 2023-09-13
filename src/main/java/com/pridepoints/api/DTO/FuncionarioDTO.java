package com.pridepoints.api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Funcionario;

import java.time.LocalDateTime;

public class FuncionarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String cargo;
    private Empresa empresa;
    private boolean isGerente;
    private boolean isAtivo;

    private String tipoFuncionario;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime ultimaTrocaSenha;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Funcionario entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.email = entity.getEmail();
        this.cargo = entity.getCargo();
        this.empresa = entity.getEmpresa();
        this.isGerente = entity.isGerente();
        this.isAtivo = entity.isAtivo();
        this.tipoFuncionario = entity.getTipoFuncionario();
        this.ultimaTrocaSenha = entity.getUltimaTrocaSenha();
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

    public LocalDateTime getUltimaTrocaSenha() {
        return ultimaTrocaSenha;
    }

    public Boolean isGerente() {
        return isGerente;
    }

    public Boolean isAtivo() {
        return isAtivo;
    }


}
