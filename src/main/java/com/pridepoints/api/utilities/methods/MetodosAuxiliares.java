package com.pridepoints.api.utilities.methods;

import com.pridepoints.api.entities.Avaliacao;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Fisica;
import com.pridepoints.api.entities.Funcionario;
import org.springframework.http.ResponseEntity;

public class MetodosAuxiliares {

    public boolean verificaObjetoFisica(Fisica f){
        if(f == null || f.getDtNascimento() == null
        || f.getNome().isBlank()
        || f.getSenha().isBlank()
        || f.getEmail().isBlank()
        || f.getOrientacaoSexual().isBlank()
        || f.getGenero().isBlank()){
            return false;
        }
        return true;
    }

    public boolean verificaObjetoAvaliacao(Avaliacao a){
        if(a == null || a.getTag().isBlank()){
            return false;
        }
        return true;
    }

    public boolean verificaObjetoEmpresa(Empresa e){
     if(e == null || e.getNomeFantasia().isBlank()
     || e.getCnpj().isBlank()
     || e.getCep().isBlank()
     || e.getEstado().isBlank()
     || e.getCidade().isBlank()){
         return false;
     }
     return true;
    }

    public boolean verificaObjetoDonoEmpresa(Funcionario dono){
        if(dono == null || dono.getTipoFuncionario().isBlank()
        || dono.getSenha().isBlank()
        || dono.getNome().isBlank()){
            return false;
        }
        return true;
    }

    public boolean verificaObjetoFuncionario(Funcionario f){
        if(f == null
        || f.getNome().isBlank()
        || f.getSenha().isBlank()
        || f.getEmail().isBlank()
        || f.getCargo().isBlank()
        || f.getCpf().isBlank()
        || f.getTipoFuncionario().isBlank()){
            return false;
        }
        return true;
    }


    public boolean verificaEmaileSenha(Fisica f){
        if(f.getEmail() == null || f.getSenha() == null
        || f.getEmail().isBlank() || f.getSenha().isBlank()){
            return false;
        }
        return true;
    }
}
