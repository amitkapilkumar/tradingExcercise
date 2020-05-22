package com.acme.mytrader.price;

import com.acme.mytrader.model.Price;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PriceListenerImpl implements PriceListener {
    private Map<String, Price> priceMap;

    public PriceListenerImpl() {
        priceMap = new ConcurrentHashMap<>();
    }

    @Override
    public void priceUpdate(String security, double price) {
        if(!priceMap.containsKey(security)) {
            Price priceModel = new Price();
            priceModel.setCurrentPrice(price);
            priceModel.setSpecifiedPrice(price);
            priceMap.put(security, priceModel);
        }
        else {
            Price priceModel = priceMap.get(security);
            priceModel.setCurrentPrice(price);
        }
    }

    public Map<String, Price> getPriceMap() {
        return priceMap;
    }

    @Override
    public double getCurrentPrice(String security) {
        return priceMap.get(security).getCurrentPrice();
    }

}
