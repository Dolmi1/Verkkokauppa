package com.verkkokauppa.verkkokauppa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TuoteRepository extends JpaRepository<Tuote, Long> {
    // Additional query methods can be defined here
}
