package com.pridepoints.api.DTO.Usuario.Funcionario;

import com.pridepoints.api.entities.Empresa;

public class FuncionarioCriacaoDTO {
    private String nome;
    private String email;
    private String senha;
    private String cargo;
    private String cpf;
    private String tipoFuncionario;
    private Boolean isGerente;

    private Empresa empresa;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(String tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Boolean getIsGerente() {
        return isGerente;
    }

    public void setGerente(Boolean gerente) {
        isGerente = gerente;
    }
}
