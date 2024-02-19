package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class SalarieAideADomicileTest {
    @Test
    public void  testALegalementDroitADesCongesPayesTrue() {
        // Given
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Robertus",
                LocalDate.now(),
                LocalDate.now(),
                20,
                10,
                15,
                3,
                6
        );
        // When
        boolean result = monSalarie.aLegalementDroitADesCongesPayes();
        // Then
        Assertions.assertEquals(true, result, "Teste ");
    }
    @Test
    public void  testALegalementDroitADesCongesPayesFalse() {
        // Given
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Robertus",
                LocalDate.now(),
                LocalDate.now(),
                20,
                2.5,
                6,
                15,
                8
        );
        // When
        boolean result = monSalarie.aLegalementDroitADesCongesPayes();
        // Then
        Assertions.assertEquals(false, result);
    }
    @Test
    public void testEstHabituellementTravailleTrue() {
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        LocalDate jour = LocalDate.of(2024, 2,19);
        // When
        boolean result = salarie.estHabituellementTravaille(jour);
        // Then
        Assertions.assertEquals(true, result);
    }
    @Test
    public void testEstHabituellementTravailleFalse() {
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        LocalDate jour = LocalDate.of(2024, 2,18);
        // When
        boolean result = salarie.estHabituellementTravaille(jour);
        // Then
        Assertions.assertEquals(false, result);
    }
}
