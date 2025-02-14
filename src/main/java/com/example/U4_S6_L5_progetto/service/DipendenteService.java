package com.example.U4_S6_L5_progetto.service;

import com.example.U4_S6_L5_progetto.entity.Dipendente;
import com.example.U4_S6_L5_progetto.entity.Viaggio;
import com.example.U4_S6_L5_progetto.payload.DipendenteDTO;
import com.example.U4_S6_L5_progetto.payload.ViaggioDTO;
import com.example.U4_S6_L5_progetto.repository.DipendenteDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DipendenteService {

    @Autowired
    DipendenteDAORepository dipendenteDAO;

    //metodi dao
    //salvo dipendente( post )
    public Long saveDipendente(DipendenteDTO dipendenteDTO){
        Dipendente dipendente = fromDipendenteDTOToEntity(dipendenteDTO);
        return dipendenteDAO.save(dipendente).getId();
    }
    //prendo tutti i dipendenti( get all )
    public Page<DipendenteDTO> findAllDipendente(Pageable page) {
        Page<Dipendente> listaDipendenti = dipendenteDAO.findAll(page);
        List<DipendenteDTO> listaDipendentiDTO = new ArrayList<>();
        for (Dipendente dipendente : listaDipendenti.getContent()) {
            DipendenteDTO dipendenteDTO =fromDipendenteToDipendenteDTO(dipendente);
                    listaDipendentiDTO.add(dipendenteDTO);
        }
        Page<DipendenteDTO> listaPage = new PageImpl<>(listaDipendentiDTO);
        return listaPage;
    }

    //prendo un solo dipendente( get by id )
    public DipendenteDTO findDipendenteById(long id){
        Optional<Dipendente> dipendente = dipendenteDAO.findById(id);
        if (dipendente.isPresent()){
            DipendenteDTO dipendenteDTO = fromDipendenteToDipendenteDTO(dipendente.get());
            return dipendenteDTO;
        }else{
            throw new RuntimeException("nessun dipendente trovato con l id richiesto")
        }
    }

    //travasi DTO
    public Dipendente fromDipendenteDTOToEntity(DipendenteDTO dipendenteDTO) {
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(dipendenteDTO.getUsername());
        dipendente.setNome(dipendenteDTO.getNome());
        dipendente.setCognome(dipendenteDTO.getCognome());
        dipendente.setEmail(dipendenteDTO.getEmail());
        return dipendente;
    }

    public DipendenteDTO fromDipendenteToDipendenteDTO(Dipendente dipendente) {
        DipendenteDTO dipendenteDTO = new DipendenteDTO();
        dipendenteDTO.setUsername(dipendente.getUsername());
        dipendenteDTO.setNome(dipendente.getNome());
        dipendenteDTO.setCognome(dipendente.getCognome());
        dipendenteDTO.setEmail(dipendente.getEmail());
        return dipendenteDTO;
    }
}
