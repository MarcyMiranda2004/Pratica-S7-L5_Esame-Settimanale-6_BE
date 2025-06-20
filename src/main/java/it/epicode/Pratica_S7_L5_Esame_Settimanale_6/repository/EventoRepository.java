package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.repository;

import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByPostiDisponibiliGreaterThan(int minPosti);
}
