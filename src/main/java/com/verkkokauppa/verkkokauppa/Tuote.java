package com.verkkokauppa.verkkokauppa;

import javax.persistence.Column;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.AbstractPersistable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tuote extends AbstractPersistable<Long>{

    /*Tuotetiedot*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false) // Tämä rivi määrittelee productId-kentän pakolliseksi
    private Long productId; // Changed to Long

    private String name;

    private double price;

    private int quantity;

}
