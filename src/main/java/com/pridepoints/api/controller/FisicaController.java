package com.pridepoints.api.controller;

import com.pridepoints.api.dto.Autenticacao.UsuarioTokenDTO;
import com.pridepoints.api.dto.Usuario.Fisica.FisicaCriacaoDTO;
import com.pridepoints.api.dto.Usuario.Fisica.FisicaFullDTO;
import com.pridepoints.api.dto.Usuario.Fisica.FisicaMinDTO;
import com.pridepoints.api.services.FisicaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class FisicaController {
    @Autowired
    private FisicaService fisicaService;


    @PostMapping("/login")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<UsuarioTokenDTO> loginUsuario(@Valid @RequestBody FisicaCriacaoDTO f){

        UsuarioTokenDTO result = fisicaService.autenticarFisica(f);
        if(result == null){
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(result);
        }
    }

    @PatchMapping("/{indice}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<FisicaFullDTO> atualizarEmail(@RequestBody FisicaCriacaoDTO obj, @PathVariable Long indice){
            FisicaFullDTO result =  fisicaService.atualizarEmail(obj, indice);
            if(result == null){
                return ResponseEntity.status(404).build();
            } else {
                return ResponseEntity.status(200).body(result);
            }
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<FisicaFullDTO> cadastrarUsuario(@Valid @RequestBody FisicaCriacaoDTO f){

            FisicaFullDTO result = fisicaService.cadastrarUsuario(f);
            if(result == null){
                return ResponseEntity.status(409).build();
            } else {
                return ResponseEntity.status(201).body(result);
            }
    }

    @GetMapping("/listar-usuarios")
    @SecurityRequirement(name = "Bearer")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<FisicaMinDTO>> listarUsuarios(){
        List<FisicaMinDTO> listaUsuarios = fisicaService.listarPessoasFisicas();
        if(listaUsuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }
            return ResponseEntity.status(200).body(listaUsuarios);
    }

    @DeleteMapping("/{indice}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long indice){
        boolean result = fisicaService.removerPessoaFisica(indice);
        if(result){
            return ResponseEntity.status(200).build();
        }
            return ResponseEntity.status(404).build();
    }



}
