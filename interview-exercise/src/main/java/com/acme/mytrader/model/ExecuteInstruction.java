package com.acme.mytrader.model;

public class ExecuteInstruction {
    private String security;
    private double specifiedPrice;

    public ExecuteInstruction(String security, double specifiedPrice) {
        this.security = security;
        this.specifiedPrice = specifiedPrice;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public double getSpecifiedPrice() {
        return specifiedPrice;
    }

    public void setSpecifiedPrice(double specifiedPrice) {
        this.specifiedPrice = specifiedPrice;
    }
}
