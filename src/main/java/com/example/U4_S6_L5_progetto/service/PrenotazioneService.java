package com.example.U4_S6_L5_progetto.service;

import com.example.U4_S6_L5_progetto.repository.PrenotazioneDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {

    @Autowired
    PrenotazioneDAORepository prenotazioneDAO;
}
