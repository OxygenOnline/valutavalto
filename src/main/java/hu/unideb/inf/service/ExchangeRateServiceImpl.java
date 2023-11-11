package hu.unideb.inf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    FixerService fixerService;


    @Override
    public double convertCurrency(String from, String to, double amount) {
        if (from.equals("EUR")){
            return fixerService.latestCurrencyRate(to) * amount;
        }
        else{
            return 1/fixerService.latestCurrencyRate(from) * fixerService.latestCurrencyRate(to) * amount;
        }
    }
}
