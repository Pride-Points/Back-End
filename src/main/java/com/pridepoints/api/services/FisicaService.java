package com.pridepoints.api.services;

import com.pridepoints.api.DTO.FisicaDTO;
import com.pridepoints.api.entities.Fisica;
import com.pridepoints.api.entities.Funcionario;
import com.pridepoints.api.repositories.FisicaRepository;
import com.pridepoints.api.utilities.interfaces.iValidarTrocaDeSenha;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FisicaService implements iValidarTrocaDeSenha {

    @Autowired
    private FisicaRepository fisicaRepository;

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

            obj.setEmaileSenha(novosDados.getEmail(), novosDados.getSenha());

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

    @Override
    public void validatePasswordChange() {
        LocalDateTime quatroMesesAtras = LocalDateTime.now().minusMonths(4);
        List<Fisica> pessoasFisica = fisicaRepository.findAll();

        for (Fisica pessoaFisica: pessoasFisica){
            if(pessoaFisica.getUltimaTrocaSenha() == null || pessoaFisica.getUltimaTrocaSenha().isBefore(quatroMesesAtras));
                pessoaFisica.setForcarTrocaDeSenha(true);
                fisicaRepository.save(pessoaFisica);
        }
    }
}
