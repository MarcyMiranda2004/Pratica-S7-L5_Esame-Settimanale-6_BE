package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Prenotazione {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private User utente;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    private LocalDateTime orarioPrenotazione = LocalDateTime.now();
}
