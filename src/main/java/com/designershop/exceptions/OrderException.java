package com.designershop.exceptions;

import com.designershop.enums.ErrorSourceEunm;
import lombok.Getter;
import org.apache.logging.log4j.Level;

@Getter
public class OrderException extends Exception implements IBaseException {

    protected String[] customMessages;

    public OrderException(String message) {
        super(message);
    }

    public OrderException(Throwable cause, String message) {
        super(message, cause);
    }

    public OrderException(Throwable cause, String message, String... customMessages) {
        super(message, cause);
        this.customMessages = customMessages;
    }

    @Override
    public String getSystemName() {
        return ErrorSourceEunm.O.name();
    }

    @Override
    public Level getLogLevel() {
        return Level.INFO;
    }
}