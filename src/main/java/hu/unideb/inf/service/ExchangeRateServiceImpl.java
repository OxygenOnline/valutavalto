package hu.unideb.inf.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A service that provides currency conversion based on the <a href="https://fixer.io">fixer.io</a> api
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final FixerService fixerService;

    /**
     * Create a new <a href="#{@link}">{@link ExchangeRateServiceImpl}</a>
     * @param fixerService the fixerService implementation, that actually provides the currency rates
     */
    public ExchangeRateServiceImpl(FixerService fixerService) {
        this.fixerService = fixerService;
    }

    /**
     * Converts between currencies.
     * @param from A 3-letter code of a currency we want to convert
     * @param to The 3-letter code of the target currency
     * @param amount How much we want to convert
     * @return The converted amount measured in the target currency
     */
    @Override
    public double convertCurrency(String from, String to, double amount) {
        if (from.equals("EUR")) {
            return fixerService.latestCurrencyRate(to) * amount;
        }
        var currencies = fixerService.multipleLatestCurrencyRates(List.of(from, to));
        return 1 / currencies.get(from) * currencies.get(to) * amount;
    }
}
