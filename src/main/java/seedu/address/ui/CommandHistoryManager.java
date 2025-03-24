package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the history of user-entered commands, enabling navigation through
 * previously entered commands using UP and DOWN arrow keys.
 * Also preserves in-progress user input when navigating history.
 */
public class CommandHistoryManager {
    /** Stores the full list of commands entered by the user. */
    private final List<String> history = new ArrayList<>();

    /**
     * The current index in the history list.
     * This points to the next command to display when navigating.
     */
    private int index = 0;

    /**
     * Stores the temporary input that the user was typing before navigating
     * the history. This is restored when the user navigates back down.
     */
    private String tempInput = null;

    /**
     * Adds a command to the history and resets the index and temporary input.
     *
     * @param command the user-entered command to store
     */
    public void add(String command) {
        history.add(command);
        index = history.size();
        tempInput = null;
    }

    /**
     * Returns the previous command in history, moving the index backward.
     * On first call, it preserves the current user input so it can be restored later.
     *
     * @param currentInput the current text in the command box before pressing UP
     * @return the previous command if available; null if already at the top
     */
    public String getPrevious(String currentInput) {
        if (tempInput == null) {
            tempInput = currentInput;
        }
        if (index > 0) {
            index--;
        }
        if (history.isEmpty()) {
            return null;
        }
        return history.get(index);
    }

    /**
     * Returns the next command in history, moving the index forward.
     * If at the end of the history, returns the preserved input or empty string.
     *
     * @return the next command or original input; empty string if no input
     */
    public String getNext() {
        // Don't do anything if user hasn't pressed UP before
        if (tempInput == null && index == history.size()) {
            return null; // signal that nothing should happen
        }

        if (index < history.size() - 1) {
            index++;
            return history.get(index);
        } else if (index == history.size() - 1) {
            index++;
            return tempInput != null ? tempInput : "";
        }
        return tempInput != null ? tempInput : "";
    }
}
