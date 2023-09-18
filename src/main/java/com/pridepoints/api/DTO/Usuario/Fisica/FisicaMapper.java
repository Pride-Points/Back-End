package com.pridepoints.api.DTO.Usuario.Fisica;

import com.pridepoints.api.DTO.Avaliacao.AvaliacaoDTO;
import com.pridepoints.api.entities.Fisica;

import java.util.List;
import java.util.stream.Collectors;

public class FisicaMapper {

    public static Fisica of (FisicaCriacaoDTO fisicaCriacaoDTO){
        Fisica usuario = new Fisica();

        usuario.setNome(fisicaCriacaoDTO.getNome());
        usuario.setSenha(fisicaCriacaoDTO.getSenha());
        usuario.setEmail(fisicaCriacaoDTO.getEmail());
        usuario.setOrientacaoSexual(fisicaCriacaoDTO.getOrientacaoSexual());
        usuario.setGenero(fisicaCriacaoDTO.getGenero());
        usuario.setDtNascimento(fisicaCriacaoDTO.getDtNascimento());

        return usuario;
    }

    public static FisicaFullDTO of(Fisica fisica){
        FisicaFullDTO usuario = new FisicaFullDTO();

        usuario.setId(fisica.getId());
        usuario.setNome(fisica.getNome());
        usuario.setEmail(fisica.getEmail());
        usuario.setOrientacaoSexual(fisica.getOrientacaoSexual());
        usuario.setGenero(fisica.getGenero());
        usuario.setDtNascimento(fisica.getDtNascimento());
        usuario.setAvaliacoes(
                fisica.getAvaliacoesUsuario().stream()
                        .map(AvaliacaoDTO::new)
                        .collect(Collectors.toList())
        );

        return usuario;
    }

    public static FisicaMinDTO ofMin(Fisica fisica){
        FisicaMinDTO usuario = new FisicaMinDTO();

        usuario.setId(fisica.getId());
        usuario.setNome(fisica.getNome());
        usuario.setEmail(fisica.getEmail());

        return usuario;
    }

    public static List<FisicaMinDTO> ofListMin(List<Fisica> usuarios){

        List<FisicaMinDTO> usuariosDTO = usuarios.stream()
                .map(FisicaMinDTO::new)
                .collect(Collectors.toList());

        return usuariosDTO;
    }
    public static List<FisicaFullDTO> ofListFull(List<Fisica> usuarios){

        List<FisicaFullDTO> usuariosDTO = usuarios.stream()
                .map(FisicaFullDTO::new)
                .collect(Collectors.toList());

            return usuariosDTO;
    }
}
