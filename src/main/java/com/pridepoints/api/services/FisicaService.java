package com.pridepoints.api.services;

import com.pridepoints.api.DTO.FisicaDTO;
import com.pridepoints.api.entities.Fisica;
import com.pridepoints.api.repositories.FisicaRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FisicaService {

    @Autowired
    private FisicaRepository fisicaRepository;

    @Autowired
    private ModelMapper mapper = new ModelMapper();

    @Transactional
    public FisicaDTO cadastrarUsuario(Fisica f){
        Fisica consultaBanco = fisicaRepository.findByEmail(f.getEmail());
        if(consultaBanco == null){
            Fisica result = fisicaRepository.save(f);
            return new FisicaDTO(result);
        }
            return null;
    }

    @Transactional
    public FisicaDTO loginUsuario(Fisica f){
        Fisica result = fisicaRepository.findByEmailAndSenha(f.getEmail(), f.getSenha());
        if(result != null){
            return new FisicaDTO(result);
        }
            return null;
    }


    @Transactional
    public List<FisicaDTO> listarPessoasFisicas() {
        List<FisicaDTO> pessoasFisicasList = fisicaRepository.findAll().stream().map(FisicaDTO::new).collect(Collectors.toList());

        return pessoasFisicasList;
    }

    @Transactional
    public FisicaDTO atualizarEmaileSenha(Fisica novosDados, Long id){
        Optional<Fisica> result = fisicaRepository.findById(id);
        if(result.isPresent()){
            Fisica obj = result.get();

            obj.setEmail(novosDados.getEmail());
            obj.setSenha(novosDados.getSenha());

            fisicaRepository.save(obj);
            return new FisicaDTO(obj);
        }
            return null;
    }

    @Transactional
    public boolean removerPessoaFisica(Long id){
        if(fisicaRepository.existsById(id)){
            fisicaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
