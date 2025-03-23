package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.model.person.TagsContainKeywordPredicate;

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
        String input = "math";
        ListCommand expectedCommand = new ListCommand(
                new TagsContainKeywordPredicate("math"),
                "Listed all persons with subject ",
                "math"
        );
        assertParseSuccess(parser, input, expectedCommand);
    }

    @Test
    public void parse_invalidTuitionTimeArg_throwsParseException() {
        String input = " tt/Funday";
        assertParseFailure(parser, input, INVALID_TT_FORMAT);
    }
}
