package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the history of user-entered commands, enabling navigation through
 * previously entered commands using UP and DOWN arrow keys.
 * Also preserves in-progress user input when navigating history.
 */
public class CommandHistoryManager {

    private final List<String> history = new ArrayList<>();
    /**
     * index points to the \"current\" slot:
     *  - 0..(history.size()-1): points to actual history entries
     *  - history.size(): the \"blank\" slot for new input
     */
    private int index = 0;

    /**
     * The partial input the user was typing before they first pressed UP.
     * Restored if the user navigates back DOWN to the blank slot.
     */
    private String tempInput = null;

    /**
     * Adds a new executed command to the history, resetting index to the blank slot.
     * Also clears any previously stored temp input.
     * @param command the command to add
     */
    public void add(String command) {
        history.add(command);
        // Move index to blank slot (just past the new last entry)
        index = history.size();
        tempInput = null;
    }

    /**
     * Moves to the previous command (pressing UP).
     * If currently in the blank slot, stores the user's in-progress input (if not stored yet).
     *
     * @param currentInput the current text in the command box before pressing UP
     * @return the previous command to display, or null if history is empty
     */
    public String getPrevious(String currentInput) {
        if (history.isEmpty()) {
            return null; // no history at all
        }

        // If at blank slot, store partial input for later restoration
        if (index == history.size() && tempInput == null) {
            tempInput = currentInput;
        }

        // Move index up unless at the very first command
        if (index > 0) {
            index--;
        }

        // Return the command at the new index
        return history.get(index);
    }

    /**
     * Moves to the next command (pressing DOWN).
     * If we reach the blank slot, restores tempInput if any.
     *
     * @return the next command or the restored in-progress input if at blank slot
     */
    public String getNext() {
        if (history.isEmpty()) {
            return null;
        }

        // If we're already at the blank slot, do nothing
        if (index == history.size()) {
            // User is already at blank slot: no change
            // The test expects us to remain with the \"tempInput\" if any,
            // but we typically return null to signal \"no change\".
            return tempInput != null ? tempInput : "";
        }

        // Move index down
        index++;

        // If we have returned to the blank slot
        if (index == history.size()) {
            // Restore temp input if present
            return tempInput != null ? tempInput : "";
        }

        // Otherwise, we are at a real history entry
        return history.get(index);
    }
}
