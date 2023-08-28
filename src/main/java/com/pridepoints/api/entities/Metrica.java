package com.pridepoints.api.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_metrica")
public class Metrica {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String tempo;

    public Metrica(){

    }
    public Metrica(String tempo) {
        this.tempo = tempo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Metrica metrica)) return false;
        return Objects.equals(id, metrica.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
