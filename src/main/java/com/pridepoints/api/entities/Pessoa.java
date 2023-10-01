package com.pridepoints.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;



@MappedSuperclass
public abstract class Pessoa{
    private String nome;
    private String senha;
    private String email;

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime ultimaTrocaSenha;


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

    public LocalDateTime getUltimaTrocaSenha() {
        return ultimaTrocaSenha;
    }

    public void setUltimaTrocaSenha(LocalDateTime ultimaTrocaSenha) {
        this.ultimaTrocaSenha = ultimaTrocaSenha;
    }
}
