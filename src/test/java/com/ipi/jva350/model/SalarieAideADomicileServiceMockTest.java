package com.ipi.jva350.model;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import com.ipi.jva350.service.SalarieAideADomicileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.LinkedHashSet;

@ExtendWith(MockitoExtension.class)
public class SalarieAideADomicileServiceMockTest {

    @Mock
    private SalarieAideADomicileRepository salarieAideADomicileRepository;
    @InjectMocks
    private SalarieAideADomicileService salarieService = new SalarieAideADomicileService();

    // mocké :
    @Test
    public void testAjouteConge() throws SalarieException {
        // Given :
        SalarieAideADomicile monSalarie = new SalarieAideADomicile("Paul",
                LocalDate.of(2022, 6, 28), LocalDate.of(2023, 11, 1),
                9, 2.5,
                80, 20, 8);
        // When :
        salarieService.ajouteConge(monSalarie, LocalDate.of(2024, 12, 17),
                LocalDate.of(2024, 12, 18));
        // Then :
        ArgumentCaptor<SalarieAideADomicile> salarieAideADomicileCaptor = ArgumentCaptor.forClass(SalarieAideADomicile.class);
        Mockito.verify(salarieAideADomicileRepository, Mockito.times(1)).save(salarieAideADomicileCaptor.capture()); // arg capture !
        Assertions.assertEquals(1L, salarieAideADomicileCaptor.getValue().getCongesPayesPrisAnneeNMoins1());
    }

}
