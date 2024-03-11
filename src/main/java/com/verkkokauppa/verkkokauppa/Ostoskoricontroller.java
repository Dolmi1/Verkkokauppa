package com.verkkokauppa.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

public class Ostoskoricontroller {
    private ProductService productService;

    @Autowired
    private Ostoskorirepository ostoskorirepository;

    // Lisää tuote ostoskoriin
    @PostMapping("/{tuoteId}/lisaa")
    public void lisaaTuoteOstoskoriin(@PathVariable String tuoteId, @RequestParam int maara) {
        // Etsi tuote tietokannasta
        long productId = Long.parseLong(tuoteId);
        Tuote tuote = productService.findProductById(productId).orElse(null);

        if (tuote != null) {
            // Lisää tuote ostoskoriin
            Ostoskori ostoskori = new Ostoskori();
            ostoskori.lisaaTuote(tuote, maara);

            // Tallenna ostoskori tietokantaan
            ostoskorirepository.save(ostoskori);
        }
    }

    // Poista tuote ostoskorista
    @DeleteMapping("/{tuoteId}/poista")
    public void poistaTuoteOstoskorista(@PathVariable String tuoteId, @RequestParam int maara) {
        // Etsi tuote tietokannasta
        long productId = Long.parseLong(tuoteId);
        Tuote tuote = productService.findProductById(productId).orElse(null);

        if (tuote != null) {
            // Poista tuote ostoskorista
            Ostoskori ostoskori = new Ostoskori();
            ostoskori.poistaTuote(tuote, maara);

            // Tallenna ostoskori tietokantaan
            ostoskorirepository.save(ostoskori);
        }
    }

    // Hae ostoskori
    @GetMapping("/{id}")
    public Ostoskori haeOstoskori(@PathVariable String id) {
        long ostoskoriId = Long.parseLong(id);
        return ostoskorirepository.findById(ostoskoriId).orElse(null);
    }
}