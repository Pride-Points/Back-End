package com.pridepoints.api.repositories;

import com.pridepoints.api.entities.Fisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FisicaRepository extends JpaRepository<Fisica, Long> {
    @Query("SELECT f FROM Fisica f WHERE f.email = :email AND f.senha = :senha")
     Fisica findByEmailAndSenha(@Param("email") String email,@Param("senha") String senha);

    @Query("SELECT f FROM Fisica f WHERE f.email = :email")
    Fisica findByEmail(@Param("email") String email);
}
