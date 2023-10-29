package hu.unideb.inf.service;

import org.springframework.beans.factory.annotation.Autowired;

public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    FixerService fixerService;


    @Override
    public double convertCurrency(String from, String to, double amount) {
        if(from.equals("EUR")){
            return fixerService.latestCurrencyRate(to)*amount;
        }
        throw new IllegalArgumentException("Conversion is only available from EUR");
    }
}
