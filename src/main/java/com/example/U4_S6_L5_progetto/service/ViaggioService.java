package com.example.U4_S6_L5_progetto.service;


import com.example.U4_S6_L5_progetto.entity.Viaggio;
import com.example.U4_S6_L5_progetto.payload.ViaggioDTO;
import com.example.U4_S6_L5_progetto.repository.ViaggioDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViaggioService {

    @Autowired
    ViaggioDAORepository viaggioDao;

    //metodi dao

    //salvataggio viaggio
    public long saveViaggio(ViaggioDTO viaggiodto){
        Viaggio viaggio = fromViaggioDTOToEntity(viaggiodto);
        Viaggio viaggioSalavato = viaggioDao.save(viaggio);
        return viaggioSalavato.getId();
    }

    //ottenere tutti i viaggi( get all )
    public Page<ViaggioDTO> findAllViaggio(Pageable page){
        Page<Viaggio> listaViaggi = viaggioDao.findAll(page);
        List<ViaggioDTO> listaViaggiDTO = new ArrayList<>();
        for(Viaggio viaggio :listaViaggi.getContent()){
            ViaggioDTO viaggioDTO = fromViaggioToViaggioDTO(viaggio);
            listaViaggiDTO.add(viaggioDTO);
        }
        Page<ViaggioDTO> listaPage = new PageImpl<>(listaViaggiDTO);
        return listaPage;
    }

    //ottenere un singolo viaggio( get by id )
    public ViaggioDTO findViaggioById(long id){
        Optional<Viaggio> viaggio = viaggioDao.findById(id);
        if(viaggio.isPresent()) {
            ViaggioDTO viaggioDTO = fromViaggioToViaggioDTO(viaggio.get());
            return viaggioDTO;
        }else{
            throw new RuntimeException("nessun viaggio trovato con id richiesto");
        }
    }

    //modificare viaggio( put )
    public void modificaViaggio(ViaggioDTO viaggioDTO, long id){
        Optional<Viaggio> viaggioTrovato = viaggioDao.findById(id);
        if(viaggioTrovato.isPresent()){
            Viaggio viaggio = viaggioTrovato.get();
            viaggio.setDestinazione(viaggioDTO.getDestinazione());
            viaggio.setData(viaggioDTO.getData());
            viaggio.setStato(viaggioDTO.getStato());
            viaggioDao.save(viaggio);
        }else {
            throw new RuntimeException("nessun viaggio trovato con l id richiesto! errore nella modifica");
        }
    }

    //eliminare viaggio( delete )
    public void eliminaViaggio(long id){
        Optional<Viaggio> viaggioTrovato = viaggioDao.findById(id);
        if(viaggioTrovato.isPresent()){
            viaggioDao.deleteById(id);
        }else {
            throw new RuntimeException("nessun viaggio trovato con l id richiesto! errore nell eliminazione");
        }
    }



    //metodi dto
    //travasi DTO
    public Viaggio fromViaggioDTOToEntity(ViaggioDTO viaggioDTO) {
        Viaggio viaggio = new Viaggio();
        viaggio.setDestinazione(viaggioDTO.getDestinazione());
        viaggio.setStato(viaggioDTO.getStato());
        viaggio.setData(viaggioDTO.getData());
        return viaggio;
    }

    public ViaggioDTO fromViaggioToViaggioDTO(Viaggio viaggio) {
        ViaggioDTO viaggioDTO = new ViaggioDTO();
        viaggioDTO.setDestinazione(viaggio.getDestinazione());
        viaggioDTO.setStato(viaggio.getStato());
        viaggioDTO.setData(viaggio.getData());
        return viaggioDTO;
    }
}
