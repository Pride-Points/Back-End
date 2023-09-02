package com.pridepoints.api.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("FP")
public class FuncionarioPadrao extends Funcionario{

    public FuncionarioPadrao(String nome, String senha, String email, String cargo, String cpf, boolean isGerente, boolean isAtivo) {
        super(nome, senha, email, cargo, cpf, isGerente, isAtivo);
    }
}
