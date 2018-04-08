package org.wallet;

import java.util.Arrays;
import java.util.List;

/**
 * Date and Time extraction is done based on the period character.
 * 
 * @author Ravindra Ranwala.
 *
 */
public class BasicSearchTokenExtractionStrategy implements SearchTokenExtractionStrategy {
	private final String delimiter;

	private static final String ESCAPE_CHAR = "\\";

	public BasicSearchTokenExtractionStrategy(String delimiter) {
		super();
		this.delimiter = delimiter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String extractSearchToken(String startTime, DurationEnum duration) {
		String searchToken = null;
		List<String> dateTimeTokens = Arrays.asList(startTime.split(ESCAPE_CHAR + delimiter));
		switch (duration) {
		case HOURLY:
			searchToken = dateTimeTokens.get(0) + " " + dateTimeTokens.get(1).split(":")[0];
			break;

		case DAILY:
			searchToken = dateTimeTokens.get(0);
		default:
			break;
		}
		return searchToken;
	}

}
