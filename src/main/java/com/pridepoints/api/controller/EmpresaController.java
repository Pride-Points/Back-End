package com.pridepoints.api.controller;


import com.pridepoints.api.dto.Empresa.EmpresaCriacaoDTO;
import com.pridepoints.api.dto.Empresa.EmpresaFullDTO;
import com.pridepoints.api.dto.Empresa.EmpresaMapper;
import com.pridepoints.api.dto.Empresa.EmpresaMinDTO;
import com.pridepoints.api.dto.Usuario.Funcionario.FuncionarioFullDTO;
import com.pridepoints.api.dto.Usuario.Funcionario.FuncionarioMapper;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Funcionario;
import com.pridepoints.api.services.EmpresaService;
import com.pridepoints.api.utilities.multiclasse.EmpresaDonoRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;
    private final PasswordEncoder passwordEncoder;

    public EmpresaController(EmpresaService empresaService,
                             PasswordEncoder passwordEncoder){
        this.empresaService = empresaService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<EmpresaFullDTO> cadastrarEmpresa(@Valid @RequestBody EmpresaDonoRequest request){
        Empresa empresa = EmpresaMapper.of(request.getEmpresa());
        String senhaCriptografada = passwordEncoder.encode(request.getFuncionario().getSenha());
        request.getFuncionario().setSenha(senhaCriptografada);
        Funcionario dono = FuncionarioMapper.of(request.getFuncionario());
        dono.setEmpresa(empresa);
        empresa.adicionarFuncionario(dono);
        EmpresaFullDTO result = empresaService.cadastrarEmpresa(empresa);
        if(result != null){
            return ResponseEntity.status(200).body(result);
        } else {
            return ResponseEntity.status(409).build();
        }
    }
    @GetMapping
    public ResponseEntity<List<EmpresaMinDTO>> listarEmpresas(){
        List<EmpresaMinDTO> listaDeEmpresas = empresaService.listarEmpresas();
        if(listaDeEmpresas == null || listaDeEmpresas.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaDeEmpresas);
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaFullDTO> buscarPorId(@PathVariable Long id){
        EmpresaFullDTO result = empresaService.buscarPorId(id);

        if(result == null){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(result);
    }
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{idEmpresa}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EmpresaFullDTO> atualizarEmpresa(@Valid @RequestBody EmpresaCriacaoDTO
            novosDados,
                                                           @PathVariable Long idEmpresa){
        EmpresaFullDTO result = empresaService.atualizarEmpresa(novosDados, idEmpresa);

        if(result == null){
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(result);
        }
    }
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{idEmpresa}")
    public ResponseEntity<Void> removerEmpresa(@PathVariable Long idEmpresa){

        boolean removeu = empresaService.deletarEmpresa(idEmpresa);

        if(removeu){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).build();
    }

    @GetMapping("/funcionarios-por-cnpj")
    public ResponseEntity<List<FuncionarioFullDTO>> getFuncionariosDaEmpresa(@RequestParam String cnpj) {
        Long empresaId = empresaService.procurarPorCnpj(cnpj);

        if (empresaId != null) {
            List<FuncionarioFullDTO> funcionarios = funcionarioService.listarFuncionarioPeloIdEmpresa(empresaId);
            return ResponseEntity.ok(funcionarios);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
