package com.pridepoints.api.repositories;

import com.pridepoints.api.entities.Fisica;
import com.pridepoints.api.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Query("SELECT f FROM Funcionario f WHERE f.email = :email")
    Funcionario findByEmail(@Param("email") String email);
}
