package com.verkkokauppa.verkkokauppa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TuoteRepository extends JpaRepository<Tuote, Long> {
    // Voit lisätä omia erityisiä kyselyjä tarvittaessa
}
