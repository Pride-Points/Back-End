package com.pridepoints.api.services;

import com.pridepoints.api.DTO.FisicaDTO;
import com.pridepoints.api.entities.Fisica;
import com.pridepoints.api.repositories.FisicaRepository;
import com.pridepoints.api.utilities.ResultadoOperacao;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FisicaService {

    @Autowired
    private FisicaRepository fisicaRepository;
    private ModelMapper mapper = new ModelMapper();

    @Transactional
    public ResponseEntity<FisicaDTO> cadastrarUsuario(Fisica f){
        if(f == null ){
            return ResponseEntity.status(404).build();
        }
        Fisica result = fisicaRepository.save(f);
        return ResponseEntity.status(200).body(new FisicaDTO(result));
    }

    @Transactional
    public ResponseEntity<String> loginUsuario(String email,String senha){
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

    @Transactional
    public ResponseEntity<FisicaDTO> atualizarPessoaFisica(FisicaDTO novosDados, Long id){

        ResultadoOperacao<Fisica> resultado = isExiste(id);
        if(resultado.isSucesso()){

            BeanUtils.copyProperties(novosDados, resultado.getObjeto());
            fisicaRepository.save(resultado.getObjeto());

            FisicaDTO pessoaAtualizada = new FisicaDTO(resultado.getObjeto());
          return ResponseEntity.status(200).body(pessoaAtualizada);
        }

        return ResponseEntity.status(404).build();
    }

    @Transactional
    public ResponseEntity<Void> removerPessoaFisica(Long id){
        ResultadoOperacao<Fisica> resultado = isExiste(id);
        if(resultado.isSucesso()){
            fisicaRepository.deleteById(resultado.getObjeto().getId());

            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }


    public ResultadoOperacao<Fisica> isExiste(Long id){

        Optional<Fisica> pessoaFisicaOptional = fisicaRepository.findById(id);

        if(pessoaFisicaOptional.isPresent()){

            Fisica pessoaFisicaExiste = pessoaFisicaOptional.get();

            return new ResultadoOperacao<>(true, pessoaFisicaExiste);
        }
            return new ResultadoOperacao<>(false, null);
    }
}
