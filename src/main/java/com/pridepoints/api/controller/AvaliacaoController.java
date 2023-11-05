package com.pridepoints.api.controller;

import com.pridepoints.api.dto.Avaliacao.AvaliacaoCriacaoDTO;
import com.pridepoints.api.dto.Avaliacao.AvaliacaoDTO;
import com.pridepoints.api.services.AvaliacaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService){
        this.avaliacaoService = avaliacaoService;
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoDTO>> listarTodasAvaliacoes(){
        List<AvaliacaoDTO> listaDeAvaliacoes = avaliacaoService.listarTodasAvaliacoes();
        if(listaDeAvaliacoes.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaDeAvaliacoes);
    }

    @GetMapping("/nota/listar-menor/{idEmpresa}")
    public ResponseEntity<List<AvaliacaoDTO>> listarPorMenorNota(@PathVariable Long idEmpresa){

        List<AvaliacaoDTO> result = avaliacaoService.listarAvaliacoesPorMenorNota(idEmpresa);

        if(result == null || result.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/nota/listar-maior/{idEmpresa}")
    public ResponseEntity<List<AvaliacaoDTO>> listarPorMaiorNota(@PathVariable Long idEmpresa){

        List<AvaliacaoDTO> result = avaliacaoService.listarAvaliacoesPorMaiorNota(idEmpresa);

        if(result == null || result.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/empresa/{idEmpresa}")
    public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacoesDaEmpresa(@PathVariable Long idEmpresa){

        List<AvaliacaoDTO> result = avaliacaoService.listarAvaliacoesDaEmpresa(idEmpresa);

        if(result == null || result.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/usuario/{idUsuario}")
    @PreAuthorize("hasRole('ROLE_FISICA')")
    public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacoesDoUsuario(@PathVariable Long idUsuario){

        List<AvaliacaoDTO> result = avaliacaoService.listarAvaliacoesDoUsuario(idUsuario);

        if(result == null || result.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(result);
    }


    @PostMapping("/{empresaId}/{usuarioId}")
    @PreAuthorize("hasRole('ROLE_FISICA')")
    public ResponseEntity<AvaliacaoDTO> publicarAvaliacao(@Valid @RequestBody AvaliacaoCriacaoDTO f, @PathVariable Long empresaId, @PathVariable Long usuarioId){

        AvaliacaoDTO result = avaliacaoService.publicarAvaliacaoDaEmpresa(f,empresaId,usuarioId);
        if(result == null){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(201).body(result);

    }

    @PutMapping("/{idUsuario}/{idAvaliacao}/{idEmpresa}")
    @PreAuthorize("hasRole('ROLE_FISICA')")
    public ResponseEntity<AvaliacaoDTO> atualizarAvaliacaoDaEmpresa(@Valid @RequestBody AvaliacaoCriacaoDTO novaAvaliacao
            , @PathVariable Long idAvaliacao, @PathVariable Long idEmpresa, @PathVariable Long idUsuario){


            AvaliacaoDTO result = avaliacaoService.atualizarAvaliacaoDaEmpresa(novaAvaliacao,
                    idAvaliacao, idEmpresa, idUsuario);

            if(result ==  null){
                return ResponseEntity.status(204).build();
            }
                return ResponseEntity.status(200).body(result);

    }

    @DeleteMapping("/{idAvaliacao}")
    @PreAuthorize("hasRole('ROLE_FISICA')")
    public ResponseEntity<Void> deletarAvaliacaoDaEmpresa(@PathVariable Long idAvaliacao){

        boolean removeu = avaliacaoService.deletarAvaliacaoDaEmpresa(idAvaliacao);

        if(removeu){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).build();
    }


    
}
