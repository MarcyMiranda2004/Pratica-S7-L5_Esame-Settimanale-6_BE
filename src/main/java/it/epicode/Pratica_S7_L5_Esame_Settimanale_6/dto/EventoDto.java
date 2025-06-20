package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoDto {
    private String titolo;
    private String descrizione;
    private LocalDate dataEvento;
    private String luogo;
    private int postiDisponibili;
}
