package com.pridepoints.api.utilities.pesquisaBinaria;

import com.pridepoints.api.entities.Funcionario;

import java.util.List;

public class PesquisaBinaria {
    public Funcionario binarySearch(List<Funcionario> funcionarios, String cpf) {
        int left = 0;
        int right = funcionarios.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Funcionario funcionario = funcionarios.get(mid);

            int comparacao = cpf.compareTo(funcionario.getCpf());

            if (comparacao == 0) {
                // Encontrou o funcionário com o CPF desejado.
                return funcionario;
            } else if (comparacao < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // Funcionário com o CPF não encontrado na lista.
        return null;
    }


}
