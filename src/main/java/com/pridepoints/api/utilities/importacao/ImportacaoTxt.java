package com.pridepoints.api.utilities.importacao;

import com.pridepoints.api.dto.Usuario.Funcionario.FuncionarioCriacaoDTO;
import com.pridepoints.api.dto.Usuario.Funcionario.FuncionarioFullDTO;
import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Funcionario;
import com.pridepoints.api.services.EmpresaService;
import com.pridepoints.api.services.FuncionarioService;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ImportacaoTxt {

    private final FuncionarioService funcionarioService;
    private final EmpresaService empresaService;

    public ImportacaoTxt(FuncionarioService funcionarioService, EmpresaService empresaService) {
        this.funcionarioService = funcionarioService;
        this.empresaService = empresaService;
    }

    public List<FuncionarioFullDTO> leArquivoTxt(MultipartFile file) {
        List<Funcionario> listaFuncionarios = new ArrayList<>();
        List<Empresa> listaEmpresas = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String registro;
            while ((registro = entrada.readLine()) != null) {
                String tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("02")) {

                    String nome = registro.substring(2, 257).trim();

                    String email = registro.substring(257, 257).trim();

                    String cargo = registro.substring(257, 512).trim();

                    String cpf = registro.substring(512, 542).trim();

                    String tipoFuncionario = registro.substring(542, 553).trim();

                    Boolean isGerente = registro.substring(542, 553).trim().equalsIgnoreCase("true");


                    Funcionario funcionario = new Funcionario();
                    funcionario.setCargo(cargo);
                    funcionario.setCpf(cpf);
                    funcionario.setTipoFuncionario(tipoFuncionario);
                    funcionario.setNome(nome);
                    funcionario.setEmail(email);
                    funcionario.setSenha("SenhaPadrao@123");
                    listaFuncionarios.add(funcionario);
                }
                else if(tipoRegistro.equals("03")){
                    String cnpj = registro.substring(2, 20).trim();
                    Empresa empresa = new Empresa();

                    empresa.setCnpj(cnpj);
                    listaEmpresas.add(empresa);
                }else{
                    System.out.println("Tipo de registro indevido: " + registro);
                }

            }
        } catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
        }
        // Processar os dados de empresas e associá-los aos usuários
        for (Funcionario funcionarioAtual : listaFuncionarios) {
            for (Empresa empresa : listaEmpresas) {
                if(empresa.getId() != null){
                    Long idempresa = empresaService.procurarPorCnpj(empresa.getCnpj());
                        empresa = empresaService.buscarPorIdTxt(idempresa);
                        funcionarioAtual.setEmpresa(empresa);
                    break;
                }
            }
        }
        // Aqui seria possível salvar a lista no BD
        List<FuncionarioFullDTO> funcionariosDTO = funcionarioService.salvarFuncionarios(listaFuncionarios);
        return funcionariosDTO;
    }
}
