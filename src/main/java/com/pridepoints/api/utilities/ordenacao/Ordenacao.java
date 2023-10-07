package com.pridepoints.api.utilities.ordenacao;

import com.pridepoints.api.entities.Avaliacao;
import com.pridepoints.api.entities.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class Ordenacao {
    public List<Avaliacao> ordenaPorMaiorNota(List<Avaliacao> avaliacoes) {

        int n = avaliacoes.size();
        List<Avaliacao> ordenados = new ArrayList<>();

        Avaliacao vetor[] = new Avaliacao[n];

        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = avaliacoes.get(i);
        }

        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (vetor[j].getNota() > vetor[maxIndex].getNota()) {
                    maxIndex = j;
                }
            }

            // Trocar o elemento atual pelo elemento de maior nota encontrado
            Avaliacao temp = vetor[i];

            vetor[i] = vetor[maxIndex];

            vetor[maxIndex] = temp;


        }

        for (Avaliacao avaliacao : vetor) {
            ordenados.add(avaliacao);
        }

        return ordenados;
    }

    public List<Avaliacao> ordenaPorMenorNota(List<Avaliacao> avaliacoes) {

        int n = avaliacoes.size();
        List<Avaliacao> ordenados = new ArrayList<>();

        Avaliacao vetor[] = new Avaliacao[n];

        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = avaliacoes.get(i);
        }

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (vetor[j].getNota() < vetor[minIndex].getNota()) {
                    minIndex = j;
                }
            }

            // Trocar o elemento atual pelo elemento de menor nota encontrado
            Avaliacao temp = vetor[i];

            vetor[i] = vetor[minIndex];

            vetor[minIndex] = temp;

        }

        for (Avaliacao avaliacao : vetor) {
            ordenados.add(avaliacao);
        }

        return ordenados;
    }

    public List<Funcionario> ordenaAlfabeticamente(List<Funcionario> funcionarios) {
        int n = funcionarios.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (funcionarios.get(j).getNome().compareTo(funcionarios.get(minIndex).getNome()) < 0) {
                    minIndex = j;
                }
            }

            // Trocar o funcionário atual pelo funcionário de menor nome encontrado
            Funcionario temp = funcionarios.get(i);
            funcionarios.set(i, funcionarios.get(minIndex));
            funcionarios.set(minIndex, temp);
        }

        return funcionarios;
    }

}
