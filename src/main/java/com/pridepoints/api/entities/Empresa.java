package com.pridepoints.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tb_empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeFantasia;

    private String cnpj;

    private String cep;

    private int numero;

    private String cidade;
    private String estado;

    @OneToMany(mappedBy = "empresa")
    private List<Funcionario> funcionarios;

    @OneToMany(mappedBy = "empresa")
    private List<Avaliacao> avaliacoes;
    @OneToMany(mappedBy = "empresa")
    private List<Evento> eventos;



    public Empresa(){
        this.funcionarios = new ArrayList<>();
        this.avaliacoes = new ArrayList<>();
        this.eventos = new ArrayList<>();
    }


    public Empresa(String cnpj, String cep, int numero, String cidade, String estado, String nomeFantasia) {
        this.cnpj = cnpj;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.nomeFantasia = nomeFantasia;
        this.funcionarios = new ArrayList<>();
        this.avaliacoes = new ArrayList<>();
        this.eventos = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void adicionarFuncionario(Funcionario funcionario){
       this.funcionarios.add(funcionario);
    }

    public void adicionarAvaliacao(Avaliacao avaliacao){
        this.avaliacoes.add(avaliacao);
    }

    public void adicionarEvento(Evento evento){
        this.eventos.add(evento);
    }
}
