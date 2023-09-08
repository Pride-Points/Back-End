package com.pridepoints.api.utilities.multiclasse;

import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Funcionario;

public class EmpresaDonoRequest {
    private Empresa empresa;
    private Funcionario funcionario;

    public EmpresaDonoRequest(Empresa empresa, Funcionario funcionario) {
        this.empresa = empresa;
        this.funcionario = funcionario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
