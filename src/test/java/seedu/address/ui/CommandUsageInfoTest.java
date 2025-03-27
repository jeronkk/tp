package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandUsageInfoTest {

    @Test
    public void constructor_createsRecordWithValues() {
        // Given
        String command = "add";
        String format = "add n/NAME p/PHONE";
        String example = "add n/John p/91234567";

        // When
        CommandUsageInfo info = new CommandUsageInfo(command, format, example);

        // Then
        assertEquals(command, info.command(), "Command field should match constructor arg");
        assertEquals(format, info.format(), "Format field should match constructor arg");
        assertEquals(example, info.example(), "Example field should match constructor arg");
    }

    @Test
    public void record_immutableProperties() {
        // Once a record is created, its fields cannot be changed (immutability).
        CommandUsageInfo info = new CommandUsageInfo("delete", "delete INDEX", "delete 3");

        // We can only read the values, not modify them
        assertEquals("delete", info.command());
        assertEquals("delete INDEX", info.format());
        assertEquals("delete 3", info.example());
    }
}
