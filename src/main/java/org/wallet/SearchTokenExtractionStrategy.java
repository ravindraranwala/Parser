package org.wallet;

/**
 * Defines the contract to extract a search token from a given starting time.
 * 
 * @author Ravindra Ranwala.
 *
 */
public interface SearchTokenExtractionStrategy {
	/**
	 * Extracts the search token to search over a source data based off of given
	 * input values.
	 * 
	 * @param startTime
	 *            Start Time as a string
	 * @param duration
	 *            duration enumeration, this can be hours, days etc.
	 * @return search String to scan the source file.
	 */
	String extractSearchToken(String startTime, DurationEnum duration);
}
