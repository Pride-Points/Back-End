package com.pridepoints.api.DTO.Usuario.Fisica;

import com.pridepoints.api.entities.Fisica;

public class FisicaMinDTO {
    private Long id;
    private String nome;
    private String email;

    public FisicaMinDTO() {
    }

    // Construtor com os atributos mínimos
    public FisicaMinDTO(Fisica fisica) {
        this.id = fisica.getId();
        this.nome = fisica.getNome();
        this.email = fisica.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

