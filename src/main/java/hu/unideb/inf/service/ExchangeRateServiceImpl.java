package hu.unideb.inf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    FixerService fixerService;

    @Override
    public double convertCurrency(String from, String to, double amount) {
        if (from.equals("EUR")) {
            return fixerService.latestCurrencyRate(to) * amount;
        }
        var currencies = fixerService.multipleLatestCurrencyRates(List.of(from, to));
        return 1 / currencies.get(from) * currencies.get(to) * amount;
    }
}
