package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.controller;

import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.dto.EventoDto;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model.Evento;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model.User;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Evento crea(@RequestBody EventoDto dto, @AuthenticationPrincipal User user) {
        return eventoService.createEvento(dto, user);
    }

    @PutMapping("/{id}")
    public Evento aggiorna(@PathVariable Long id, @RequestBody EventoDto dto, @AuthenticationPrincipal User user) {
        return eventoService.updateEvento(id, dto, user);
    }

    @DeleteMapping("/{id}")
    public void elimina(@PathVariable Long id, @AuthenticationPrincipal User user) {
        eventoService.deleteEvento(id, user);
    }
}
