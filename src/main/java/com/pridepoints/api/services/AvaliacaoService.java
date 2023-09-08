package com.pridepoints.api.services;

import com.pridepoints.api.DTO.AvaliacaoDTO;
import com.pridepoints.api.DTO.EmpresaDTO;
import com.pridepoints.api.entities.Avaliacao;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Fisica;
import com.pridepoints.api.repositories.AvaliacaoRepository;
import com.pridepoints.api.repositories.EmpresaRepository;
import com.pridepoints.api.repositories.FisicaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private FisicaRepository fisicaRepository;

    @Transactional
    public AvaliacaoDTO publicarAvaliacao(Avaliacao avaliacao, Long empresaId, Long usuarioId){

        Optional<Empresa> resultEmpresa = empresaRepository.findById(empresaId);
        Optional<Fisica> resultFisica = fisicaRepository.findById(usuarioId);

        if(resultFisica.isPresent() && resultEmpresa.isPresent()){
            avaliacao.setEmpresa(resultEmpresa.get());
            avaliacao.setPessoaFisica(resultFisica.get());

            Empresa empresa = resultEmpresa.get();
            empresa.adicionarAvaliacao(avaliacao);

            Fisica pessoaFisica = resultFisica.get();
            pessoaFisica.adicionarAvaliacao(avaliacao);

            empresaRepository.save(empresa);
            fisicaRepository.save(pessoaFisica);
            avaliacaoRepository.save(avaliacao);

            return new AvaliacaoDTO(avaliacao);
        }
            return null;
    }

    @Transactional
    public List<AvaliacaoDTO> listarAvaliacoes() {
        List<AvaliacaoDTO> avaliacaoList = avaliacaoRepository.findAll().stream().map(AvaliacaoDTO::new).collect(Collectors.toList());

        return avaliacaoList;
    }
}
