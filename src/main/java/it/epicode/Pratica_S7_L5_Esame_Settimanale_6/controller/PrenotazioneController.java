package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.controller;

import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.dto.PrenotazioneDto;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model.Prenotazione;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model.User;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenService;

    @PostMapping
    public Prenotazione prenota(@RequestBody PrenotazioneDto dto, @AuthenticationPrincipal User user) {
        return prenService.prenota(dto, user);
    }

    @GetMapping
    public List<Prenotazione> mie(@AuthenticationPrincipal User user) {
        return prenService.getByUser(user);
    }

    @DeleteMapping("/{id}")
    public void annulla(@PathVariable Long id, @AuthenticationPrincipal User user) {
        prenService.annulla(id, user);
    }
}
