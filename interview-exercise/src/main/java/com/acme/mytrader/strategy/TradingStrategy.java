package com.acme.mytrader.strategy;

import com.acme.mytrader.exception.InvalidPriceException;
import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.execution.ExecutionServiceImpl;
import com.acme.mytrader.model.ExecuteInstruction;
import com.acme.mytrader.model.Price;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceListenerImpl;
import com.acme.mytrader.price.PriceSource;
import com.acme.mytrader.price.PriceSourceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {

    private ExecutionService executionService;
    private PriceSource priceSource;
    private List<ExecuteInstruction> instructions;

    public TradingStrategy() {
        priceSource = new PriceSourceImpl();
        executionService = new ExecutionServiceImpl();
        instructions = new ArrayList<>();
    }


    public void addExecuteInstruction(String security, double specifiedPrice) {
        if(specifiedPrice < 0.01) {
            throw new InvalidPriceException("Invalid price less than 0");
        }
        instructions.add(new ExecuteInstruction(security, specifiedPrice));
    }

    public void initiateListener() {
        PriceListener listener = new PriceListenerImpl();
        for(ExecuteInstruction ei : instructions) {
            listener.priceUpdate(ei.getSecurity(), ei.getSpecifiedPrice());
        }
        priceSource.addPriceListener(listener);
    }

    public void runSimulation() {
        while (!instructions.isEmpty()) {
            priceSource.simulatePriceChange();
            PriceListener listener = priceSource.getPriceListener();
            for(Map.Entry<String, Price> e : listener.getPriceMap().entrySet()) {
                findAndRemove(e.getKey(), e.getValue().getCurrentPrice());
            }
        }
    }

    private void findAndRemove(String security, double price) {
        List<ExecuteInstruction> list = new ArrayList<>();
        for(ExecuteInstruction  ei: instructions) {
            if(ei.getSecurity().equals(security) && ei.getSpecifiedPrice() > price) {
                list.add(ei);
                executionService.buy(ei.getSecurity(), price, 100);
            }
        }

        instructions.removeAll(list);
    }

}
