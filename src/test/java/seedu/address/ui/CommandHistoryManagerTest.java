package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandHistoryManagerTest {

    private CommandHistoryManager historyManager;

    @BeforeEach
    public void setUp() {
        historyManager = new CommandHistoryManager();
    }

    @Test
    public void getPrevious_emptyHistory_returnsNull() {
        String result = historyManager.getPrevious("typing something");
        assertNull(result);
    }

    @Test
    public void add_andNavigateHistory_correctOrder() {
        historyManager.add("list");
        historyManager.add("add John");
        historyManager.add("delete 1");

        // Simulate that user is typing something before pressing UP for the first time
        String dummyCurrentInput = "some in-progress typing";

        // Navigate up
        assertEquals("delete 1", historyManager.getPrevious(dummyCurrentInput));
        assertEquals("add John", historyManager.getPrevious(""));
        assertEquals("list", historyManager.getPrevious(""));

        // Cannot go further up
        assertEquals("list", historyManager.getPrevious(""));

        // Navigate down
        assertEquals("add John", historyManager.getNext());
        assertEquals("delete 1", historyManager.getNext());

        // Reaches temp input (restores what was typed before)
        assertEquals(dummyCurrentInput, historyManager.getNext());

        // Cannot go further down
        assertEquals(dummyCurrentInput, historyManager.getNext());
    }

    @Test
    public void navigateHistory_preservesTempInput() {
        historyManager.add("list");

        // Start typing a new command
        String inProgress = "add Alice";

        // Navigate up to access history
        assertEquals("list", historyManager.getPrevious(inProgress));

        // Navigate down should return back to in-progress input
        assertEquals(inProgress, historyManager.getNext());
    }

    @Test
    public void addCommand_resetsTempInput() {
        historyManager.getPrevious("temp input");
        historyManager.add("new command");

        // Should clear previous tempInput
        assertEquals("new command", historyManager.getPrevious(""));
        assertEquals("", historyManager.getNext());
    }
}
