package com.pridepoints.api.DTO.Usuario.Funcionario;

import com.pridepoints.api.entities.Funcionario;

import java.util.List;
import java.util.stream.Collectors;

public class FuncionarioMapper {

    public static Funcionario of(FuncionarioCriacaoDTO funcionarioCriacaoDTO){
        Funcionario funcionario = new Funcionario();

        funcionario.setNome(funcionarioCriacaoDTO.getNome());
        funcionario.setSenha(funcionarioCriacaoDTO.getSenha());
        funcionario.setEmail(funcionarioCriacaoDTO.getEmail());
        funcionario.setCargo(funcionarioCriacaoDTO.getCargo());
        funcionario.setCpf(funcionarioCriacaoDTO.getCpf());
        funcionario.setIsGerente(funcionarioCriacaoDTO.getIsGerente());
        funcionario.setTipoFuncionario(funcionarioCriacaoDTO.getTipoFuncionario());

        return funcionario;
    }

    public static FuncionarioFullDTO ofFull(Funcionario funcionario){
        FuncionarioFullDTO funcionarioFullDTO = new FuncionarioFullDTO();

        funcionarioFullDTO.setId(funcionario.getId());
        funcionarioFullDTO.setNome(funcionario.getNome());
        funcionarioFullDTO.setEmail(funcionario.getEmail());
        funcionarioFullDTO.setCargo(funcionario.getCargo());
        funcionarioFullDTO.setEmpresa(funcionario.getEmpresa().getNomeFantasia());
        funcionarioFullDTO.setTipoFuncionario(funcionario.getTipoFuncionario());
        funcionarioFullDTO.setUltimaTrocaSenha(funcionario.getUltimaTrocaSenha());
        funcionarioFullDTO.setGerente(funcionario.isGerente());
        funcionarioFullDTO.setAtivo(funcionario.isAtivo());


        return funcionarioFullDTO;
    }

    public static FuncionarioMinDTO ofMin(Funcionario funcionario){
        FuncionarioMinDTO funcionarioMinDTO = new FuncionarioMinDTO();

        funcionarioMinDTO.setId(funcionario.getId());
        funcionarioMinDTO.setNome(funcionario.getNome());
        funcionarioMinDTO.setCargo(funcionario.getCargo());

        return funcionarioMinDTO;
    }

    public static List<FuncionarioFullDTO> of(List<Funcionario> funcionariosList){
        List<FuncionarioFullDTO> funcionariosFullDTO;

        funcionariosFullDTO = funcionariosList.stream()
                .map(FuncionarioMapper::ofFull)
                .collect(Collectors.toList());

        return funcionariosFullDTO;
    }

}
