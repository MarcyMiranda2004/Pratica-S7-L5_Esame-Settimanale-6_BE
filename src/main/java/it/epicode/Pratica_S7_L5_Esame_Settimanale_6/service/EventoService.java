package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.service;

import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.dto.EventoDto;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.exception.ResourceNotFoundException;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model.Evento;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model.User;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepo;

    public Evento createEvento(EventoDto dto, User organizzatore) {
        Evento e = new Evento();
        e.setTitolo(dto.getTitolo());
        e.setDescrizione(dto.getDescrizione());
        e.setDataEvento(dto.getDataEvento());
        e.setLuogo(dto.getLuogo());
        e.setPostiDisponibili(dto.getPostiDisponibili());
        e.setOrganizzatore(organizzatore);
        return eventoRepo.save(e);
    }

    public List<Evento> getAllDisponibili() {
        return eventoRepo.findByPostiDisponibiliGreaterThan(0);
    }

    public Evento updateEvento(Long id, EventoDto dto, User organizzatore) {
        Evento e = eventoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Evento non trovato"));
        if (!e.getOrganizzatore().getId().equals(organizzatore.getId())) {
            throw new ResourceNotFoundException("Non sei organizzatore di questo evento");
        }
        e.setTitolo(dto.getTitolo());
        e.setDescrizione(dto.getDescrizione());
        e.setDataEvento(dto.getDataEvento());
        e.setLuogo(dto.getLuogo());
        e.setPostiDisponibili(dto.getPostiDisponibili());
        return eventoRepo.save(e);
    }

    public void deleteEvento(Long id, User organizzatore) {
        Evento e = eventoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Evento non trovato"));
        if (!e.getOrganizzatore().getId().equals(organizzatore.getId())) {
            throw new ResourceNotFoundException("Non sei organizzatore di questo evento");
        }
        eventoRepo.delete(e);
    }

    public Evento getById(Long id) {
        return eventoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Evento non trovato"));
    }
}
