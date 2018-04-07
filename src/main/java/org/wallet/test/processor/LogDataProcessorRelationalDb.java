package org.wallet.test.processor;

import java.util.List;

import org.wallet.test.model.LogEntry;
import org.wallet.test.repository.LogEntryDao;

public class LogDataProcessorRelationalDb implements LogDataProcessor {
	private final LogEntryDao logEntryDao;

	public LogDataProcessorRelationalDb() {
		super();
		this.logEntryDao = new LogEntryDao();
	}

	@Override
	public void processData(List<LogEntry> logEntries) {
		this.logEntryDao.saveLogEntried(logEntries);
	}
}
