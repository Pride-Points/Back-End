package com.pridepoints.api.utilities.Pilha;

import com.pridepoints.api.entities.Avaliacao;

public class PilhaAvaliacao {

    private PilhaObj<Avaliacao> pilha;

    public PilhaAvaliacao(int capacidade) {
        pilha = new PilhaObj<>(capacidade);
    }

    public boolean isEmpty() {
        return pilha.isEmpty();
    }

    public boolean isFull() {
        return pilha.isFull();
    }

    public void push(Avaliacao avaliacao) {
        pilha.push(avaliacao);
    }

    public Avaliacao pop() {
        return pilha.pop();
    }

    public Avaliacao peek() {
        return pilha.peek();
    }

    public void exibe() {
        pilha.exibe();
    }


}
