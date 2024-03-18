package com.verkkokauppa.verkkokauppa;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Tuote addProduct(Tuote product);

    List<Tuote> findAllProducts();

    Optional<Tuote> findProductById(Long id);

    Tuote updateProduct(Long id, Tuote product);

    boolean deleteProduct(Long id);
}
