package com.pridepoints.api.Controller;

import com.pridepoints.api.DTO.Avaliacao.AvaliacaoDTO;
import com.pridepoints.api.DTO.Empresa.EmpresaFullDTO;
import com.pridepoints.api.DTO.Empresa.EmpresaMapper;
import com.pridepoints.api.DTO.Empresa.EmpresaMinDTO;
import com.pridepoints.api.DTO.Evento.EventoDTO;
import com.pridepoints.api.DTO.Usuario.Funcionario.FuncionarioFullDTO;
import com.pridepoints.api.DTO.Usuario.Funcionario.FuncionarioMapper;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Evento;
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
    public ResponseEntity<EmpresaFullDTO> cadastrarEmpresa(@RequestBody EmpresaDonoRequest request){
        Empresa empresa = EmpresaMapper.of(request.getEmpresa());
        Funcionario dono = FuncionarioMapper.of(request.getFuncionario());
        if(!validador.verificaObjetoEmpresa(empresa) && !validador.verificaObjetoDonoEmpresa(dono)){
            return ResponseEntity.status(400).build();
        } else {
            dono.setEmpresa(empresa);
            empresa.adicionarFuncionario(dono);
            EmpresaFullDTO result = empresaService.cadastrarEmpresa(empresa);
            if(result != null){
                FuncionarioFullDTO resultFunc = funcionarioService.cadastrarFuncionario(dono);
                if(resultFunc != null){
                    return ResponseEntity.status(201).body(result);
                } else {
                    return ResponseEntity.status(409).build();
                }
            } else {
                return ResponseEntity.status(409).build();
            }
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

    @GetMapping("/{id}/avaliacoes")
    public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacoes(@PathVariable Long id){

        List<AvaliacaoDTO> result = empresaService.listarAvaliacoes(id);

        if(result == null || result.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/{id}/eventos")
    public ResponseEntity<List<EventoDTO>> listarEventos(@PathVariable Long id){

        List<EventoDTO> result = empresaService.listarEventos(id);

        if(result == null || result.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(result);
    }

}
