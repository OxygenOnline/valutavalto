package hu.unideb.inf.model;

import java.util.Map;

public class FixerLatestCurrencyResponse {
    private boolean success;
    private long timestamp;
    private String base;
    private String date;
    private Map<String, Double> rates;

    public FixerLatestCurrencyResponse() {

    }

    public FixerLatestCurrencyResponse(boolean success, long timestamp, String base, String date, Map<String, Double> rates) {
        this.success = success;
        this.timestamp = timestamp;
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public boolean isSuccess() {
        return success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}
