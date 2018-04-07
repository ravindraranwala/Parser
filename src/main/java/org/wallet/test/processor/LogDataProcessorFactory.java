package org.wallet.test.processor;

public class LogDataProcessorFactory {
	private static LogDataProcessorFactory instance;

	private LogDataProcessorFactory() {

	}

	public static final LogDataProcessorFactory getInstance() {
		if (instance == null) {
			instance = new LogDataProcessorFactory();
		}
		return instance;
	}

	public LogDataProcessor makeLogDataProcessor() {
		/*
		 * Can create different data processor implementations based on runtime config
		 * or property.
		 */
		return new LogDataProcessorRelationalDb();
	}
}
