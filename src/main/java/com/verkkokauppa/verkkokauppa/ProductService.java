package com.verkkokauppa.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final TuoteRepository tuoteRepository;

    @Autowired
    public ProductService(TuoteRepository tuoteRepository) {
        this.tuoteRepository = tuoteRepository;
    }

    @SuppressWarnings("null")
    public Tuote addProduct(Tuote product) {
        return tuoteRepository.save(product);
    }

    public List<Tuote> findAllProducts() {
        return tuoteRepository.findAll();
    }

    @SuppressWarnings("null")
    public Tuote updateProduct(long id, Tuote product) {
        // Implement update logic, checking for existence, etc.
        return tuoteRepository.save(product);
    }

    @SuppressWarnings("null")
    public boolean deleteProduct(Long id) {
        tuoteRepository.deleteById(id);
        return true;
    }

    public Optional<Tuote> findProductById(long id) {
        return tuoteRepository.findById(id);
    }
}
