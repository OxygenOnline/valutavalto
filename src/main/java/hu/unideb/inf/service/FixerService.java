package hu.unideb.inf.service;

import java.util.Map;

public interface FixerService {

    double latestCurrencyRate(String toCurrency);
    Map<String, Double> allLatestCurrencyRates();
}
