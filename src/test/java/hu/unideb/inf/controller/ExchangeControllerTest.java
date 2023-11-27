package hu.unideb.inf.controller;

import hu.unideb.inf.service.ExchangeRateService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class ExchangeControllerTest {

    ExchangeRateService exchangeRateService = mock(ExchangeRateService.class);

    ExchangeRateController underTest = new ExchangeRateController(exchangeRateService);

    @Test
    void testConvertShouldReturnZeroWhenAmountIsZero() {
        when(exchangeRateService.convertCurrency("USD","HUF",0))
                .thenReturn(0.0);

        ResponseEntity<Double> expected = new ResponseEntity<>(0.0, HttpStatus.OK);
        ResponseEntity<Double> actual = underTest.convert("USD","HUF",0);


        assertEquals(expected,actual);
        verify(exchangeRateService).convertCurrency("USD","HUF",0);
    }

    @Test
    void testConvertShouldReturnCorrectResultIfArgumentsAreValid() {
        when(exchangeRateService.convertCurrency("USD","HUF",1))
                .thenReturn(347.0);

        ResponseEntity<Double> expected = new ResponseEntity<>(347.0, HttpStatus.OK);
        ResponseEntity<Double> actual = underTest.convert("USD","HUF",1);


        assertEquals(expected,actual);
        verify(exchangeRateService).convertCurrency("USD","HUF",1);
    }

    @Test
    void testCovertShouldReturnCorrectResultWhenConvertingFromEur() {
        when(exchangeRateService.convertCurrency("EUR","HUF",1))
                .thenReturn(379.0);

        ResponseEntity<Double> expected = new ResponseEntity<>(379.0, HttpStatus.OK);
        ResponseEntity<Double> actual = underTest.convert("EUR","HUF",1);


        assertEquals(expected,actual);
        verify(exchangeRateService).convertCurrency("EUR","HUF",1);
    }

}
