package com.verkkokauppa.verkkokauppa;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ProductCommands {

    private final ProductService productService;

    public ProductCommands(ProductService productService) {
        this.productService = productService;
    }

    @ShellMethod("Add a new product")
    public String addProduct(
            @ShellOption(help = "Product name") String name,
            @ShellOption(help = "Product price") double price,
            @ShellOption(help = "Product quantity") int quantity) {

        Tuote product = new Tuote();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        productService.addProduct(product);
        return String.format("Added new product: %s", name);
    }
}
