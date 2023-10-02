package com.pridepoints.api.services;

import com.pridepoints.api.dto.Autenticacao.PessoaFisicaDetalhesDTO;
import com.pridepoints.api.entities.Fisica;
import com.pridepoints.api.repositories.FisicaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private FisicaRepository fisicaRepository;
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Fisica> fisica = fisicaRepository.findByEmailOptional(username);
        if(fisica.isEmpty()){
            throw new UsernameNotFoundException(String.format("Usuario: %s não encontrado", username));
        }
        return new PessoaFisicaDetalhesDTO(fisica.get());

    }
}
