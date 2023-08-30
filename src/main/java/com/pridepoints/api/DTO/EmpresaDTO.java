package com.pridepoints.api.DTO;

import com.pridepoints.api.entities.Empresa;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
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
        this.avaliacoes = new ArrayList<>(entity.getAvaliacoes().stream()
                .map(avaliacao -> mapper.map(avaliacao, AvaliacaoDTO.class))
                .collect(Collectors.toList()));
        this.eventos = new ArrayList<>(entity.getEventos().stream()
                .map(evento -> mapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList()));
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

    public List<AvaliacaoDTO> getAvaliacoes() {
        return avaliacoes;
    }

    public List<EventoDTO> getEventos() {
        return eventos;
    }
}
