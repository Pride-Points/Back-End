package com.pridepoints.api.DTO.Avaliacao;

import com.pridepoints.api.entities.Avaliacao;

import java.util.List;
import java.util.stream.Collectors;

public class AvaliacaoMapper {

    public static List<AvaliacaoDTO> of(List<Avaliacao> avaliacoes){

        List<AvaliacaoDTO> avaliacoesDTO;

        avaliacoesDTO = avaliacoes.stream()
                .map(AvaliacaoMapper::of)
                .collect(Collectors.toList());

        return avaliacoesDTO;
    }

    public static Avaliacao of(AvaliacaoCriacaoDTO avaliacaoCriacaoDTO){
        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setNota(avaliacaoCriacaoDTO.getNota());
        avaliacao.setTag(avaliacaoCriacaoDTO.getTag());
        avaliacao.setComentario(avaliacaoCriacaoDTO.getComentario());

        return avaliacao;
    }

    public static AvaliacaoDTO of(Avaliacao avaliacao){
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();

        avaliacaoDTO.setId(avaliacao.getId());
        avaliacaoDTO.setNota(avaliacao.getNota());
        avaliacaoDTO.setDtAvaliacao(avaliacao.getDtAvaliacao());
        avaliacaoDTO.setTag(avaliacao.getTag());
        avaliacaoDTO.setComentario(avaliacao.getComentario());

        return avaliacaoDTO;
    }

}
