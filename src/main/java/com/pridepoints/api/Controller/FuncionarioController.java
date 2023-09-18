package com.pridepoints.api.Controller;

import com.pridepoints.api.DTO.Usuario.Funcionario.FuncionarioCriacaoDTO;
import com.pridepoints.api.DTO.Usuario.Funcionario.FuncionarioFullDTO;
import com.pridepoints.api.entities.Funcionario;
import com.pridepoints.api.services.FuncionarioService;
import com.pridepoints.api.utilities.methods.MetodosAuxiliares;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    MetodosAuxiliares validador = new MetodosAuxiliares();

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<FuncionarioFullDTO>> listarFuncionarios(){

        List<FuncionarioFullDTO> result = funcionarioService.listarFuncionarios();

        if(result.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<FuncionarioFullDTO>> listarAtivos(){

        List<FuncionarioFullDTO> result = funcionarioService.listarFuncionariosAtivos();

        if(result.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(result);
    }

    @PostMapping("/{empresaId}")
    public ResponseEntity<FuncionarioFullDTO> cadastrarFuncionario(@RequestBody FuncionarioCriacaoDTO f, @PathVariable Long empresaId){
    if(!validador.verificaObjetoFuncionario(f)){
        return ResponseEntity.status(400).build();
        }
        else {
            FuncionarioFullDTO result = funcionarioService.cadastrarFuncionario(f, empresaId);
            if(result == null){
                return ResponseEntity.status(409).build();
            }
            else {
                return ResponseEntity.status(201).body(result);
            }
        }
    }
}
