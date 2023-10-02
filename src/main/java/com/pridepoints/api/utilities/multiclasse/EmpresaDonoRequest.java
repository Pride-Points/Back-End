package com.pridepoints.api.utilities.multiclasse;

import com.pridepoints.api.dto.Empresa.EmpresaCriacaoDTO;
import com.pridepoints.api.dto.Usuario.Funcionario.FuncionarioCriacaoDTO;

public class EmpresaDonoRequest {
    private EmpresaCriacaoDTO empresa;
    private FuncionarioCriacaoDTO funcionario;

    public EmpresaDonoRequest(EmpresaCriacaoDTO empresa, FuncionarioCriacaoDTO funcionario) {
        this.empresa = empresa;
        this.funcionario = funcionario;
    }

    public EmpresaCriacaoDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaCriacaoDTO empresa) {
        this.empresa = empresa;
    }

    public FuncionarioCriacaoDTO getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioCriacaoDTO funcionario) {
        this.funcionario = funcionario;
    }
}
