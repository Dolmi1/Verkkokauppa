package com.verkkokauppa.verkkokauppa;

import java.util.HashMap;
import java.util.Map;

public class Ostoskori {
    private Map<Tuote, Integer> tuotteet = new HashMap<>();

    // Lisää tuote ostoskoriin
    public void lisaaTuote(Tuote tuote, int maara) {
        tuotteet.put(tuote, tuotteet.getOrDefault(tuote, 0) + maara);
    }

    // Poista tuote ostoskorista
    public void poistaTuote(Tuote tuote, int maara) {
        if (tuotteet.containsKey(tuote)) {
            int uusiMaara = tuotteet.get(tuote) - maara;
            if (uusiMaara <= 0) {
                tuotteet.remove(tuote);
            } else {
                tuotteet.put(tuote, uusiMaara);
            }
        }
    }

    // Tyhjennä ostoskori
    public void tyhjennaOstoskori() {
        tuotteet.clear();
    }

    // Hae ostoskorin tuotteet
    public Map<Tuote, Integer> getTuotteet() {
        return tuotteet;
    }
}
