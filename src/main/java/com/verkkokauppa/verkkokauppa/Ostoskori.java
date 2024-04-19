package com.verkkokauppa.verkkokauppa;
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

public class Ostoskori extends AbstractPersistable<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ostoskori_id;
    private String TuoteNimi;
    private double TuoteHinta;
    private int TuoteMaara;
    private Long productId;

}
