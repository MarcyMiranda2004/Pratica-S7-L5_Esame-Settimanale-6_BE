package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.service;

import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.dto.PrenotazioneDto;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.exception.BadRequestException;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.exception.ResourceNotFoundException;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model.Evento;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model.Prenotazione;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model.User;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.repository.EventoRepository;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenRepo;
    @Autowired
    private EventoRepository evRepo;

    public Prenotazione prenota(PrenotazioneDto dto, User utente) {
        Evento e = evRepo.findById(dto.getEventoId()).orElseThrow(() -> new ResourceNotFoundException("Evento non trovato"));
        if (e.getPostiDisponibili() <= 0) {
            throw new BadRequestException("Nessun posto disponibile");
        }
        boolean exists = prenRepo.existsByUtenteIdAndEventoId(utente.getId(), e.getId());
        if (exists) {
            throw new BadRequestException("Hai già prenotato questo evento");
        }
        e.setPostiDisponibili(e.getPostiDisponibili() - 1);
        evRepo.save(e);

        Prenotazione p = new Prenotazione();
        p.setEvento(e);
        p.setUtente(utente);
        return prenRepo.save(p);
    }

    public List<Prenotazione> getByUser(User utente) {
        return prenRepo.findByUtenteId(utente.getId());
    }

    public void annulla(Long id, User utente) {
        Prenotazione p = prenRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prenotazione non trovata"));
        if (!p.getUtente().getId().equals(utente.getId())) {
            throw new BadRequestException("Non sei autorizzato a cancellare questa prenotazione");
        }
        Evento e = p.getEvento();
        e.setPostiDisponibili(e.getPostiDisponibili() + 1);
        evRepo.save(e);
        prenRepo.delete(p);
    }
}
