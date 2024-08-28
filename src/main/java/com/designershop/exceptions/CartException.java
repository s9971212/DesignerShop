package com.designershop.exceptions;

import com.designershop.enums.ErrorSourceEunm;
import lombok.Getter;
import org.apache.logging.log4j.Level;

@Getter
public class CartException extends Exception implements IBaseException {

    protected String[] customMessages;

    public CartException(String message) {
        super(message);
    }

    public CartException(Throwable cause, String message) {
        super(message, cause);
    }

    public CartException(Throwable cause, String message, String... customMessages) {
        super(message, cause);
        this.customMessages = customMessages;
    }

    @Override
    public String getSystemName() {
        return ErrorSourceEunm.C.name();
    }

    @Override
    public Level getLogLevel() {
        return Level.INFO;
    }
}