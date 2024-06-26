package com.verkkokauppa.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ostoskori")
public class OstoskoriController {

    private final OstoskoriRepository ostoskoriRepository;
    private final TuoteRepository tuoteRepository;

    @Autowired
    public OstoskoriController(OstoskoriRepository ostoskoriRepository, TuoteRepository tuoteRepository) {
        this.ostoskoriRepository = ostoskoriRepository;
        this.tuoteRepository = tuoteRepository;
    }

    @GetMapping
    public String haeKaikkiOstoskorit(Model model) {
        List<Ostoskori> ostoskoriList = ostoskoriRepository.findAll();
        model.addAttribute("ostoskoriList", ostoskoriList);
        return "ostoskori";
    }

    @PostMapping("/lisaa-tuote")
    public String lisaaTuoteOstoskoriin(@RequestParam Long tuoteId, Model model) {
        Tuote tuote = tuoteRepository.findById(tuoteId).orElse(null);
        if (tuote != null) {
            Ostoskori ostoskori = new Ostoskori();
            ostoskori.setTuoteNimi(tuote.getName());
            ostoskori.setTuoteHinta(tuote.getPrice());
            ostoskori.setTuoteMaara(1);
            ostoskori.setProductId(tuote.getId());
            ostoskoriRepository.save(ostoskori);
        }
        return "redirect:/ostoskori";
    }

    @PostMapping("/poista-tuote/{id}")
    public String poistaTuoteOstoskorista(@PathVariable Long id) {
        ostoskoriRepository.deleteById(id);
        return "redirect:/ostoskori";
    }

    @PostMapping("/osta-tuotteet")
public String ostaTuotteet(Model model) {
    List<Ostoskori> ostoskoriList = ostoskoriRepository.findAll();
    
    for (Ostoskori ostoskori : ostoskoriList) {
        Long productId = ostoskori.getProductId();
        
        if (productId != null) {
            Tuote tuote = tuoteRepository.findById(productId).orElse(null);
            
            if (tuote != null && tuote.getQuantity() >= ostoskori.getTuoteMaara()) {
                tuote.setQuantity(tuote.getQuantity() - ostoskori.getTuoteMaara());
                tuoteRepository.save(tuote);
            } else {
                continue;
            }
        } else {
            continue;
        }
        
        ostoskoriRepository.deleteById(ostoskori.getId());
    }
    return "redirect:/ostoskori";
}
}
