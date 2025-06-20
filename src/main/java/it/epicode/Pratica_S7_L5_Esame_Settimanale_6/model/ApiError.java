package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private String message;
    private LocalDateTime errorDate;
}
