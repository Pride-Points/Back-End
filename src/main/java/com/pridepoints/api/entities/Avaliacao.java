package com.pridepoints.api.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private double nota;
    private Date data;
    private String tag;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "fisica_id")
    private Fisica pessoaFisica;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}
