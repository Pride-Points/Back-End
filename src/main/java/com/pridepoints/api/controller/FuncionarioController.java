package com.pridepoints.api.controller;

import com.pridepoints.api.dto.Usuario.Funcionario.FuncionarioCriacaoDTO;
import com.pridepoints.api.dto.Usuario.Funcionario.FuncionarioFullDTO;
import com.pridepoints.api.services.EmpresaService;
import com.pridepoints.api.services.FuncionarioService;
import com.pridepoints.api.utilities.download.DownloadCSV;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.pridepoints.api.dto.Usuario.Funcionario.FuncionarioFullDTO;
import com.pridepoints.api.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {


    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @Autowired
    private EmpresaService empresaService;

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
    @GetMapping("/download-csv")
    public ResponseEntity<Resource> downloadCSVFuncionarios(@RequestParam String cnpj) {
        long idEmpresa = empresaService.procurarPorCnpj(cnpj);
        List<FuncionarioFullDTO> funcionarios = funcionarioService.listarFuncionarioPeloIdEmpresa(idEmpresa);
        System.out.println("TA PASSANDO ISSOOOOOOOOOOO");
        System.out.println(funcionarios);
        if (funcionarios.isEmpty()) {
            // Se não houver funcionários, retorne uma resposta vazia com status 204
            System.out.println("Vazio");
            return ResponseEntity.noContent().build();
        } else {
            try {
                // Gere um arquivo CSV com os funcionários
                String csvFilename = "funcionarios.csv";
                DownloadCSV<FuncionarioFullDTO> csvExporter = new DownloadCSV<>();
                csvExporter.exportToCSV(funcionarios, csvFilename);

                // Crie um recurso FileSystemResource para o arquivo CSV gerado
                Resource resource = new FileSystemResource(csvFilename);

                // Configure os cabeçalhos da resposta para indicar o tipo de mídia e o nome do arquivo
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=funcionarios.csv");
                headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

                // Retorne a resposta com o arquivo CSV como anexo
                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } catch (IOException e) {
                // Em caso de erro ao criar o arquivo CSV, retorne uma resposta de erro 500
                return ResponseEntity.internalServerError().build();
            }
        }
    }
}
    }
