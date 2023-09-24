package com.pridepoints.api.services;

import com.pridepoints.api.DTO.Avaliacao.AvaliacaoDTO;
import com.pridepoints.api.DTO.Avaliacao.AvaliacaoMapper;
import com.pridepoints.api.DTO.Empresa.EmpresaCriacaoDTO;
import com.pridepoints.api.DTO.Empresa.EmpresaFullDTO;
import com.pridepoints.api.DTO.Empresa.EmpresaMapper;
import com.pridepoints.api.DTO.Empresa.EmpresaMinDTO;
import com.pridepoints.api.DTO.Evento.EventoDTO;
import com.pridepoints.api.DTO.Evento.EventoMapper;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.repositories.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;


    @Transactional
    public EmpresaFullDTO cadastrarEmpresa(Empresa e){
        Empresa consultaBanco = empresaRepository.findByCnpj(e.getCnpj());

        if(consultaBanco == null){
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

        if(result.isPresent()){
            return EmpresaMapper.of(result.get());
        } else {

            return null;
        }
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
