package com.pridepoints.api.Controller;

import com.pridepoints.api.DTO.Avaliacao.AvaliacaoCriacaoDTO;
import com.pridepoints.api.DTO.Avaliacao.AvaliacaoDTO;
import com.pridepoints.api.services.AvaliacaoService;
import com.pridepoints.api.services.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private AvaliacaoService avaliacaoService;

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
    public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacoesDoUsuario(@PathVariable Long idUsuario){

        List<AvaliacaoDTO> result = avaliacaoService.listarAvaliacoesDoUsuario(idUsuario);

        if(result == null || result.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(result);
    }



    @PostMapping("/{empresaId}/{usuarioId}")
    public ResponseEntity<AvaliacaoDTO> publicarAvaliacao(@Valid @RequestBody AvaliacaoCriacaoDTO f, @PathVariable Long empresaId, @PathVariable Long usuarioId){

        AvaliacaoDTO result = avaliacaoService.publicarAvaliacaoDaEmpresa(f,empresaId,usuarioId);
        if(result == null){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(201).body(result);

    }

    @PutMapping("/{idUsuario}/{idAvaliacao}/{idEmpresa}")
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
    public ResponseEntity<Void> deletarAvaliacaoDaEmpresa(@PathVariable Long idAvaliacao){

        boolean removeu = avaliacaoService.deletarAvaliacaoDaEmpresa(idAvaliacao);

        if(removeu){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).build();
    }


    
}
