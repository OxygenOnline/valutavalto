package hu.unideb.inf.controller;

import hu.unideb.inf.service.ExchangeRateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A controller that handles currency conversion related requests
 */
@RestController
@CrossOrigin
@RequestMapping("/exchangerates")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    /**
     * Create a new <a href="#{@link}">{@link ExchangeRateController}</a>
     * @param exchangeRateService the service that provides the currency rates and handles
     * the actual conversion
     */
    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    /**
     Convert between two currencies.
     @param from A 3-letter code of the currency (HUF, EUR) we want to convert
     @param to The 3-letter code of the target currency
     @param amount The amount we want to convert
     @return The converted amount in the target currency
     */
    @GetMapping("/latest/convert")
    public ResponseEntity<Double> convert(@RequestParam() String from,
                          @RequestParam() String to,
                          @RequestParam(required = false, defaultValue = "1") double amount) {
        return new ResponseEntity<>(exchangeRateService.convertCurrency(from, to, amount), HttpStatus.OK);
    }
}