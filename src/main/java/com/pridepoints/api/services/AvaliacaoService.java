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

import java.util.Date;
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
    public AvaliacaoDTO publicarAvaliacaoDaEmpresa(AvaliacaoCriacaoDTO avaliacao,
                                                   Long empresaId,
                                                   Long usuarioId){

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
    public List<AvaliacaoDTO> listarTodasAvaliacoes() {
        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAll();

        return AvaliacaoMapper.of(avaliacaoList);
    }

    public List<AvaliacaoDTO> listarAvaliacoesDaEmpresa(Long id) {

        Optional<Empresa> result = empresaRepository.findById(id);

        if(result.isPresent()){

            Empresa empresaBanco = result.get();

            List<AvaliacaoDTO> avaliacoes = AvaliacaoMapper.of(empresaBanco.getAvaliacoes());

            return avaliacoes;
        }

        return null;
    }

    @Transactional
    public AvaliacaoDTO atualizarAvaliacaoDaEmpresa(AvaliacaoCriacaoDTO novaAvaliacao,
                                                    Long idAvaliacao,
                                                    Long idEmpresa,
                                                    Long idUsuario) {
        Optional<Empresa> empresaBanco = empresaRepository.findById(idEmpresa);
        Optional<Fisica> usuarioBanco = fisicaRepository.findById(idUsuario);

        if(empresaBanco.isPresent() && usuarioBanco.isPresent()){
            Empresa empresaEncontrada = empresaBanco.get();
            Fisica usuarioEncontrado = usuarioBanco.get();

            boolean existe = avaliacaoRepository.existsById(idAvaliacao);

            if(existe){
                Avaliacao avaliacaoConvertida = AvaliacaoMapper.of(novaAvaliacao);
            avaliacaoConvertida.setId(idAvaliacao);
            avaliacaoConvertida.setEmpresa(empresaEncontrada);
            avaliacaoConvertida.setPessoaFisica(usuarioEncontrado);
            avaliacaoConvertida.setDtAvaliacao(new Date());

            AvaliacaoDTO avaliacaoAtualizada = AvaliacaoMapper
                    .of(avaliacaoRepository
                            .save(avaliacaoConvertida));

            return avaliacaoAtualizada;

            }
        }

        return null;
    }



    @Transactional
    public boolean deletarAvaliacaoDaEmpresa(Long idAvaliacao) {

        Optional<Avaliacao> avaliacaoBanco = avaliacaoRepository.findById(idAvaliacao);

        if(avaliacaoBanco.isPresent()){

            Avaliacao avaliacaoEncontrada = avaliacaoBanco.get();

            avaliacaoRepository.delete(avaliacaoEncontrada);

            return true;
        }
        return false;
    }


    public List<AvaliacaoDTO> listarAvaliacoesDoUsuario(Long idUsuario) {
        Optional<Fisica> result = fisicaRepository.findById(idUsuario);

        if(result.isPresent()){

            Fisica fisicaBanco = result.get();

            List<AvaliacaoDTO> avaliacoes = AvaliacaoMapper.of(fisicaBanco.getAvaliacoesUsuario());

            return avaliacoes;
        }

        return null;
    }
}
