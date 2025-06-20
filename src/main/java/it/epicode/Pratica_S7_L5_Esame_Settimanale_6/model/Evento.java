package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;
    private String descrizione;
    private LocalDate dataEvento;
    private String luogo;
    private int postiDisponibili;

    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    private User organizzatore;
}
