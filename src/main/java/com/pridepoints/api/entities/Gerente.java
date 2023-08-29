package com.pridepoints.api.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_gerente")
@DiscriminatorValue("GERENTE")
public class Gerente extends Funcionario{
    public Gerente(String nome, String senha, String email, String cargo, String cpf, boolean isGerente, boolean isAtivo) {
        super(nome, senha, email, cargo, cpf, isGerente, isAtivo);
    }
}
