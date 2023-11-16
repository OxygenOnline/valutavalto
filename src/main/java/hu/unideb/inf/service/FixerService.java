package hu.unideb.inf.service;

import java.util.List;
import java.util.Map;


public interface FixerService {

    double latestCurrencyRate(String toCurrency);
    Map<String, Double> multipleLatestCurrencyRates(List<String> toCurrencies);
    Map<String, Double> allLatestCurrencyRates();
}
