package org.wallet.processor;

import java.util.List;

import org.wallet.model.LogEntry;

public interface LogDataProcessor {
	void processData(List<LogEntry> logEntries);
}
