package com.pridepoints.api.services;

import com.pridepoints.api.DTO.EmpresaDTO;
import com.pridepoints.api.DTO.FuncionarioDTO;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Funcionario;
import com.pridepoints.api.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public FuncionarioDTO cadastrarFuncionario(Funcionario e){
        Funcionario consultaBanco = funcionarioRepository.findByEmail(e.getEmail());

        if(consultaBanco == null){
            Funcionario result = funcionarioRepository.save(e);
            return new FuncionarioDTO(result);
        }
        return null;
    }
}
