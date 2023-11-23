package hu.unideb.inf.service;

import java.util.List;
import java.util.Map;

/**
 * Interface for the <a href="https://fixer.io">fixer.io</a> api
 */
public interface FixerService {

    double latestCurrencyRate(String toCurrency);
    Map<String, Double> multipleLatestCurrencyRates(List<String> toCurrencies);
}
