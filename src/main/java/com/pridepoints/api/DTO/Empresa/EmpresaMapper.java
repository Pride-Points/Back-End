package com.pridepoints.api.DTO.Empresa;

import com.pridepoints.api.DTO.Avaliacao.AvaliacaoMapper;
import com.pridepoints.api.entities.Empresa;

import java.util.List;
import java.util.stream.Collectors;

public class EmpresaMapper {

    public static Empresa of(EmpresaCriacaoDTO empresaCriacaoDTO){
        Empresa empresa = new Empresa();

        empresa.setNomeFantasia(empresaCriacaoDTO.getNomeFantasia());
        empresa.setCnpj(empresaCriacaoDTO.getCnpj());
        empresa.setCep(empresaCriacaoDTO.getCep());
        empresa.setNumero(empresaCriacaoDTO.getNumero());
        empresa.setCidade(empresaCriacaoDTO.getCidade());
        empresa.setEstado(empresaCriacaoDTO.getEstado());

        return empresa;
    }

    public static EmpresaFullDTO of(Empresa empresa){
        EmpresaFullDTO empresaFullDTO = new EmpresaFullDTO();

        empresaFullDTO.setId(empresa.getId());
        empresaFullDTO.setNomeFantasia(empresa.getNomeFantasia());
        empresaFullDTO.setCnpj(empresa.getCnpj());
        empresaFullDTO.setCep(empresa.getCep());
        empresaFullDTO.setNumero(empresa.getNumero());
        empresaFullDTO.setCidade(empresa.getCidade());
        empresaFullDTO.setEstado(empresa.getCidade());
        empresaFullDTO.setDono(empresa.getFuncionarios().get(0).getNome());

        return empresaFullDTO;
    }

    public static List<EmpresaMinDTO> ofListMin(List<Empresa> empresas){

        List<EmpresaMinDTO> empresasMinDTO = empresas.stream()
                .map(EmpresaMinDTO::new)
                .collect(Collectors.toList());

        return empresasMinDTO;
    }

}
