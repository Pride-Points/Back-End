package com.pridepoints.api.Controller;

import com.pridepoints.api.DTO.Usuario.Fisica.FisicaCriacaoDTO;
import com.pridepoints.api.DTO.Usuario.Fisica.FisicaFullDTO;
import com.pridepoints.api.DTO.Usuario.Fisica.FisicaMinDTO;
import com.pridepoints.api.services.FisicaService;
import com.pridepoints.api.utilities.methods.MetodosAuxiliares;
import jakarta.validation.Valid;
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
    public ResponseEntity<FisicaFullDTO> loginUsuario(@Valid @RequestBody FisicaCriacaoDTO f){

            FisicaFullDTO result = fisicaService.loginUsuario(f);
            if(result == null){
                return ResponseEntity.status(404).build();
            } else {
                return ResponseEntity.status(200).body(result);
            }
    }

    @PatchMapping("/{indice}")
    public ResponseEntity<FisicaFullDTO> atualizarEmail(@RequestBody FisicaCriacaoDTO obj, @PathVariable Long indice){
            FisicaFullDTO result =  fisicaService.atualizarEmail(obj, indice);
            if(result == null){
                return ResponseEntity.status(404).build();
            } else {
                return ResponseEntity.status(200).body(result);
            }
    }

    @PostMapping
    public ResponseEntity<FisicaFullDTO> cadastrarUsuario(@Valid @RequestBody FisicaCriacaoDTO f){

            FisicaFullDTO result = fisicaService.cadastrarUsuario(f);
            if(result == null){
                return ResponseEntity.status(409).build();
            } else {
                return ResponseEntity.status(201).body(result);
            }
    }

    @GetMapping
    public ResponseEntity<List<FisicaMinDTO>> listarUsuarios(){
        List<FisicaMinDTO> listaUsuarios = fisicaService.listarPessoasFisicas();
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
