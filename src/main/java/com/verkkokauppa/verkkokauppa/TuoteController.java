package com.verkkokauppa.verkkokauppa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/products") // Base URL for all operations related to products
public class TuoteController {

    private final ProductService productService;

    @Autowired
    public TuoteController(ProductService productService) {
        this.productService = productService;
    }

    // Endpoint to add a new product
    @PostMapping
    public ResponseEntity<Tuote> addProduct(@RequestBody Tuote product) {
        Tuote newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    // Endpoint to get all products
    @GetMapping
    public ResponseEntity<List<Tuote>> getAllProducts() {
        List<Tuote> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tuote> getProductById(@PathVariable Long id) {
        Optional<Tuote> productOptional = productService.findProductById(id);
        
        if (productOptional.isPresent()) {
            return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    // Endpoint to update a product
    @PutMapping("/{id}")
    public ResponseEntity<Tuote> updateProduct(@PathVariable Long id, @RequestBody Tuote product) {
        Tuote updatedProduct = productService.updateProduct(id, product);
        return updatedProduct != null ? new ResponseEntity<>(updatedProduct, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Additional endpoints as needed
}
