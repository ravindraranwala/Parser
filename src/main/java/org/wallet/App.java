package org.wallet;

import java.time.Duration;
import java.time.Instant;

public class App {
	public static void main(String[] args) {
		final Instant start = Instant.now();
		new FileScanner(new BasicSearchTokenExtractionStrategy("."), "./src/main/resources/access.log")
				.search("2017-01-01.00:00:00", DurationEnum.DAILY, 500);
		final Instant end = Instant.now();
		System.out.println("Execution time in msec: " + Duration.between(start, end).toMillis());
	}
}
