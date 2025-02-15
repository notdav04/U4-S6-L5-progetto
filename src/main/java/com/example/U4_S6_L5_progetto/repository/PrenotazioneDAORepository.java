package com.example.U4_S6_L5_progetto.repository;

import com.example.U4_S6_L5_progetto.entity.Dipendente;
import com.example.U4_S6_L5_progetto.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PrenotazioneDAORepository extends JpaRepository<Prenotazione, Long> {

    //@Query("SELECT p FROM Prenotazione p WHERE p.data = :data AND p.dipendente.id = :dipendenteId")
    public List<Prenotazione> findByDataAndDipendente( LocalDate data, Dipendente dipendente);
}
