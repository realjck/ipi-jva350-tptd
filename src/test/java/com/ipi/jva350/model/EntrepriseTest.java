package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static com.ipi.jva350.model.Entreprise.*;

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

    /**
     * Vérifie si les dates définies sont incluses ou non dans la plage ci-dessus :
     */
    @Test
    void testEstDansPlage() {

        Assertions.assertFalse(estDansPlage(dateAnterieure, plageDebut, plageFin),
                "Échec test date antérieure 31/12/1969");

        Assertions.assertFalse(estDansPlage(dateUlterieure, plageDebut, plageFin),
                "Échec test data ultérieure 1/3/2040");

        Assertions.assertTrue(estDansPlage(dateIncluse, plageDebut, plageFin),
                "Échec test data incluse 1/1/2000");

    }


    //********************************************** //
    //******* Test de la méthode estJourFerie ****** //
    //********************************************** //
    // (Alvin Kita)

    /**
     * Test parametré de la méthode estJourFerie
     * @param jour Le jour à utiliser dans la méthode pour savoir s'il est ferié (Pour les jours de pâques, je modifie pour être sur le lundi)
     * @param expectedResult résultat booléen attendu
     */
    @ParameterizedTest(name = "Test de la méthode estJourFerie sur la date {0}, résultat attendu {1}")
    @CsvSource({
            "'2016-03-28', 'true'",
            "'2024-04-01', 'true'",
            "'2024-01-01', 'true'",
            "'2024-07-14', 'true'",
            "'2024-01-05', 'false'",
            "'2024-12-12', 'false'"
    })
    void testEstJourFerie(String jour, String expectedResult) {

        /*
        Given :
        Transformation du résultat attendu en boolean
         */

        boolean expectedResultFinal = expectedResult.equals("true");

        /*
        When :
        Récupération du retour de la méthode
         */

        boolean result = estJourFerie(LocalDate.parse(jour));

        /*
        Then :
        Test du résultat attendu
         */

        Assertions.assertEquals(expectedResultFinal, result);

    }


    //************************************************************* //
    //******* Test de la méthode getPremierJourAnneeDeConges *******//
    //************************************************************* //
    // (Alvin Kita)

    /**
     * Affiche le premier jour de congé de l'année n actuelle
     * @param jour Le jour à tester
     * @param expectedResult Resultat attendu
     */
    @ParameterizedTest(name = "Test de la méthode getPremierJourAnneeDeConges sur la date {0}, résultat attendu {1}")
    @CsvSource({
            "'2024-04-01', '2023-06-01'",
            "'2024-05-01', '2023-06-01'",
            "'2024-05-31', '2023-06-01'",
            "'2024-06-01', '2024-06-01'",
            "'2025-01-12', '2024-06-01'",
            "'2025-01-12', '2024-06-01'"
    })
    void testGetPremierJourAnneeDeConges(String jour, String expectedResult) {

        /*
        Given :
         */

        /*
        When :
        Récupération du retour de la méthode
         */

        LocalDate result = getPremierJourAnneeDeConges(LocalDate.parse(jour));

        /*
        Then :
        Test du résultat attendu
         */

        Assertions.assertEquals(expectedResult, result.toString());

    }

    @Test
    void testGetPremierJourAnneeDeCongesNull() {
        // Utilisation de la syntaxe fluent (Given, When, Then sur une seule ligne)
        // afin de ne pas causer d'alerte IDE :
        Assertions.assertNull(getPremierJourAnneeDeConges(null));
    }

}
