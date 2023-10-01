package com.pridepoints.api.repositories;

import com.pridepoints.api.entities.Empresa;
import com.pridepoints.api.entities.Fisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    @Query("SELECT e FROM Empresa e WHERE e.cnpj = :cnpj")
    boolean existsByCnpj(@Param("cnpj") String cnpj);
}
