package com.pridepoints.api.repositories;

import com.pridepoints.api.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
