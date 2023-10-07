package com.pridepoints.api.services;

import com.pridepoints.api.dto.Avaliacao.AvaliacaoDTO;
import com.pridepoints.api.dto.Avaliacao.AvaliacaoMapper;
import com.pridepoints.api.dto.Usuario.Funcionario.FuncionarioCriacaoDTO;
import com.pridepoints.api.dto.Usuario.Funcionario.FuncionarioFullDTO;
import com.pridepoints.api.dto.Usuario.Funcionario.FuncionarioMapper;
import com.pridepoints.api.entities.Avaliacao;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Funcionario;
import com.pridepoints.api.repositories.EmpresaRepository;
import com.pridepoints.api.repositories.FuncionarioRepository;
import com.pridepoints.api.utilities.PesquisaBinaria.PesquisaBinaria;
import com.pridepoints.api.utilities.interfaces.iValidarTrocaDeSenha;
import com.pridepoints.api.utilities.ordenacao.Ordenacao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService implements iValidarTrocaDeSenha {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmailService emailService;


    @Transactional
    public FuncionarioFullDTO cadastrarFuncionario(FuncionarioCriacaoDTO funcionario, Long idEmpresa){

        boolean existsByEmail = funcionarioRepository.existsByEmail(funcionario.getEmail());
        Optional<Empresa> consultaBancoEmpresa = empresaRepository.findById(idEmpresa);

        if(!existsByEmail && consultaBancoEmpresa.isPresent()){
            Funcionario funcionarioMapeado = FuncionarioMapper.of(funcionario);
            funcionarioMapeado.setEmpresa(consultaBancoEmpresa.get());

           Funcionario result = funcionarioRepository.save(funcionarioMapeado);
            return FuncionarioMapper.ofFull(result);
        }
        return null;
    }

    @Override
    @Scheduled(cron = "0 0 0 1 */2 ?") // Agendar a cada 2 meses
    public void validatePasswordChange() throws AddressException {
            LocalDateTime doisMesesAtras = LocalDateTime.now().minusMonths(2);
            List<Funcionario> funcionarios = funcionarioRepository.findAll();

            for (Funcionario f: funcionarios){
                if(f.getUltimaTrocaSenha() == null || f.getUltimaTrocaSenha().isBefore(doisMesesAtras)){
                    emailService.enviarNotificacaoAlterarSenha(f);
                }
            }
        }

    @Transactional
    public List<FuncionarioFullDTO> listarFuncionarios() {

        List<Funcionario> funcionarioList = funcionarioRepository.findAll();

            return FuncionarioMapper.of(funcionarioList);
    }

    @Transactional
    public List<FuncionarioFullDTO> listarFuncionariosAtivos() {

        List<Funcionario> ativosList = funcionarioRepository.findByIsAtivoTrue();

        if(ativosList.isEmpty()){
            return null;
        }

        return FuncionarioMapper.of(ativosList);
    }

    @Transactional
    public List<FuncionarioFullDTO> listarFuncionariosInativos() {
        List<Funcionario> ativosList = funcionarioRepository.findByIsAtivoFalse();

        if(ativosList.isEmpty()){
            return null;
        }

        return FuncionarioMapper.of(ativosList);
    }
    @Transactional
    public FuncionarioFullDTO listarPorId(Long idFunc) {
        Optional<Funcionario> func = funcionarioRepository.findById(idFunc);

        if(func.isPresent()){
            return FuncionarioMapper.ofFull(func.get());
        }
        return null;
    }

    @Transactional
    public List<FuncionarioFullDTO> listarFuncionariosOrdenadosPorNome() {
        List<Funcionario> funcionarioList = funcionarioRepository.findAll();

        Ordenacao ordenacaoNome = new Ordenacao();
        List<Funcionario> funcionarioOrdenado = ordenacaoNome.ordenaAlfabeticamente(funcionarioList);

        return FuncionarioMapper.of(funcionarioOrdenado);
    }

}
