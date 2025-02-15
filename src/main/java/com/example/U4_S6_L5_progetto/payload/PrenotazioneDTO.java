package com.example.U4_S6_L5_progetto.payload;

import com.example.U4_S6_L5_progetto.entity.Dipendente;
import com.example.U4_S6_L5_progetto.entity.Viaggio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneDTO {

    @NotNull(message = "il campo viaggio è obbligatorio")

    private long fk_viaggio;

    @NotNull(message = "il campo dipendente è obbligatorio")

    private long fk_dipendente;

    @NotNull(message = "il campo data è obbligatorio")

    private LocalDate data;

    @Size(max= 100, message = "nota preferenze troppo lunga (max = 100 caratteri)")
    private String noteDipendente;

}
