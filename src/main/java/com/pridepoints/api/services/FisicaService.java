package com.pridepoints.api.services;

import com.pridepoints.api.dto.Autenticacao.UsuarioTokenDTO;
import com.pridepoints.api.dto.Usuario.Fisica.FisicaCriacaoDTO;
import com.pridepoints.api.dto.Usuario.Fisica.FisicaFullDTO;
import com.pridepoints.api.dto.Usuario.Fisica.FisicaMapper;
import com.pridepoints.api.dto.Usuario.Fisica.FisicaMinDTO;
import com.pridepoints.api.entities.Fisica;
import com.pridepoints.api.repositories.FisicaRepository;
import com.pridepoints.api.utilities.interfaces.iValidarTrocaDeSenha;
import com.pridepoints.api.utilities.security.GerenciadorTokenJwt;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FisicaService implements iValidarTrocaDeSenha {

    @Autowired
    private FisicaRepository fisicaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public FisicaFullDTO cadastrarUsuario(FisicaCriacaoDTO f){
        boolean consultaBanco = fisicaRepository.existsByEmail(f.getEmail());
        if(!consultaBanco){
            final Fisica novoUsuario = FisicaMapper.of(f);

            String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
            novoUsuario.setSenha(senhaCriptografada);

            Fisica result = fisicaRepository.save(novoUsuario);

            return FisicaMapper.of(result);
        }
            return null;
    }

    @Transactional
    public UsuarioTokenDTO autenticarFisica(FisicaCriacaoDTO f){
        final UsernamePasswordAuthenticationToken credenciais =
                new UsernamePasswordAuthenticationToken(f.getEmail(),f.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credenciais);

        Optional<Fisica> fisicaAutenticada = fisicaRepository.findByEmail(f.getEmail());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);

        if(fisicaAutenticada.isPresent()){
            return FisicaMapper.of(fisicaAutenticada.get(),token);
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
