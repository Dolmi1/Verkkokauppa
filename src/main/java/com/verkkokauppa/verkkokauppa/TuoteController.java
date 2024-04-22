package com.verkkokauppa.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tuoteSivu")
public class TuoteController {

    private final ProductService productService;

    @Autowired
    public TuoteController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public String addProduct(@ModelAttribute("tuote") Tuote product) {
        productService.addProduct(product);
        return "redirect:/tuoteSivu";
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Tuote> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "tuoteSivu";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        Optional<Tuote> productOptional = productService.findProductById(id);

        if (productOptional.isPresent()) {
            model.addAttribute("product", productOptional.get());
            return "tuoteSivu";
        } else {
            return "notFound";
        }
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("tuote") Tuote product) {
        productService.updateProduct(id, product);
        return "redirect:/tuoteSivu";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/tuoteSivu";
    }
}
