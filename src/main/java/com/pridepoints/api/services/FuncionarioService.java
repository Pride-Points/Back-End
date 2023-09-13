package com.pridepoints.api.services;

import com.pridepoints.api.DTO.FuncionarioDTO;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Fisica;
import com.pridepoints.api.entities.Funcionario;
import com.pridepoints.api.repositories.EmpresaRepository;
import com.pridepoints.api.repositories.FuncionarioRepository;
import com.pridepoints.api.utilities.interfaces.iValidarTrocaDeSenha;
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
    public FuncionarioDTO cadastrarFuncionario(Funcionario e){
        Funcionario consultaBanco = funcionarioRepository.findByEmail(e.getEmail());

        if(consultaBanco == null){
            Funcionario result = funcionarioRepository.save(e);
            return new FuncionarioDTO(result);
        }
        return null;
    }

    @Transactional
    public FuncionarioDTO cadastrarFuncionario(Funcionario funcionario, Long idEmpresa){
        Funcionario consultaBancoFuncionario = funcionarioRepository.findByEmail(funcionario.getEmail());
        Optional<Empresa> consultaBancoEmpresa = empresaRepository.findById(idEmpresa);
        if(consultaBancoFuncionario == null && consultaBancoEmpresa.isPresent()){
            funcionario.setEmpresa(consultaBancoEmpresa.get());

            Empresa empresa = consultaBancoEmpresa.get();
            empresa.adicionarFuncionario(funcionario);

            empresaRepository.save(empresa);
            funcionarioRepository.save(funcionario);
            return new FuncionarioDTO(funcionario);
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
    }
