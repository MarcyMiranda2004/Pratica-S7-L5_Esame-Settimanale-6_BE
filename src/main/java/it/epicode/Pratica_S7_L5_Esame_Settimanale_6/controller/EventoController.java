package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.controller;

import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.dto.EventoDto;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model.Evento;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model.User;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.repository.EventoRepository;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> tutti() {
        return eventoService.getAllDisponibili();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('EVENT_CREATOR')")
    public Evento crea(@RequestBody @Valid EventoDto dto, @AuthenticationPrincipal User user) {
        return eventoService.createEvento(dto, user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EVENT_CREATOR')")
    public Evento aggiorna(@PathVariable Long id, @RequestBody @Valid EventoDto dto, @AuthenticationPrincipal User user) {
        return eventoService.updateEvento(id, dto, user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EVENT_CREATOR')")
    public void elimina(@PathVariable Long id, @AuthenticationPrincipal User user) {
        eventoService.deleteEvento(id, user);
    }
}
