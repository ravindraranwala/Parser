package org.wallet.processor;

import java.util.List;

import org.wallet.dao.LogEntryDao;
import org.wallet.model.LogEntry;

public class LogDataProcessorRelationalDb implements LogDataProcessor {
	private final LogEntryDao logEntryDao;

	public LogDataProcessorRelationalDb() {
		super();
		this.logEntryDao = LogEntryDao.getInstance();
	}

	@Override
	public void processData(List<LogEntry> logEntries) {
		this.logEntryDao.saveLogEntried(logEntries);
	}
}
