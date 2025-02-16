package com.example.U4_S6_L5_progetto.payload;

import com.example.U4_S6_L5_progetto.entity.Prenotazione;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
public class DipendenteDTO {

    @NotNull(message = "il campo username è obbligatorio")
    @NotBlank(message = "username non puo essere vuoto")
    private String username;

    @NotNull(message = "il campo nome è obbligatorio")
    @NotBlank(message = "nome non puo essere vuoto")
    private String nome;

    @NotNull(message = "il campo cognome è obbligatorio")
    @NotBlank(message = "cognome non puo essere vuoto")
    private String cognome;

    @Email(message = "email non valida")
    private String email;

    @URL
    private String avatar;



}
