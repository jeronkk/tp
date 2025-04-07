package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_TT_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MISSING_KEYWORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.model.person.TagsContainKeywordPredicate;
import seedu.address.model.person.TuitionTimeContainsKeywordPredicate;

public class ListCommandParserTest {

    private static final String INVALID_TT_FORMAT =
            "Invalid day entered. Please use a valid day like 'monday' or 'mon' (case-insensitive).";
    private final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg_returnsListAllCommand() {
        assertParseSuccess(parser, "   ", new ListCommand());
    }

    @Test
    public void parse_validTagArg_returnsTagFilteredListCommand() {
        String input = " t/math";
        ListCommand expectedCommand = new ListCommand(
                new TagsContainKeywordPredicate("math"),
                "Listed all persons with subject ",
                "math"
        );
        assertParseSuccess(parser, input, expectedCommand);
    }

    @Test
    public void parse_validTuitionTime_returnsTuitionTimeFilteredListCommand() {
        String input = " tt/Monday";
        ListCommand expectedCommand = new ListCommand(
                new TuitionTimeContainsKeywordPredicate("Monday"),
                "Listed all persons with tuition time on ",
                "Monday"
        );
        assertParseSuccess(parser, input, expectedCommand);
    }

    @Test
    public void parse_validFullTuitionTime_noThrow() {
        String input = " tt/Monday, 1000-1200";
        assertDoesNotThrow(() -> parser.parse(input));
    }

    @Test
    public void parse_validTuitionTimeAndTag_noThrow() {
        String input = " tt/Monday t/Math";
        assertDoesNotThrow(() -> parser.parse(input));
    }

    @Test
    public void parse_invalidTuitionTimeArg_throwsParseException() {
        String input = " tt/Funday";
        String expectedMessage = String.format(MESSAGE_INVALID_TT_FORMAT, ListCommand.MESSAGE_USAGE);
        assertParseFailure(parser, input, expectedMessage);
    }

    @Test
    public void parse_missingFieldPrefix_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " math", expectedMessage);
    }

    @Test
    public void parse_emptyTagField_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_MISSING_KEYWORD, ListCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " t/ ", expectedMessage);
    }

    @Test
    public void parse_emptyTuitionTimeField_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_MISSING_KEYWORD, ListCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " tt/ ", expectedMessage);
    }
}
