package com.verkkokauppa.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final TuoteRepository tuoteRepository;

    @Autowired
    public ProductServiceImpl(TuoteRepository tuoteRepository) {
        this.tuoteRepository = tuoteRepository;
    }

    @Override
    public Tuote addProduct(Tuote product) {
        return tuoteRepository.save(product);
    }

    @Override
    public List<Tuote> findAllProducts() {
        return tuoteRepository.findAll();
    }

    @Override
    public Optional<Tuote> findProductById(Long id) {
        return tuoteRepository.findById(id);
    }

    @Override
    public Tuote updateProduct(Long id, Tuote product) {
        return tuoteRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setQuantity(product.getQuantity());
                    return tuoteRepository.save(existingProduct);
                }).orElse(null);
    }

    @Override
    public boolean deleteProduct(Long id) {
        if (tuoteRepository.existsById(id)) {
            tuoteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
