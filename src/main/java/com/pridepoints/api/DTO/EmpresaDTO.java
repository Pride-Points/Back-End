package com.pridepoints.api.DTO;

import com.pridepoints.api.entities.Empresa;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EmpresaDTO {
    private ModelMapper mapper = new ModelMapper();
    private Long id;
    private String nomeFantasia;
    private String cnpj;
    private String cep;
    private int numero;
    private String cidade;
    private String estado;
    private String Dono;

    private List<AvaliacaoDTO> avaliacoes;

    private List<EventoDTO> eventos;

    public EmpresaDTO(Empresa entity) {
        this.id = entity.getId();
        this.nomeFantasia = entity.getNomeFantasia();
        this.cnpj = entity.getCnpj();
        this.cep = entity.getCep();
        this.numero = entity.getNumero();
        this.cidade = entity.getCidade();
        this.estado = entity.getEstado();
        if (entity.getAvaliacoes() != null) {
            // Se o usuário possui avaliações, copie a lista existente.
            this.avaliacoes = new ArrayList<>(entity.getAvaliacoes().stream().map(AvaliacaoDTO::new).collect(Collectors.toList()));
        } else {
            // Se o usuário não possui avaliações, inicialize a lista como vazia.
            this.avaliacoes = Collections.emptyList();
        }
        if (entity.getEventos() != null) {
            // Se o usuário possui avaliações, copie a lista existente.
            this.eventos = new ArrayList<>(entity.getEventos().stream().map(EventoDTO::new).collect(Collectors.toList()));
        } else {
            // Se o usuário não possui avaliações, inicialize a lista como vazia.
            this.eventos = Collections.emptyList();
        }
        if(entity.getFuncionarios() != null){
            this.Dono = entity.getFuncionarios().get(0).getNome();
        } else {
            this.Dono = "NÃO SEI ACHAR O DONO";
        }
    }

    public Long getId() {
        return id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getCep() {
        return cep;
    }

    public int getNumero() {
        return numero;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getDono(){ return Dono; }

    public List<AvaliacaoDTO> getAvaliacoes() {
        return avaliacoes;
    }

    public List<EventoDTO> getEventos() {
        return eventos;
    }
}
