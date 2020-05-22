package com.acme.mytrader.price;

import com.acme.mytrader.model.Price;

import java.util.Map;

public interface PriceListener {
    void priceUpdate(String security, double price);
    double getCurrentPrice(String security);
    Map<String, Price> getPriceMap();
}
