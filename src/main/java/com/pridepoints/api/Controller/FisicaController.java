package com.pridepoints.api.Controller;

import com.pridepoints.api.DTO.FisicaDTO;
import com.pridepoints.api.entities.Fisica;
import com.pridepoints.api.services.FisicaService;
import com.pridepoints.api.utilities.MetodosAuxiliares;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class FisicaController {

    MetodosAuxiliares validador = new MetodosAuxiliares();
    @Autowired
    private FisicaService fisicaService;


    @GetMapping("/login")
    public ResponseEntity<FisicaDTO> loginUsuario(@RequestBody Fisica f){
        if(!validador.verificaEmaileSenha(f)){
            return ResponseEntity.status(400).build();
        } else {
            FisicaDTO result = fisicaService.loginUsuario(f);
            if(result == null){
                return ResponseEntity.status(404).build();
            } else {
                return ResponseEntity.status(200).body(result);
            }
        }
    }

    @PatchMapping("/{indice}")
    public ResponseEntity<FisicaDTO> atualizarEmaileSenha(@RequestBody Fisica novosDados, @PathVariable Long indice){
        if(!validador.verificaEmaileSenha(novosDados)){
            return ResponseEntity.status(400).build();
        } else {
            FisicaDTO result =  fisicaService.atualizarEmaileSenha(novosDados, indice);
            if(result == null){
                return ResponseEntity.status(404).build();
            } else {
                return ResponseEntity.status(200).body(result);
            }
        }
    }

    @PostMapping
    public ResponseEntity<FisicaDTO> cadastrarUsuario(@RequestBody Fisica f){
        if(!validador.verificaObjetoFisica(f)){
                return ResponseEntity.status(404).build();
        } else {
            FisicaDTO result = fisicaService.cadastrarUsuario(f);
            if(result == null){
                return ResponseEntity.status(409).build();
            } else {
                return ResponseEntity.status(201).body(result);
            }
        }
    }

    @GetMapping
    public ResponseEntity<List<FisicaDTO>> listarUsuarios(){
        List<FisicaDTO> listaUsuarios = fisicaService.listarPessoasFisicas();
        if(listaUsuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }
            return ResponseEntity.status(200).body(listaUsuarios);
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long indice){
        boolean result = fisicaService.removerPessoaFisica(indice);
        if(result){
            return ResponseEntity.status(200).build();
        }
            return ResponseEntity.status(404).build();
    }
}
