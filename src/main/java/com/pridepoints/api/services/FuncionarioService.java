package com.pridepoints.api.services;

import com.pridepoints.api.dto.Usuario.Funcionario.FuncionarioCriacaoDTO;
import com.pridepoints.api.dto.Usuario.Funcionario.FuncionarioFullDTO;
import com.pridepoints.api.dto.Usuario.Funcionario.FuncionarioMapper;
import com.pridepoints.api.entities.Empresa;
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
import java.util.stream.Collectors;

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

            Empresa empresa = consultaBancoEmpresa.get();
            empresa.adicionarFuncionario(funcionarioMapeado);

            empresaRepository.save(empresa);

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

    public List<FuncionarioFullDTO> listarFuncionariosAtivos() {

        List<Funcionario> ativosList = funcionarioRepository.findAll()
                .stream().filter(funcionario -> funcionario.isAtivo())
                .collect(Collectors.toList());

        return FuncionarioMapper.of(ativosList);
    }

    public List<FuncionarioFullDTO> listarFuncionariosInativos() {
        List<Funcionario> ativosList = funcionarioRepository.findAll()
                .stream().filter(funcionario -> !funcionario.isAtivo())
                .collect(Collectors.toList());

        return FuncionarioMapper.of(ativosList);
    }
}
