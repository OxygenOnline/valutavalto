package hu.unideb.inf.controller;

import hu.unideb.inf.service.ExchangeRateService;
import hu.unideb.inf.service.FixerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exchangerates")
public class ExchangeRateController {

    @Autowired
    FixerService fixerService;
    ExchangeRateService exchangeRateService;

    @GetMapping("/currencycodes")
    public List<String> getSupportedCurrencies() {
        return List.of(
                "EUR",
                "USD",
                "HUF"
        );
    }

    @GetMapping("/latest/fromEUR")
    public double getRateEUR(@RequestParam() String to) {
        return fixerService.latestCurrencyRate(to);
    }

    @GetMapping("/latest/convert")
    public ResponseEntity<Double> Convert(@RequestParam() String from,
                          @RequestParam() String to,
                          @RequestParam(required = false, defaultValue = "1") double amount) {
        if(from.equals("EUR"))
            return new ResponseEntity<Double>(exchangeRateService.convertCurrency(from,to,amount), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/latest/ratemap")
    public ResponseEntity<Map<String, Double>> getRateMap(@RequestParam(required = false, defaultValue = "EUR") String base) {
        if(base.equals("EUR"))
            return new ResponseEntity<>(
                    Map.of("USD", getRateEUR("USD"),
                            "HUF", getRateEUR("HUF")),
                    HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }
}