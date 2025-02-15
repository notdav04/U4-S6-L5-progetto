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


    public List<Prenotazione> findByDataAndDipendente( LocalDate data, Dipendente dipendente);

    //la riga "prevenire la prenotazione di viaggi per dipendenti gia impegnati in altre date" era poco chiara e l ho interpretata cosi:
    //se il dipendente ha gia prenotato un viaggio non puo prenotarne un altro.
    public List<Prenotazione> findByDipendente(Dipendente dipendente);
}
