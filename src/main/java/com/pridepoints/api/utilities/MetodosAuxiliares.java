package com.pridepoints.api.utilities;

import com.pridepoints.api.entities.Fisica;
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


    public boolean verificaEmaileSenha(Fisica f){
        if(f.getEmail() == null || f.getSenha() == null
        || f.getEmail().isBlank() || f.getSenha().isBlank()){
            return false;
        }
        return true;
    }
}
