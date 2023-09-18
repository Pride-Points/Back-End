package com.pridepoints.api.DTO.Usuario.Fisica;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pridepoints.api.DTO.Avaliacao.AvaliacaoDTO;
import com.pridepoints.api.DTO.Avaliacao.AvaliacaoMapper;
import com.pridepoints.api.entities.Fisica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FisicaFullDTO {

    private Long id;
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
    private LocalDate dtNascimento;
    private String email;
    private String genero;

    private String orientacaoSexual;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime ultimaTrocaSenha;

    private List<AvaliacaoDTO> avaliacoes;

    public FisicaFullDTO() {

    }
    public FisicaFullDTO(Fisica fisica) {
        this.id = fisica.getId();
        this.nome = fisica.getNome();
        this.dtNascimento = fisica.getDtNascimento();
        this.email = fisica.getEmail();
        this.genero = fisica.getEmail();
        this.genero = fisica.getGenero();
        this.orientacaoSexual = fisica.getOrientacaoSexual();
        this.ultimaTrocaSenha = fisica.getUltimaTrocaSenha();
        this.avaliacoes = AvaliacaoMapper.of(fisica.getAvaliacoesUsuario());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDtNascimento() {
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

    public LocalDateTime getUltimaTrocaSenha() {
        return ultimaTrocaSenha;
    }

    public List<AvaliacaoDTO> getAvaliacoes() {
        return avaliacoes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setOrientacaoSexual(String orientacaoSexual) {
        this.orientacaoSexual = orientacaoSexual;
    }

    public void setUltimaTrocaSenha(LocalDateTime ultimaTrocaSenha) {
        this.ultimaTrocaSenha = ultimaTrocaSenha;
    }

    public void setAvaliacoes(List<AvaliacaoDTO> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
