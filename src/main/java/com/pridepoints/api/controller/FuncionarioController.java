package com.pridepoints.api.controller;

import com.pridepoints.api.dto.Usuario.Funcionario.FuncionarioCriacaoDTO;
import com.pridepoints.api.dto.Usuario.Funcionario.FuncionarioFullDTO;
import com.pridepoints.api.services.EmpresaService;
import com.pridepoints.api.services.FuncionarioService;
import com.pridepoints.api.utilities.importacao.ImportacaoTxt;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {


    private final FuncionarioService funcionarioService;
    private final EmpresaService empresaService;

    public FuncionarioController(FuncionarioService funcionarioService, EmpresaService empresaService) {
        this.funcionarioService = funcionarioService;
        this.empresaService = empresaService;
    }

    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<FuncionarioFullDTO>> listarFuncionarios() {

        List<FuncionarioFullDTO> result = funcionarioService.listarFuncionarios();

        if (result.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(result);
    }

    @SecurityRequirement(name = "Bearer")
    @GetMapping("/ativos/{idEmpresa}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<FuncionarioFullDTO>> listarAtivos(@PathVariable Long idEmpresa) {

        List<FuncionarioFullDTO> result = funcionarioService.listarFuncionariosAtivos(idEmpresa);

        if (result.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(result);
    }

    @SecurityRequirement(name = "Bearer")
    @GetMapping("/inativos/{idEmpresa}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<FuncionarioFullDTO>> listarInativos(Long idEmpresa) {

        List<FuncionarioFullDTO> result = funcionarioService.listarFuncionariosInativos(idEmpresa);

        if (result.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(result);
    }

    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{idFunc}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FuncionarioFullDTO> listarPorId(@PathVariable Long idFunc) {
        FuncionarioFullDTO fullDTO = funcionarioService.listarPorId(idFunc);
        if (fullDTO == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(fullDTO);
    }

    @SecurityRequirement(name = "Bearer")
    @GetMapping("/ordenados")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<FuncionarioFullDTO>> listarFuncionariosOrdenadosPorNome() {
        List<FuncionarioFullDTO> result = funcionarioService.listarFuncionariosOrdenadosPorNome();
        if (result.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(result);
    }

    @SecurityRequirement(name = "Bearer")
    @PostMapping("/{empresaId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FuncionarioFullDTO> cadastrarFuncionario(@Valid @RequestBody FuncionarioCriacaoDTO f,
                                                                   @PathVariable Long empresaId) {

        FuncionarioFullDTO result = funcionarioService.cadastrarFuncionario(f, empresaId);

        if (result == null) {
            return ResponseEntity.status(409).build();
        } else {
            return ResponseEntity.status(201).body(result);
        }
    }
    @SecurityRequirement(name = "Bearer")
    @PostMapping("/importacao/{empresaId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<FuncionarioFullDTO>> cadastrarFuncionariosTxt(
            @PathVariable Long empresaId,
            @RequestParam("file") MultipartFile file
    ) {
        // Verifique se o arquivo é nulo ou vazio antes de prosseguir
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<FuncionarioFullDTO> result = new ImportacaoTxt(funcionarioService, empresaService).leArquivoTxt(file, empresaId);

        if (result.isEmpty()) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(201).body(result);
        }
    }

    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{idFunc}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deletarFunc(@PathVariable Long idFunc) {
        boolean result = funcionarioService.deletarFunc(idFunc);

        if (result) {
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }
        @SecurityRequirement(name = "Bearer")
        @GetMapping("/cpf")
        public ResponseEntity<FuncionarioFullDTO> encontrarFuncionarioPorCpf(@RequestParam String cpf){
            FuncionarioFullDTO result = funcionarioService.encontrarFuncionarioPorCpf(cpf);

            if (result != null) {
                return ResponseEntity.status(200).body(result);
            } else {
                return ResponseEntity.status(404).build();
            }
        }
    }