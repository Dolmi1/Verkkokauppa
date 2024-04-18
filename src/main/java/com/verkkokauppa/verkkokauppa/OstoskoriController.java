package com.verkkokauppa.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ostoskori")
public class OstoskoriController {

    private final OstoskoriRepository ostoskoriRepository;
    private final TuoteRepository tuoteRepository; // Lisätty tuoterepo

    @Autowired
    public OstoskoriController(OstoskoriRepository ostoskoriRepository, TuoteRepository tuoteRepository) {
        this.ostoskoriRepository = ostoskoriRepository;
        this.tuoteRepository = tuoteRepository;
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

    // Lisää tuote ostoskoriin
    @PostMapping("/lisaa-tuote")
    public Ostoskori lisaaTuoteOstoskoriin(@RequestParam Long tuoteId) {
        Tuote tuote = tuoteRepository.findById(tuoteId).orElse(null); // Etsi tuote tietokannasta
        if (tuote != null) {
            Ostoskori ostoskori = new Ostoskori();
            ostoskori.setTuoteNimi(tuote.getName());
            ostoskori.setTuoteHinta(tuote.getPrice());
            ostoskori.setTuoteMaara(1); // Oletetaan, että lisätään yksi kappale tuotetta
            return ostoskoriRepository.save(ostoskori);
        }
        return null; // Palauta null, jos tuotetta ei löydy
    }
}
