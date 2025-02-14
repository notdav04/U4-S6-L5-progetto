package com.example.U4_S6_L5_progetto.repository;

import com.example.U4_S6_L5_progetto.entity.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteDAORepository extends JpaRepository<Dipendente, Long> {
}
