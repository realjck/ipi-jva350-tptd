package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;

class SalarieAideADomicileTest {
    SalarieAideADomicile salarieTest;


    /**
     * Salarié à initialiser avant chaque test
     */
    @BeforeEach
    public void setUpSalarie() {
        salarieTest = new SalarieAideADomicile(
                "🏹~Robin Dubois~",
                LocalDate.of(2038,1,20),
                LocalDate.now(),
                254,
                30,
                126,
                15,
                8
        );
    }


    /**
     * Test de la méthode ALégalementDroitADesCongésPayes().
     * D'après la documentation les critères d'admissions sont :
     * - Avoir travaillé pendant au moins 10 jours (Pas forcément de suite)
     * - Avoir un jour de congé N-1
     */
    @Test
    void testALegalementDroitADesCongesPayesTrue() {
        // Given : Mise en place de l'environnement de test
        // ⇾ Le salarie est initialisé avec le @BeforeEach

        // When : Test
        boolean auMoinsDixJoursTravaille = salarieTest.aLegalementDroitADesCongesPayes(); // True si nbCongés >= 10
        boolean auMoinsUnJoursDansLeSoldeConges = salarieTest.getCongesPayesRestantAnneeNMoins1() > 0; // True si au moins 1 jour de congé

        // Then : Vérifie que le salarié a le droit à des congés payés
        Assertions.assertTrue(auMoinsDixJoursTravaille, "Le nombre de jours travaillé \"" + salarieTest.getJoursTravaillesAnneeNMoins1() + " jours\" n'est pas supérieur ou égal à 10.");
        Assertions.assertTrue(auMoinsUnJoursDansLeSoldeConges, "Aucun jours de congés dans le solde N-1.");
    }

    /**
     * Test de la méthode estHabituellementTravaille() à partir d'une date donnée
     */
    @Test
    void testEstHabituellementTravailleTrue() {
        // Given : mise en place de la date à tester
        // ⇾ Le salarie est initialisé avec le @BeforeEach
        LocalDate dateTest = LocalDate.of(2024,2,19); // ⇾ date de test

        // When : Test si le jour est habituellement travaillé
        boolean testEstHabituellementTravailleResult = salarieTest.estHabituellementTravaille(dateTest);

        // Then : Vérification
        Assertions.assertTrue(testEstHabituellementTravailleResult, dateTest.getDayOfWeek() + " n'est pas un jours habituelement travaillé.");
    }


    /**
     * Test paramétré de calculeJoursDeCongeDecomptesPourPlage()
     * @param dateDebut Début de la plage de test
     * @param dateFin Fin de la plage de test
     * @param expectedResult Résultat attendu pour calculeJoursDeCongeDecomptesPourPlage(dateDebut, dateFin)
     */
    @ParameterizedTest(name = "Plage {0} à {1}, résultat attendu : {2}")
    @CsvSource({
            "'2023-12-17', '2023-12-28', 9",
            "'2023-12-17', '2024-01-08', 17"
    })
    void calculeJoursDeCongeDecomptesPourPlage(String dateDebut, String dateFin, int expectedResult){
        // Given
        // ⇾ Le salarie est initialisé avec le @BeforeEach

        // When
        LinkedHashSet<LocalDate> result = salarieTest.calculeJoursDeCongeDecomptesPourPlage(
                LocalDate.parse(dateDebut),
                LocalDate.parse(dateFin)
        );
        // Then
        Assertions.assertEquals(expectedResult, result.size());
    }

}
