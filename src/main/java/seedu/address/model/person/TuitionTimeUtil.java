package seedu.address.model.person;

/**
 * Utility methods for parsing and comparing tuition time values.
 */
public class TuitionTimeUtil {

    /**
     * Converts a tuition time string into a sortable key based on day order and start time.
     * E.g. "Tuesday 1800-2000" → "2_1800"
     *
     * @param value The tuition time string.
     * @return A sortable key for comparing tuition times.
     */
    public static String getSortKey(String value) {
        String[] parts = value.trim().split(",");
        if (parts.length != 2) {
            return value.toLowerCase(); // fallback
        }

        String day = parts[0].trim().toLowerCase();
        String timeRange = parts[1].trim();

        // Get start time (before dash)
        String startTime = timeRange.split("-")[0].trim();

        int dayOrder = switch (day) {
        case "monday" -> 1;
        case "tuesday" -> 2;
        case "wednesday" -> 3;
        case "thursday" -> 4;
        case "friday" -> 5;
        case "saturday" -> 6;
        case "sunday" -> 7;
        default -> 8; // unknown day
        };

        return String.format("%d_%s", dayOrder, startTime);
    }
}
