package com.designershop.exceptions;

import org.apache.logging.log4j.Level;

import com.designershop.enums.ErrorSourceEunm;

import lombok.Getter;

@Getter
public class ProductException extends Exception implements IBaseException {

	protected String[] customMessages;

	public ProductException(String message) {
		super(message);
	}

	public ProductException(Throwable cause, String message) {
		super(message, cause);
	}

	public ProductException(Throwable cause, String message, String... customMessages) {
		super(message, cause);
		this.customMessages = customMessages;
	}

	@Override
	public String getSystemName() {
		return ErrorSourceEunm.P.name();
	}

	@Override
	public Level getLogLevel() {
		return Level.INFO;
	}
}