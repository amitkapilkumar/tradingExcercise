package com.acme.mytrader.execution;

public class ExecutionServiceImpl implements ExecutionService {

    @Override
    public void buy(String security, double price, int volume) {
        System.out.println(security + " : Order for buy executed at price " + price);
    }

    @Override
    public void sell(String security, double price, int volume) {
        System.out.println(security + " : Order for sell executed at price " + price);
    }
}
