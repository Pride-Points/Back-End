package com.pridepoints.api.services;

import com.pridepoints.api.DTO.Usuario.Fisica.FisicaCriacaoDTO;
import com.pridepoints.api.DTO.Usuario.Fisica.FisicaFullDTO;
import com.pridepoints.api.DTO.Usuario.Fisica.FisicaMapper;
import com.pridepoints.api.DTO.Usuario.Fisica.FisicaMinDTO;
import com.pridepoints.api.entities.Fisica;
import com.pridepoints.api.repositories.FisicaRepository;
import com.pridepoints.api.utilities.interfaces.iValidarTrocaDeSenha;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FisicaService implements iValidarTrocaDeSenha {

    @Autowired
    private FisicaRepository fisicaRepository;

    @Transactional
    public FisicaFullDTO cadastrarUsuario(FisicaCriacaoDTO f){
        Fisica consultaBanco = fisicaRepository.findByEmail(f.getEmail());
        if(consultaBanco == null){
            final Fisica novoUsuario = FisicaMapper.of(f);

            Fisica result = fisicaRepository.save(novoUsuario);

            return FisicaMapper.of(result);
        }
            return null;
    }

    @Transactional
    public FisicaFullDTO loginUsuario(FisicaCriacaoDTO f){
        Fisica result = fisicaRepository.findByEmailAndSenha(f.getEmail(), f.getSenha());
        if(result != null){

            return FisicaMapper.of(result);
        }
            return null;
    }


    @Transactional
    public List<FisicaMinDTO> listarPessoasFisicas() {

        List<Fisica> pessoasFisicasList = fisicaRepository.findAll();

        return FisicaMapper.ofListMin(pessoasFisicasList);
    }

    @Transactional
    public FisicaFullDTO atualizarEmail(FisicaCriacaoDTO obj, Long id){
        Optional<Fisica> result = fisicaRepository.findById(id);

        if(result.isPresent()){
            Fisica usuarioBanco = result.get();
            usuarioBanco.setEmail(obj.getEmail());
            fisicaRepository.save(usuarioBanco);

            return FisicaMapper.of(usuarioBanco);
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

            if(pessoaFisica.getUltimaTrocaSenha().isBefore(quatroMesesAtras));
                pessoaFisica.setForcarTrocaDeSenha(true);
                    fisicaRepository.save(pessoaFisica);
        }
    }
}
