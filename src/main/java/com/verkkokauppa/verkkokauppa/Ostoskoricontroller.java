package com.verkkokauppa.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ostoskori")
// OstoskoriController
public class OstoskoriController {

    private final OstoskoriRepository ostoskoriRepository;

    @Autowired
    public OstoskoriController(OstoskoriRepository ostoskoriRepository) {
        this.ostoskoriRepository = ostoskoriRepository;
    }

    // Hae kaikki ostoskorit
    @GetMapping
    public List<Ostoskori> haeKaikkiOstoskorit() {
        return ostoskoriRepository.findAll();
    }

    // Hae tietty ostoskori
    @GetMapping("/{id}")
    public Ostoskori haeOstoskori(@PathVariable Long id) {
        return ostoskoriRepository.findById(id).orElse(null);
    }

    // Lisää uusi ostoskori
    @PostMapping
    public Ostoskori lisaaOstoskori(@RequestBody Ostoskori ostoskori) {
        return ostoskoriRepository.save(ostoskori);
    }

    // Päivitä olemassa oleva ostoskori
    @PutMapping("/{id}")
    public Ostoskori paivitaOstoskori(@PathVariable Long id, @RequestBody Ostoskori uusiOstoskori) {
        return ostoskoriRepository.findById(id)
                .map(ostoskori -> {
                    ostoskori.setTuoteNimi(uusiOstoskori.getTuoteNimi());
                    ostoskori.setTuoteHinta(uusiOstoskori.getTuoteHinta());
                    ostoskori.setTuoteMaara(uusiOstoskori.getTuoteMaara());
                    return ostoskoriRepository.save(ostoskori);
                })
                .orElse(null);
    }

    // Poista olemassa oleva ostoskori
    @DeleteMapping("/{id}")
    public void poistaOstoskori(@PathVariable Long id) {
        ostoskoriRepository.deleteById(id);
    }
}
