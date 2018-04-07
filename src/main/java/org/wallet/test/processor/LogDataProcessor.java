package org.wallet.test.processor;

import java.util.List;

import org.wallet.test.model.LogEntry;

public interface LogDataProcessor {
	void processData(List<LogEntry> logEntries);
}
