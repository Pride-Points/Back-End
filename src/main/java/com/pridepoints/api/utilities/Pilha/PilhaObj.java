package com.pridepoints.api.utilities.Pilha;

public class PilhaObj<T> {

    private Object[] pilha;
    private int topo;

    public PilhaObj(int capacidade) {
        pilha = new Object[capacidade];
        this.topo = -1;
    }

    public boolean isEmpty() {
        return topo == -1;
    }

    public boolean isFull() {
        return topo == pilha.length - 1;
    }

    public void push(T elemento) {
        if (isFull()) {
            throw new IllegalStateException("Pilha cheia!");
        }
        topo++;
        pilha[topo] = elemento;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Pilha vazia!");
        }
        T elemento = (T) pilha[topo];
        topo--;
        return elemento;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Pilha vazia!");
        }
        return (T) pilha[topo];
    }

    public void exibe() {
        for (int i = 0; i <= topo; i++) {
            System.out.println(pilha[i]);
        }
    }
}
