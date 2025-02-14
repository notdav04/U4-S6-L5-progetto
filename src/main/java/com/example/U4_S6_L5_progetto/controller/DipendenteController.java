package com.example.U4_S6_L5_progetto.controller;

import com.example.U4_S6_L5_progetto.entity.Dipendente;
import com.example.U4_S6_L5_progetto.payload.DipendenteDTO;
import com.example.U4_S6_L5_progetto.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
