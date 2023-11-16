package hu.unideb.inf.controller;

import hu.unideb.inf.service.ExchangeRateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/exchangerates")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/latest/convert")
    public ResponseEntity<Double> convert(@RequestParam() String from,
                          @RequestParam() String to,
                          @RequestParam(required = false, defaultValue = "1") double amount) {
        return new ResponseEntity<>(exchangeRateService.convertCurrency(from, to, amount), HttpStatus.OK);
    }
}