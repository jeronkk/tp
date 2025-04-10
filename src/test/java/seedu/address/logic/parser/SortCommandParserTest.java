package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {

    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validInputNameAsc_returnsSortCommand() throws Exception {
        SortCommand expected = new SortCommand("name", true);
        assertParseSuccess(parser, "name asc", expected);
    }


    @Test
    public void parse_validInputEmailDesc_returnsSortCommand() throws Exception {
        SortCommand expected = new SortCommand("email", false);
        assertParseSuccess(parser, "email desc", expected);
    }

    @Test
    public void parse_validInputWithExtraSpaces_returnsSortCommand() throws Exception {
        SortCommand expected = new SortCommand("address", true);
        assertParseSuccess(parser, "   address    asc   ", expected);
    }

    @Test
    public void parse_missingField_throwsParseException() {
        assertParseFailure(parser, "   ", "Invalid sort field. Valid fields: name, phone, "
                + "email, address, tuition, tag");
    }

    @Test
    public void parse_missingDirection_throwsParseException() {
        assertParseSuccess(parser, "name", new SortCommand("name", true));
    }

    @Test
    public void parse_invalidDirection_throwsParseException() {
        assertParseFailure(parser, "name upwards", "Invalid sort order. Use 'asc' or 'desc'.");
    }

    @Test
    public void parse_caseInsensitiveInput_returnsSortCommand() throws Exception {
        SortCommand expected = new SortCommand("email", false);
        assertParseSuccess(parser, "By EMAIL Desc", expected);
    }

    @Test
    public void parse_validInputWithBy_returnsSortCommand() throws Exception {
        SortCommand expected = new SortCommand("phone", true);
        assertParseSuccess(parser, "by phone", expected);
    }

    @Test
    public void parse_invalidDirectionWithBy_throwsParseException() {
        assertParseFailure(parser, "by name up", "Invalid sort order. Use 'asc' or 'desc'.");
    }

    @Test
    public void parse_invalidFieldFormat_throwsParseException() {
        assertParseFailure(parser, "sort name descending fast", "Invalid sort format. Try: sort by name desc");
    }
}
