package com.pridepoints.api.Controller;

import com.pridepoints.api.DTO.FuncionarioDTO;
import com.pridepoints.api.entities.Funcionario;
import com.pridepoints.api.services.FuncionarioService;
import com.pridepoints.api.utilities.methods.MetodosAuxiliares;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    MetodosAuxiliares validador = new MetodosAuxiliares();

    @Autowired
    private FuncionarioService funcionarioService;


    @PostMapping("/{empresaId}")
    public ResponseEntity<FuncionarioDTO> cadastrarFuncionario(@RequestBody Funcionario f, @PathVariable Long empresaId){
    if(!validador.verificaObjetoFuncionario(f)){
        return ResponseEntity.status(400).build();
        }
        else {
            FuncionarioDTO result = funcionarioService.cadastrarFuncionario(f, empresaId);
            if(result == null){
                return ResponseEntity.status(409).build();
            }
            else {
                return ResponseEntity.status(201).body(result);
            }
        }
    }
}
