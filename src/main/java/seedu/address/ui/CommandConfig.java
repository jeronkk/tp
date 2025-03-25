package seedu.address.ui;

import java.util.Arrays;
import java.util.List;

/**
 * Stores all command information for the help window.
 */
public class CommandConfig {
    public static List<CommandUsageInfo> getAllCommands() {
        return Arrays.asList(
                new CommandUsageInfo(
                        "add - add a person to the address book",
                        "add n/NAME p/PHONE e/EMAIL a/ADDRESS [t/TAG] [tt/TUITION TIME]",
                        "add n/John Doe p/98765432 e/johnd@example.com a/311 Clementi Ave 2 "
                                + "t/math t/science tt/Monday, 1000 - 1200"
                ),
                new CommandUsageInfo(
                        "clear - clear all contact details",
                        "clear",
                        "clear"
                ),
                new CommandUsageInfo(
                        "delete - delete a person from the address book",
                        "delete INDEX",
                        "delete 3"
                ),
                new CommandUsageInfo(
                        "edit - edit a person's contact details to the address book",
                        "edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG] [tt/TUITION TIME]",
                        "edit 2 n/Betsy Crower e/betsycrower@example.com"
                ),
                new CommandUsageInfo(
                        "find - find a person from the address book by his/her name",
                        "find KEYWORD [MORE_KEYWORDS]",
                        "find Alice Bob"
                ),
                new CommandUsageInfo(
                        "list - list all contacts",
                        "list",
                        "list"
                ),
                new CommandUsageInfo(
                        "list by subject - list all contacts with the particular tag (subject)",
                        "list t/[TAG]",
                        "list t/Math"
                ),
                new CommandUsageInfo(
                        "list by tuition time - list all contacts with tuition on a particular day (Monday - Sunday)",
                        "list tt/[TUITION TIME]",
                        "list tt/Monday or list tt/mon"
                ),
                new CommandUsageInfo(
                        "sort - Sort all contacts according to a field entered by user (ascending by default)",
                        "sort [FIELD] asc/desc",
                        "sort name asc or sort phone desc"
                ),
                new CommandUsageInfo(
                        "help - list all commands with explanation",
                        "help",
                        "help"
                )
        );
    }
}
