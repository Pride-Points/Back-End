package com.pridepoints.api.repositories;

import com.pridepoints.api.entities.Metrica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricaRepository extends JpaRepository<Metrica, Long> {
}
