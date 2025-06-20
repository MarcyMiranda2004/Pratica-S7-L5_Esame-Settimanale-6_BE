package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Evento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;
    @Column(length = 1000)
    private String descrizione;
    private LocalDate dataEvento;
    private String luogo;
    private int postiDisponibili;

    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    private User organizzatore;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.REMOVE)
    private List<Prenotazione> prenotazioni;
}
