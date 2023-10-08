package com.pridepoints.api.services;

import com.pridepoints.api.dto.Empresa.EmpresaCriacaoDTO;
import com.pridepoints.api.dto.Empresa.EmpresaFullDTO;
import com.pridepoints.api.dto.Empresa.EmpresaMapper;
import com.pridepoints.api.dto.Empresa.EmpresaMinDTO;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.repositories.EmpresaRepository;
import com.pridepoints.api.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    private final FuncionarioRepository funcionarioRepository;

    public EmpresaService(EmpresaRepository empresaRepository,
                          FuncionarioRepository funcionarioRepository){
        this.empresaRepository = empresaRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public EmpresaFullDTO cadastrarEmpresa(Empresa e){
        boolean existEmp = empresaRepository.existsByCnpj(e.getCnpj());
        boolean existsFunc = funcionarioRepository.existsByEmail(e.getFuncionarios().get(0).getEmail());

        if(!existEmp && !existsFunc){
            Empresa result = empresaRepository.save(e);

            return EmpresaMapper.of(result);
        }
        return null;
    }


    @Transactional
    public List<EmpresaMinDTO> listarEmpresas() {
        List<Empresa> empresaList = empresaRepository.findAll();
        return EmpresaMapper.ofListMin(empresaList);
    }

    public EmpresaFullDTO buscarPorId(Long id) {

        Optional<Empresa> result = empresaRepository.findById(id);

        return result.map(EmpresaMapper::of).orElse(null);
    }


    public EmpresaFullDTO atualizarEmpresa(EmpresaCriacaoDTO novosDados, Long idEmpresa) {
        Optional<Empresa> result = empresaRepository.findById(idEmpresa);

        if(result.isPresent()){
            Empresa empresaAtualizada = EmpresaMapper.of(novosDados);

            return EmpresaMapper.of(empresaRepository.save(empresaAtualizada));
        }
        return null;
    }

    public boolean deletarEmpresa(Long idEmpresa) {
        boolean exists = empresaRepository.existsById(idEmpresa);

        if(exists){
            empresaRepository.deleteById(idEmpresa);
            return true;
        }
        return false;
    }
}
