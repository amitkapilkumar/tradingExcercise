package com.acme.mytrader.strategy;

import com.acme.mytrader.exception.InvalidPriceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TradingStrategyTest {
    @InjectMocks
    private TradingStrategy tradingStrategy;

    @Test
    public void testForIBMSecurity() {
        tradingStrategy.addExecuteInstruction("IBM", 55.00);
        tradingStrategy.initiateListener();
        tradingStrategy.runSimulation();
    }

    @Test
    public void testForMutipleSecurity() {
        tradingStrategy.addExecuteInstruction("IBM", 55.00);
        tradingStrategy.addExecuteInstruction("GA", 31.00);
        tradingStrategy.addExecuteInstruction("MS", 21.75);
        tradingStrategy.addExecuteInstruction("FB", 33.33);
        tradingStrategy.initiateListener();
        tradingStrategy.runSimulation();
    }

    @Test(expected = InvalidPriceException.class)
    public void testForInvalidPrice() {
        tradingStrategy.addExecuteInstruction("IBM", -55.00);
        tradingStrategy.initiateListener();
        tradingStrategy.runSimulation();
    }

    @Test
    public void testSameSecurityForMutipleTimes() {
        tradingStrategy.addExecuteInstruction("IBM", 55.00);
        tradingStrategy.addExecuteInstruction("IBM", 45.00);
        tradingStrategy.addExecuteInstruction("IBM", 35.00);
        tradingStrategy.initiateListener();
        tradingStrategy.runSimulation();
    }

}
