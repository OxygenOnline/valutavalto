package hu.unideb.inf.service;

import hu.unideb.inf.model.FixerLatestCurrencyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * A service implementation that handles acquiring currency rates, and converting between multiple currencies.
 * Uses the <a href="https://fixer.io">fixer.io</a> api
 */
@Service
public class FixerServiceImpl implements FixerService {

    private final WebClient webClient;
    private final String apiKey;

    /**
     * Create a new <a href="#{@link}">{@link FixerServiceImpl}</a>
     * @param baseUrl the base url of the <a href="https://fixer.io">fixer</a> api
     * @param apiKey the fixer api key
     */
    public FixerServiceImpl(@Value(value = "${fixer.base-url}") String baseUrl, @Value(value = "${fixer.api-key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.apiKey = apiKey;
    }

    private Map<String, Double> getCurrencyRates(Optional<String> symbols) {
        var uriBuilder = UriComponentsBuilder.fromPath("/latest")
                .queryParam("access_key", apiKey)
                .queryParamIfPresent("symbols", symbols)
                .queryParam("format", 1);

        var responseMono = webClient.get()
                .uri(uriBuilder.build().toUriString())
                .retrieve()
                .bodyToMono(FixerLatestCurrencyResponse.class);

        return responseMono.map(FixerLatestCurrencyResponse::getRates).block();
    }

    /**
     * @param toCurrency The 3-letter code of the target currency
     * @return the exchange rate between a euro and <i>toCurrency</i>
     */
    @Override
    public double latestCurrencyRate(String toCurrency) {
        return getCurrencyRates(Optional.of(toCurrency)).get(toCurrency);
    }

    private String convertListToString(List<String> toCurrencies) {
        var joiner = new StringJoiner(",");
        for (var currency : toCurrencies) {
            joiner.add(currency);
        }
        return joiner.toString();
    }

    /**
     * @param toCurrencies A list of 3-letter codes of currencies
     * @return A map with the exchange rates from euro to each currency.
     */
    @Override
    public Map<String, Double> multipleLatestCurrencyRates(List<String> toCurrencies) {
        var currencies = convertListToString(toCurrencies);
        return getCurrencyRates(Optional.of(currencies));
    }
}
