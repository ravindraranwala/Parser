package org.wallet.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Scans the file using a token obtained via provided
 * {@link SearchTokenExtractionStrategy}.
 * 
 * @author Ravindra Ranwala.
 *
 */
public class FileScanner {
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
	public void search(final String startDate, final Duration duration, final int threshold) {
		final Path logFile = Paths.get(this.sourceFilePath);
		try (Stream<String> stream = Files.lines(logFile)) {
			final Map<String, Long> entriesByIp = stream.filter(
					line -> line.contains(this.searchTokenExtractionStrategy.extractSearchToken(startDate, duration)))
					.collect(Collectors.groupingBy(l -> l.split("\\|")[1], Collectors.counting()));
			entriesByIp.entrySet().stream().filter(entry -> entry.getValue() > threshold).forEach(System.out::println);
		} catch (IOException e) {
			LOGGER.error("An ERROR occurred while reading the file.");
			throw new RuntimeException(e);
		}
	}
}
