package com.pridepoints.api.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "tb_metrica")
public class Metrica {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String tempo;

    public Metrica() {
        this.tempo = tempo;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

}
