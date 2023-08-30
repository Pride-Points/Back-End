package com.pridepoints.api.utilities;

public class ResultadoOperacao<T> {
    private boolean sucesso;
    private T objeto;

    public ResultadoOperacao(boolean sucesso, T objeto) {
        this.sucesso = sucesso;
        this.objeto = objeto;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public T getObjeto() {
        return objeto;
    }
}
