package com.pridepoints.api.services;

import com.pridepoints.api.DTO.FisicaDTO;
import com.pridepoints.api.entities.Fisica;
import com.pridepoints.api.repositories.FisicaRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FisicaService {

    @Autowired
    private FisicaRepository fisicaRepository;
    private ModelMapper mapper = new ModelMapper();

    @Transactional
    public ResponseEntity<FisicaDTO> cadastrarUsuario(@RequestBody Fisica f){
        if(f == null ){
            return ResponseEntity.status(404).build();
        }
        Fisica result = fisicaRepository.save(f);
        return ResponseEntity.status(200).body(new FisicaDTO(result));
    }

    @Transactional
    public ResponseEntity<String> loginUsuario(@RequestBody String email, @RequestBody String senha){
        boolean result = fisicaRepository.existsByEmailAndSenha(email, senha);
        if(result){
            return ResponseEntity.status(200).body("Usuário Autenticado com Sucesso!");
        }
            return ResponseEntity.status(404).build();
    }




    @Transactional
    public ResponseEntity<List<FisicaDTO>> listarPessoasFisicas() {


        List<FisicaDTO> pessoasFisicasList = fisicaRepository.findAll().stream()
                .map(fisica -> mapper.map(fisica, FisicaDTO.class))
                .collect(Collectors.toList());
        
        if(pessoasFisicasList.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(pessoasFisicasList);
    }
}
