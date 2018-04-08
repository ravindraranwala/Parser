package org.wallet;

import java.time.Duration;
import java.time.Instant;

public class App {
	public static void main(String[] args) {
		// SearchTokenExtractionStrategy tokenExtraction = new
		// BasicSearchTokenExtractionStrategy();
		// System.out.println(tokenExtraction.extractSearchToken("2017-01-01.13:00:00",
		// ".", Duration.HOURLY));
		Instant start = Instant.now();
		new FileScanner(new BasicSearchTokenExtractionStrategy("."), "./src/main/resources/access.log")
				.search("2017-01-01.15:00:00", DurationEnum.HOURLY, 200);
		Instant end = Instant.now();
		System.out.println("Execution time in msec: " + Duration.between(start, end).toMillis());
	}
}
