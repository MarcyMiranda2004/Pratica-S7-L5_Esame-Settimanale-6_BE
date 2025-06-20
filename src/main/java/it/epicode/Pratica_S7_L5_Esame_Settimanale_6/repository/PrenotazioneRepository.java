package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.repository;

import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByUtenteId(Integer utenteId);
    boolean existsByUtenteIdAndEventoId(Integer userId, Long eventoId);
}
