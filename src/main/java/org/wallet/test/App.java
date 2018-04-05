package org.wallet.test;

public class App {
	public static void main(String[] args) {
		// SearchTokenExtractionStrategy tokenExtraction = new
		// BasicSearchTokenExtractionStrategy();
		// System.out.println(tokenExtraction.extractSearchToken("2017-01-01.13:00:00",
		// ".", Duration.HOURLY));
		new FileScanner(new BasicSearchTokenExtractionStrategy("."), "./src/main/resources/access.log")
				.search("2017-01-01.15:00:00", Duration.HOURLY, 200);
	}
}
