package hu.unideb.inf.service;

import hu.unideb.inf.model.FixerLatestCurrencyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;


@Service
public class FixerServiceImpl implements FixerService {

    private final WebClient webClient;
    private final String apiKey;

    public FixerServiceImpl(@Value(value = "${fixer.base-url}") String baseUrl, @Value(value = "${fixer.api-key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.apiKey = apiKey;
    }

    public Map<String, Double> allLatestCurrencyRates() {

        var responseMono = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/latest")
                        .queryParam("access_key", apiKey)
                        .queryParam("format", 1)
                        .build())
                .retrieve()
                .bodyToMono(FixerLatestCurrencyResponse.class);

        return responseMono.map(FixerLatestCurrencyResponse::getRates).block();
    }

    public double latestCurrencyRate(String toCurrency) {
        var responseMono = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/latest")
                        .queryParam("access_key", apiKey)
                        .queryParam("symbols", toCurrency)
                        .queryParam("format", 1)
                        .build())
                .retrieve()
                .bodyToMono(FixerLatestCurrencyResponse.class);

        return responseMono.map(response -> response.getRates().get(toCurrency)).block();
    }
}
