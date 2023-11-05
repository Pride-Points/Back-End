package com.pridepoints.api.utilities.Pilha;

import com.pridepoints.api.entities.Avaliacao;

public class TestePilha {
    public static void main(String[] args) {
        PilhaObj<Avaliacao> pilhaAvaliacoes = new PilhaObj<>(10);

        // Crie uma avaliação de exemplo
        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setNota(4.5);
        avaliacao1.setTag("Serviço");
        avaliacao1.setComentario("Bom atendimento!");
        // Defina outros campos da avaliação, se necessário.

        System.out.println("Empilhando avaliação: ");
        // Empilhe a avaliação na pilha
        pilhaAvaliacoes.push(avaliacao1);

        System.out.println("Exibindo conteudo: ");
        // Exiba o conteúdo da pilha
        pilhaAvaliacoes.exibe();

        // Desempilhe a avaliação
        Avaliacao avaliacaoDesempilhada = pilhaAvaliacoes.pop();

        // Exiba a avaliação desempilhada
        System.out.println("Avaliação desempilhada:");
        System.out.println("Nota: " + avaliacaoDesempilhada.getNota());
        System.out.println("Tag: " + avaliacaoDesempilhada.getTag());
        System.out.println("Comentário: " + avaliacaoDesempilhada.getComentario());

        // Exiba o conteúdo da pilha após a desempilhagem
        pilhaAvaliacoes.exibe();
    }
    }


