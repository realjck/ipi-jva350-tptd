package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.ipi.jva350.model.Entreprise.estDansPlage;

class EntrepriseTest {

    LocalDate plageDebut;
    LocalDate plageFin;
    LocalDate dateAnterieure;
    LocalDate dateUlterieure;
    LocalDate dateIncluse;

    @BeforeEach
    public void setUpDate() {
        // Ce test de plage se réfère à des vérifications en dehors du temps POSIX
        plageDebut = LocalDate.of(1970, 1, 1);
        plageFin = LocalDate.of(2040, 2, 29);
        dateAnterieure = LocalDate.of(1969, 12, 31);
        dateUlterieure = LocalDate.of(2040,3,1);
        dateIncluse = LocalDate.of(2000,1,1);
    }

    @Test
    void testEstDansPlage() {

        Assertions.assertFalse(estDansPlage(dateAnterieure, plageDebut, plageFin),
                "ÉCHEC TEST DATE ANTÉRIEURE 31/12/1969");

        Assertions.assertFalse(estDansPlage(dateUlterieure, plageDebut, plageFin),
                "ÉCHEC TEST DATE ULTÉRIEURE 1/3/2040");

        Assertions.assertTrue(estDansPlage(dateIncluse, plageDebut, plageFin),
                "ÉCHEC TEST DATE INCLUSE 1/1/2000");

    }

}
