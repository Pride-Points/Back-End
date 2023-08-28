package com.pridepoints.api.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_fisica")
public class Fisica extends Pessoa{
    private String orientacaoSexual;

    @OneToMany(mappedBy = "pessoaFisica")
    private List<Avaliacao> avaliacoesUsuario;

    public Fisica(String nome, String senha, Date dtNascimento, String email, String genero, String orientacaoSexual) {
        super(nome, senha, dtNascimento, email, genero);
        this.orientacaoSexual = orientacaoSexual;
        this.avaliacoesUsuario = new ArrayList<>();
    }

    public Fisica() {

    }


    public String getOrientacaoSexual() {
        return orientacaoSexual;
    }

    public void setOrientacaoSexual(String orientacaoSexual) {
        this.orientacaoSexual = orientacaoSexual;
    }

    public List<Avaliacao> getAvaliacoesUsuario() {
        return avaliacoesUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fisica fisica)) return false;
        return Objects.equals(getId(), fisica.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
