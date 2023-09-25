package com.pridepoints.api.Controller;


import com.pridepoints.api.DTO.Empresa.EmpresaCriacaoDTO;
import com.pridepoints.api.DTO.Empresa.EmpresaFullDTO;
import com.pridepoints.api.DTO.Empresa.EmpresaMapper;
import com.pridepoints.api.DTO.Empresa.EmpresaMinDTO;
import com.pridepoints.api.DTO.Usuario.Funcionario.FuncionarioFullDTO;
import com.pridepoints.api.DTO.Usuario.Funcionario.FuncionarioMapper;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Funcionario;
import com.pridepoints.api.services.EmpresaService;
import com.pridepoints.api.services.FuncionarioService;
import com.pridepoints.api.utilities.multiclasse.EmpresaDonoRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<EmpresaFullDTO> cadastrarEmpresa(@Valid @RequestBody EmpresaDonoRequest request){
        Empresa empresa = EmpresaMapper.of(request.getEmpresa());
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

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaFullDTO> buscarPorId(@PathVariable Long id){
        EmpresaFullDTO result = empresaService.buscarPorId(id);

        if(result == null){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(result);
    }

    @PutMapping("/{idEmpresa}")
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

    @DeleteMapping("/{idEmpresa}")
    public ResponseEntity<Void> removerEmpresa(@PathVariable Long idEmpresa){

        boolean removeu = empresaService.deletarEmpresa(idEmpresa);

        if(removeu){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).build();
    }

}
