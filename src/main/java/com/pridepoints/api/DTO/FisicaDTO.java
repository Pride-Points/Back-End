package com.pridepoints.api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pridepoints.api.entities.Avaliacao;
import com.pridepoints.api.entities.Fisica;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FisicaDTO {

    private Long id;
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
    private Date dtNascimento;
    private String email;
    private String genero;

    private String orientacaoSexual;

    private List<AvaliacaoDTO> avaliacoes;

    public FisicaDTO(){

    }

    public FisicaDTO(Fisica entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.dtNascimento = entity.getDtNascimento();
        this.email = entity.getEmail();
        this.genero = entity.getGenero();
        this.orientacaoSexual = entity.getOrientacaoSexual();
        if (entity.getAvaliacoesUsuario() != null) {
            // Se o usuário possui avaliações, copie a lista existente.
            this.avaliacoes = new ArrayList<>(entity.getAvaliacoesUsuario().stream().map(AvaliacaoDTO::new).collect(Collectors.toList()));
        } else {
            // Se o usuário não possui avaliações, inicialize a lista como vazia.
            this.avaliacoes = Collections.emptyList();
        }
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

    public List<AvaliacaoDTO> getAvaliacoes() {
        return avaliacoes;
    }
}
