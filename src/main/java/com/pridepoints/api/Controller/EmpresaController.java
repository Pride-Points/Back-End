package com.pridepoints.api.Controller;

import com.pridepoints.api.DTO.EmpresaDTO;
import com.pridepoints.api.DTO.FuncionarioDTO;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Funcionario;
import com.pridepoints.api.services.EmpresaService;
import com.pridepoints.api.services.FuncionarioService;
import com.pridepoints.api.utilities.multiclasse.EmpresaDonoRequest;
import com.pridepoints.api.utilities.methods.MetodosAuxiliares;
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
    MetodosAuxiliares validador = new MetodosAuxiliares();

    @PostMapping
    public ResponseEntity<EmpresaDTO> cadastrarEmpresa(@RequestBody EmpresaDonoRequest request){
        Empresa empresa = request.getEmpresa();
        Funcionario dono = request.getFuncionario();
        if(!validador.verificaObjetoEmpresa(empresa) && !validador.verificaObjetoDonoEmpresa(dono)){
            return ResponseEntity.status(400).build();
        } else {
            dono.setEmpresa(empresa);
            empresa.adicionarFuncionario(dono);
            EmpresaDTO result = empresaService.cadastrarEmpresa(empresa);
            FuncionarioDTO resultFunc = funcionarioService.cadastrarFuncionario(dono);
            if(result == null || resultFunc == null){
                return ResponseEntity.status(409).build();
            } else {
                return ResponseEntity.status(201).body(result);
            }
        }
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> listarUsuarios(){
        List<EmpresaDTO> listaDeEmpresas = empresaService.listarEmpresas();
        if(listaDeEmpresas.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaDeEmpresas);
    }
}
