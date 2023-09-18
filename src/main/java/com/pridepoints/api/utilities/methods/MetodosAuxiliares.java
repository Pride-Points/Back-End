package com.pridepoints.api.utilities.methods;

import com.pridepoints.api.DTO.Avaliacao.AvaliacaoCriacaoDTO;
import com.pridepoints.api.DTO.Usuario.Fisica.FisicaCriacaoDTO;
import com.pridepoints.api.DTO.Usuario.Funcionario.FuncionarioCriacaoDTO;
import com.pridepoints.api.entities.Avaliacao;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Funcionario;

public class MetodosAuxiliares {

    public boolean verificaObjetoFisica(FisicaCriacaoDTO f){
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

    public boolean verificaObjetoAvaliacao(AvaliacaoCriacaoDTO a){
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

    public boolean verificaObjetoFuncionario(FuncionarioCriacaoDTO f){
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


    public boolean verificaEmail(String novoEmail){
        if(novoEmail == null || novoEmail.isBlank()){
            return false;
        }
        return true;
    }

    public boolean verificaEmaileSenha(FisicaCriacaoDTO f) {
        if(f.getEmail() == null || f.getEmail().isBlank()
        || f.getSenha() == null || f.getSenha().isBlank()){
            return false;
        }
        return true;
    }
}
