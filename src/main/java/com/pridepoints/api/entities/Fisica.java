package com.pridepoints.api.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "tb_fisica")
public class Fisica extends Pessoa{

    private String orientacaoSexual;

    private String genero;

    private Date dtNascimento;

    @OneToMany(mappedBy = "pessoaFisica")
    private List<Avaliacao> avaliacoesUsuario;

    public Fisica() {
    }

    public Fisica(String orientacaoSexual, String genero, Date dtNascimento) {
        this.orientacaoSexual = orientacaoSexual;
        this.genero = genero;
        this.dtNascimento = dtNascimento;
        this.avaliacoesUsuario = new ArrayList<>();
    }

    public String getOrientacaoSexual() {
        return orientacaoSexual;
    }

    public void setOrientacaoSexual(String orientacaoSexual) {
        this.orientacaoSexual = orientacaoSexual;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public List<Avaliacao> getAvaliacoesUsuario() {
        return avaliacoesUsuario;
    }


}
