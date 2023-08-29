package com.pridepoints.api.DTO;

import com.pridepoints.api.entities.Avaliacao;
import com.pridepoints.api.entities.Fisica;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FisicaDTO {

    private Long id;
    private String nome;
    private Date dtNascimento;
    private String email;
    private String genero;

    private String orientacaoSexual;

    private List<Avaliacao> avaliacoes;

    public FisicaDTO(Fisica entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.dtNascimento = entity.getDtNascimento();
        this.email = entity.getEmail();
        this.genero = entity.getGenero();
        this.orientacaoSexual = entity.getOrientacaoSexual();
        this.avaliacoes = new ArrayList<>(entity.getAvaliacoesUsuario());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getGenero() {
        return genero;
    }

    public String getOrientacaoSexual() {
        return orientacaoSexual;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }
}
