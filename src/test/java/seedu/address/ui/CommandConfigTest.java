package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

public class CommandConfigTest {

    @Test
    public void getAllCommands_returnsNonNullList() {
        List<CommandUsageInfo> commands = CommandConfig.getAllCommands();
        assertNotNull(commands, "Command list should not be null");
    }

    @Test
    public void getAllCommands_returnsCorrectNumberOfCommands() {
        List<CommandUsageInfo> commands = CommandConfig.getAllCommands();
        // There are 10 commands defined in CommandConfig.getAllCommands()
        assertEquals(10, commands.size(), "Expected 10 commands in the list");
    }

    @Test
    public void getAllCommands_containsExpectedCommandInfo() {
        List<CommandUsageInfo> commands = CommandConfig.getAllCommands();

        // Check the first command (the 'add' command)
        CommandUsageInfo addCommand = commands.get(0);
        String expectedCommand = "add - add a person to the address book";
        String expectedFormat = "add n/NAME p/PHONE e/EMAIL a/ADDRESS [t/TAG] [tt/TUITION TIME]";
        String expectedExample = "add n/John Doe p/98765432 e/johnd@example.com "
                + "a/311 Clementi Ave 2 t/math t/science tt/Monday, 1000 - 1200";

        assertEquals(expectedCommand, addCommand.command(), "Command name does not match");
        assertEquals(expectedFormat, addCommand.format(), "Command format does not match");

        // Normalize whitespace in the example to avoid minor spacing issues
        String normalizedExample = addCommand.example().replaceAll("\\s+", " ").trim();
        String normalizedExpected = expectedExample.replaceAll("\\s+", " ").trim();
        assertEquals(normalizedExpected, normalizedExample, "Command example does not match");
    }
}
