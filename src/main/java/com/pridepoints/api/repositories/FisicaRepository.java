package com.pridepoints.api.repositories;

import com.pridepoints.api.entities.Fisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FisicaRepository extends JpaRepository<Fisica, Long> {
    @Query
    boolean existsByEmailAndSenha(String email, String senha);


}
