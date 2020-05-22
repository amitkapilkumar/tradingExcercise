package com.acme.mytrader.price;

import com.acme.mytrader.model.Price;

import java.util.Map;
import java.util.Random;

public class PriceSourceImpl implements PriceSource {
    private PriceListener priceListener;
    private Random random;

    public PriceSourceImpl() {
        random = new Random();
    }

    @Override
    public void addPriceListener(PriceListener listener) {
        priceListener = listener;
    }

    @Override
    public void removePriceListener() {
        priceListener = null;
    }

    @Override
    public PriceListener getPriceListener() {
        return priceListener;
    }

    @Override
    public void simulatePriceChange() {
        if(priceListener == null) {
            return;
        }

        for(Map.Entry<String, Price> entry : priceListener.getPriceMap().entrySet()) {
            entry.getValue().setCurrentPrice(getRandomPrice(entry.getValue().getCurrentPrice()));
        }
    }

    private double getRandomPrice(double price) {
        double d = random.nextDouble();
        if(d == 1.0 || d == 0.0) {
            return price;
        }

        if(d <= 0.2) {
            return price + price * d;
        }

        if((price - price * d) > 0) {
            return price - price * d;
        }

        return price;
    }


}
