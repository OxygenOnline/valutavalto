package hu.unideb.inf.service;

/**
 * interface that provides a currency converting method.
 */
public interface ExchangeRateService {

    double convertCurrency(String from, String to, double amount);
}
