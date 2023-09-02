package com.pridepoints.api.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("G")
public class Gerente extends Funcionario{
    public Gerente(String nome, String senha, String email, String cargo, String cpf, boolean isGerente, boolean isAtivo) {
        super(nome, senha, email, cargo, cpf, isGerente, isAtivo);
    }
}
