package com.acme.mytrader.exception;

public class InvalidPriceException extends RuntimeException {
    private static final long serialVersionUID = -2453438090995210952L;

    public InvalidPriceException(String msg) {
        super(msg);
    }
}
