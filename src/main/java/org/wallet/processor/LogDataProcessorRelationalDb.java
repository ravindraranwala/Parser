package org.wallet.processor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.wallet.AppConstants;
import org.wallet.dao.LogEntryDao;
import org.wallet.model.LogEntry;

public class LogDataProcessorRelationalDb implements LogDataProcessor {
	private final LogEntryDao logEntryDao;

	public LogDataProcessorRelationalDb() {
		super();
		this.logEntryDao = LogEntryDao.getInstance();
	}

	@Override
	public void processData(final String filePath) {
		final Path logFile = Paths.get(filePath);
		try (Stream<String> stream = Files.lines(logFile)) {
			final List<LogEntry> logEntries = stream.map(line -> {
				String[] tokens = line.split(AppConstants.SPLIT_PATTERN);
				return new LogEntry(tokens[0], tokens[1], tokens[2].replaceAll("\"", ""));
			}).collect(Collectors.toList());
			this.logEntryDao.saveLogEntried(logEntries);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
