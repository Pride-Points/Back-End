package com.pridepoints.api.repositories;

import com.pridepoints.api.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuridicaRepository extends JpaRepository<Funcionario, Long> {

}
