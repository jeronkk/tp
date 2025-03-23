package seedu.address.logic.parser;

import java.util.Map;

/**
 * A utility class that provides mapping and normalization of day-of-week strings.
 * Supports both full names (e.g., "monday") and short forms (e.g., "mon"),
 * and returns the standardized, capitalized version of the day.
 */
public class DayMapping {

    private static final Map<String, String> NORMALIZED_DAYS = Map.ofEntries(
            Map.entry("monday", "Monday"),
            Map.entry("tuesday", "Tuesday"),
            Map.entry("wednesday", "Wednesday"),
            Map.entry("thursday", "Thursday"),
            Map.entry("friday", "Friday"),
            Map.entry("saturday", "Saturday"),
            Map.entry("sunday", "Sunday"),
            Map.entry("mon", "Monday"),
            Map.entry("tue", "Tuesday"),
            Map.entry("wed", "Wednesday"),
            Map.entry("thu", "Thursday"),
            Map.entry("fri", "Friday"),
            Map.entry("sat", "Saturday"),
            Map.entry("sun", "Sunday")
    );

    /**
     * Returns the standardized full name of the day based on the given input.
     * Accepts both full names and 3-letter abbreviations, case-insensitive.
     *
     * @param arg the day string to normalize (e.g., "mon", "Monday", "MON")
     * @return the properly capitalized full day name (e.g., "Monday"),
     *         or {@code null} if the input is not a valid day abbreviation or name
     */
    public static String map(String arg) {
        String day = arg.toLowerCase();
        return NORMALIZED_DAYS.get(day);
    }
}
