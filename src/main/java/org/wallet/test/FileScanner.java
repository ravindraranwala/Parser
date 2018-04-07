package org.wallet.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wallet.test.model.LogEntry;
import org.wallet.test.processor.LogDataProcessor;
import org.wallet.test.processor.LogDataProcessorFactory;

/**
 * Scans the file using a token obtained via provided
 * {@link SearchTokenExtractionStrategy}.
 * 
 * @author Ravindra Ranwala.
 *
 */
public class FileScanner {
	private static final String SPLIT_PATTERN = "\\|";

	private static Logger LOGGER = LoggerFactory.getLogger(FileScanner.class);

	private final SearchTokenExtractionStrategy searchTokenExtractionStrategy;

	private final String sourceFilePath;

	public FileScanner(SearchTokenExtractionStrategy searchTokenExtractionStrategy, String sourceFile) {
		super();
		this.searchTokenExtractionStrategy = searchTokenExtractionStrategy;
		this.sourceFilePath = sourceFile;
	}

	/**
	 * Searches the file based on given criteria.
	 * 
	 * @param startDate
	 *            starting date time to begin the search.
	 * @param duration
	 *            duration against which the search is conducted.
	 * @param threshold
	 *            minimum number of occurrences expected.
	 */
	public void search(final String startDate, final DurationEnum duration, final int threshold) {
		final LogDataProcessor logDataProcessor = LogDataProcessorFactory.getInstance().makeLogDataProcessor();
		final List<LogEntry> logEntries = new ArrayList<>();
		final String searchToken = this.searchTokenExtractionStrategy.extractSearchToken(startDate, duration);
		final Path logFile = Paths.get(this.sourceFilePath);
		try (Stream<String> stream = Files.lines(logFile)) {
			final Map<String, Long> entriesByIp = stream.filter(line -> {
				String[] split = line.split(SPLIT_PATTERN);
				// Collecting log entries for saving here.
				logEntries.add(new LogEntry(split[0], split[1], split[2].replaceAll("\"", "")));
				return line.contains(searchToken);
			}).collect(Collectors.groupingBy(l -> l.split(SPLIT_PATTERN)[1], Collectors.counting()));
			entriesByIp.entrySet().stream().filter(entry -> entry.getValue() > threshold).forEach(System.out::println);
			logDataProcessor.processData(logEntries);
		} catch (IOException e) {
			LOGGER.error("An ERROR occurred while reading the file.", e);
			throw new RuntimeException(e);
		}
	}
}
