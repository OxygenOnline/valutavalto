package hu.unideb.inf.service;

public interface ExchangeRateService {

    double convertCurrency(String from, String to, double amount);
}
