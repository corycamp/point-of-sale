package com.minipos.server.dto;

public class ForecastResponse {
    private String sku;
    private double avgDailySales;
    private int daysRemaining;
    private int suggestedReorder;

    public ForecastResponse() {}

    public ForecastResponse(String sku, double avgDailySales, int daysRemaining, int suggestedReorder) {
        this.sku = sku;
        this.avgDailySales = avgDailySales;
        this.daysRemaining = daysRemaining;
        this.suggestedReorder = suggestedReorder;
    }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public double getAvgDailySales() { return avgDailySales; }
    public void setAvgDailySales(double avgDailySales) { this.avgDailySales = avgDailySales; }
    public int getDaysRemaining() { return daysRemaining; }
    public void setDaysRemaining(int daysRemaining) { this.daysRemaining = daysRemaining; }
    public int getSuggestedReorder() { return suggestedReorder; }
    public void setSuggestedReorder(int suggestedReorder) { this.suggestedReorder = suggestedReorder; }
}
