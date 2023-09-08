package com.pridepoints.api.services;

import com.pridepoints.api.DTO.EmpresaDTO;
import com.pridepoints.api.DTO.FisicaDTO;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.repositories.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public EmpresaDTO cadastrarEmpresa(Empresa e){
        Empresa consultaBanco = empresaRepository.findByCnpj(e.getCnpj());

        if(consultaBanco == null){
            Empresa result = empresaRepository.save(e);

            return new EmpresaDTO(result);
        }
            return null;
    }


    @Transactional
    public List<EmpresaDTO> listarEmpresas() {
        List<EmpresaDTO> empresaList = empresaRepository.findAll().stream().map(EmpresaDTO::new).collect(Collectors.toList());

        return empresaList;
    }
}
