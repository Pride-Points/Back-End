package com.pridepoints.api.Controller;

import com.pridepoints.api.DTO.AvaliacaoDTO;
import com.pridepoints.api.DTO.EmpresaDTO;
import com.pridepoints.api.entities.Avaliacao;
import com.pridepoints.api.services.AvaliacaoService;
import com.pridepoints.api.utilities.MetodosAuxiliares;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
    MetodosAuxiliares validador = new MetodosAuxiliares();
    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping("/{empresaId}/{usuarioId}")
    public ResponseEntity<AvaliacaoDTO> publicarAvaliacao(@RequestBody Avaliacao f, @PathVariable Long empresaId, @PathVariable Long usuarioId){
        if(!validador.verificaObjetoAvaliacao(f)){
            return ResponseEntity.status(400).build();
        } else {
            AvaliacaoDTO result = avaliacaoService.publicarAvaliacao(f,empresaId,usuarioId);
            if(result == null){
                return ResponseEntity.status(404).build();
            }else {
                return ResponseEntity.status(201).body(result);
            }
        }
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacoes(){
        List<AvaliacaoDTO> listaDeAvaliacoes = avaliacaoService.listarAvaliacoes();
        if(listaDeAvaliacoes.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaDeAvaliacoes);
    }
}
