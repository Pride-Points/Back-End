package com.pridepoints.api.services;

import com.pridepoints.api.DTO.Avaliacao.AvaliacaoCriacaoDTO;
import com.pridepoints.api.DTO.Avaliacao.AvaliacaoDTO;
import com.pridepoints.api.DTO.Avaliacao.AvaliacaoMapper;
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
    public AvaliacaoDTO publicarAvaliacao(AvaliacaoCriacaoDTO avaliacao, Long empresaId, Long usuarioId){

        Optional<Empresa> resultEmpresa = empresaRepository.findById(empresaId);
        Optional<Fisica> resultFisica = fisicaRepository.findById(usuarioId);

        if(resultFisica.isPresent() && resultEmpresa.isPresent()){

            Avaliacao novaAvaliacao = AvaliacaoMapper.of(avaliacao);

            novaAvaliacao.setEmpresa(resultEmpresa.get());
            novaAvaliacao.setPessoaFisica(resultFisica.get());

            Empresa empresa = resultEmpresa.get();
            empresa.adicionarAvaliacao(novaAvaliacao);

            Fisica pessoaFisica = resultFisica.get();
            pessoaFisica.adicionarAvaliacao(novaAvaliacao);

            empresaRepository.save(empresa);
            fisicaRepository.save(pessoaFisica);
            avaliacaoRepository.save(novaAvaliacao);

            return AvaliacaoMapper.of(novaAvaliacao);
        }
            return null;
    }

    @Transactional
    public List<AvaliacaoDTO> listarAvaliacoes() {
        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAll();

        return AvaliacaoMapper.of(avaliacaoList);
    }
}
