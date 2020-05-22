package com.acme.mytrader.model;

public class Price {
    private double specifiedPrice;
    private double currentPrice;

    public double getSpecifiedPrice() {
        return specifiedPrice;
    }

    public void setSpecifiedPrice(double specifiedPrice) {
        this.specifiedPrice = specifiedPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
