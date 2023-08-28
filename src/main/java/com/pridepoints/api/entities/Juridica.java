package com.pridepoints.api.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_juridica")
public class Juridica extends Pessoa{

    private String cargo;
    private String cpf;

    @OneToOne(mappedBy = "pessoaJuridica")
    private Empresa empresa;


    public Juridica(String nome, String senha, Date dtNascimento, String email, String genero, String cargo, String cpf, Empresa empresa) {
        super(nome, senha, dtNascimento, email, genero);
        this.cargo = cargo;
        this.cpf = cpf;
        this.empresa = empresa;
    }

    public Juridica(){

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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Juridica juridica)) return false;
        return Objects.equals(getId(), juridica.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
