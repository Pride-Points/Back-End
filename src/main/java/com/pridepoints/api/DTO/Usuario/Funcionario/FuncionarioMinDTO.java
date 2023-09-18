package com.pridepoints.api.DTO.Usuario.Funcionario;

import com.pridepoints.api.entities.Funcionario;

public class FuncionarioMinDTO {
    private Long id;
    private String nome;
    private String cargo;

    public FuncionarioMinDTO() {
    }

    public FuncionarioMinDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.cargo = funcionario.getCargo();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
