package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {

    @NotEmpty(message = "lo username non può essere vuoto")
    private String username;
    @NotEmpty(message = "la password non può essere vuota")
    private String password;
}
