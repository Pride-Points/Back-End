package com.pridepoints.api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_funcionario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_funcionario", discriminatorType = DiscriminatorType.STRING)
public abstract class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String senha;
    private String email;
    private String cargo;
    private String cpf;

    private boolean isGerente;

    private boolean isAtivo;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Funcionario(String nome, String senha, String email, String cargo, String cpf, boolean isGerente, boolean isAtivo) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.cargo = cargo;
        this.cpf = cpf;
        this.isGerente = isGerente;
        this.isAtivo = isAtivo;
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
        return isGerente;
    }

    public void setGerente(boolean gerente) {
        isGerente = gerente;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean ativo) {
        isAtivo = ativo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
