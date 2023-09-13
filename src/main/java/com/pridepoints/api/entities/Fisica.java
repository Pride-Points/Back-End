package com.pridepoints.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "tb_fisica")
public class Fisica extends Pessoa {

    private String orientacaoSexual;

    private String genero;

    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
    private Date dtNascimento;

    private boolean forcarTrocaDeSenha;

    @OneToMany(mappedBy = "pessoaFisica")
    private List<Avaliacao> avaliacoesUsuario;

    public Fisica() {
        this.forcarTrocaDeSenha = false;
        this.avaliacoesUsuario = new ArrayList<>();
    }

    public Fisica(String orientacaoSexual, String genero, Date dtNascimento) {
        this.orientacaoSexual = orientacaoSexual;
        this.genero = genero;
        this.dtNascimento = dtNascimento;
        this.avaliacoesUsuario = new ArrayList<>();
        this.forcarTrocaDeSenha = false;
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

    public boolean isForcarTrocaDeSenha() {
        return forcarTrocaDeSenha;
    }

    public void setForcarTrocaDeSenha(boolean forcarTrocaDeSenha) {
        this.forcarTrocaDeSenha = forcarTrocaDeSenha;
    }

    public List<Avaliacao> getAvaliacoesUsuario() {
        return avaliacoesUsuario;
    }

    public void adicionarAvaliacao(Avaliacao novaAvaliacao){ this.avaliacoesUsuario.add(novaAvaliacao); }

    @Override
    public void setEmaileSenha(String email, String senha) {
        this.setEmail(email);
        this.setSenha(senha);
    }
}
