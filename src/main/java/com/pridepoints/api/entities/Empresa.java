package com.pridepoints.api.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cnpj;
    private String email;

    @OneToOne
    @JoinColumn(name = "pessoaJuridica_id")
    private Juridica pessoaJuridica;

    @OneToMany(mappedBy = "empresa")
    private List<Avaliacao> Avaliacoes;

    public Empresa() {}

    public Empresa(String cnpj, String email) {
        this.cnpj = cnpj;
        this.email = email;
        this.Avaliacoes = new ArrayList<>();
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empresa empresa)) return false;
        return Objects.equals(id, empresa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
