package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;

class SalarieAideADomicileTest {
    @Test
    void testALegalementDroitADesCongesPayesTrue() {
        // Given
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Robertus",
                LocalDate.now(),
                LocalDate.now(),
                20,
                11,
                10,
                3,
                6
        );
        // When
        boolean result = monSalarie.aLegalementDroitADesCongesPayes();
        // Then
        Assertions.assertEquals(true, result, "Teste ");
    }
    @Test
    void testALegalementDroitADesCongesPayesFalse() {
        // Given
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Robertus",
                LocalDate.now(),
                LocalDate.now(),
                20,
                2.5,
                6,
                9,
                8
        );
        // When
        boolean result = monSalarie.aLegalementDroitADesCongesPayes();
        // Then
        Assertions.assertEquals(false, result);
    }
    @Test
    void testEstHabituellementTravailleTrue() {
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        LocalDate jour = LocalDate.of(2024, 2,19);
        // When
        boolean result = salarie.estHabituellementTravaille(jour);
        // Then
        Assertions.assertEquals(true, result);
    }
    @Test
     void testEstHabituellementTravailleFalse() {
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        LocalDate jour = LocalDate.of(2024, 2,18);
        // When
        boolean result = salarie.estHabituellementTravaille(jour);
        // Then
        Assertions.assertEquals(false, result);
    }

    @ParameterizedTest
    @CsvSource({
            "'2023-12-17', '2023-12-28', 9",
            "'2023-12-17', '2024-01-08', 17"
    })
    void calculeJoursDeCongeDecomptesPourPlage(String dateDebut, String dateFin, int expectedResult){
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile(
                "Robert",
                LocalDate.of(2023,6,28),
                LocalDate.now(),
                20,
                2.5,
                9,
                1,
                8
        );
        // When
        LinkedHashSet<LocalDate> result = salarie.calculeJoursDeCongeDecomptesPourPlage(
                LocalDate.parse(dateDebut),
                LocalDate.parse(dateFin)
        );
        // Then
        Assertions.assertEquals(expectedResult, result.size());
    }
}
