package com.verkkokauppa.verkkokauppa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ThymeleafTuoteController {

    private final ProductService productService;

    public ThymeleafTuoteController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/tuoteSivu/{id}")
    public String naytaTuote(@PathVariable Long id, Model model) {
        Tuote tuote = productService.findProductById(id)
                                   .orElseThrow(() -> new IllegalArgumentException("Tuotetta ei löydy ID:llä: " + id));
        model.addAttribute("tuote", tuote);
        return "tuoteSivu"; 
    }
}
