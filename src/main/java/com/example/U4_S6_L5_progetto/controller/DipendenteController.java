package com.example.U4_S6_L5_progetto.controller;

import com.example.U4_S6_L5_progetto.entity.Dipendente;
import com.example.U4_S6_L5_progetto.payload.DipendenteDTO;
import com.example.U4_S6_L5_progetto.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dipendente")
public class DipendenteController {

    @Autowired
    DipendenteService dipendenteService;

    //metodi CRUD
    //POST
    @PostMapping
    public ResponseEntity<?> postDipendente(@RequestBody @Validated DipendenteDTO dipendenteDTO, BindingResult validation){
        if (validation.hasErrors()){
            String message = "ERRORE DI VALIDAZIONE \n";
            for(ObjectError error : validation.getAllErrors()){
                message += error.getDefaultMessage() + "\n";

            }
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        long idDipendenteSalvato = dipendenteService.saveDipendente(dipendenteDTO);
        return new ResponseEntity<>("dipendente inserito con id: " + idDipendenteSalvato, HttpStatus.CREATED);
    }
    //GET(ALL)
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Page<DipendenteDTO>> getAllDipendente(Pageable page){
        Page<DipendenteDTO> dipendenteDTO = dipendenteService.findAllDipendente(page);
        return new ResponseEntity<>(dipendenteDTO, HttpStatus.OK);
    }

    //GET
    @GetMapping("/{id}")
    public DipendenteDTO getDipendenteById(@PathVariable long id){
        return dipendenteService.findDipendenteById(id);
    }

    //PUT
    @PutMapping("/update/{id}")
    public ResponseEntity<?> putDipendente(@RequestBody @Validated DipendenteDTO dipendenteDTO, @PathVariable long id, BindingResult validation){
        if(validation.hasErrors()){
            String message = "ERRORE DI VALIDAZIONE \n";
            for (ObjectError errore : validation.getAllErrors()){
                message += errore.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }else{
            dipendenteService.modificaDipendente(dipendenteDTO, id);
            return new ResponseEntity<>("dipendente modificato con successo", HttpStatus.OK );
        }
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDipendente(@PathVariable long id){
        dipendenteService.eliminaDipendente(id);
        return new ResponseEntity<>("dipendente eliminato con successo", HttpStatus.OK);
    }
}
