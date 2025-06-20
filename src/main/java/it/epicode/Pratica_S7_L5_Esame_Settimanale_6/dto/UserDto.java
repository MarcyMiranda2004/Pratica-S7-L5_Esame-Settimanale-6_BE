package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {
    @NotEmpty(message = "il nome non può essere vuoto")
    private String nome;
    @NotEmpty(message = "il cognome non può essere vuoto")
    private String cognome;
    @NotEmpty(message = "lo username non può essere vuoto")
    private String username;
    @NotEmpty(message = "la password non può essere vuota")
    private String password;
}
