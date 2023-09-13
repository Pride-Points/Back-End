package com.pridepoints.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario extends Pessoa {

    private String cargo;
    private String cpf;
    @Column(columnDefinition = "BIT")
    private boolean isGerente;
    @Column(columnDefinition = "BIT")
    private boolean isAtivo;

    private String tipoFuncionario;


    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public  Funcionario(){
        this.isAtivo = true;
    }

    public Funcionario(String nome, String senha, String email, String cargo, String cpf, String tipoFuncionario, Empresa empresa) {
        super(nome, senha, email);
        this.cargo = cargo;
        this.cpf = cpf;
        this.isGerente = true;
        this.isAtivo = true;
        this.tipoFuncionario = tipoFuncionario;
        this.empresa = empresa;
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

    public boolean isGerente() {
        return this.isGerente;
    }

    public void setIsGerente(boolean gerente) {
        isGerente = gerente;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setIsAtivo(boolean ativo) {
        isAtivo = ativo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(String tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    @Override
    public void setEmaileSenha(String email, String senha) {
        this.setEmail(email);
        this.setSenha(senha);
    }
}
