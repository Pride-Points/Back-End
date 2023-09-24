package com.pridepoints.api.DTO.Usuario.Fisica;

import com.pridepoints.api.entities.Fisica;

public class FisicaMinDTO {
    private Long id;
    private String nome;
    private String email;

    public FisicaMinDTO() {
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

