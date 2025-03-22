package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TUITION_TIME;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TagsContainKeywordPredicate;
import seedu.address.model.person.TuitionTimeContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    private static final String MESSAGE_SUCCESS_1 = "Listed all persons with subject ";
    private static final String MESSAGE_SUCCESS_2 = "Listed all persons with tuition time on ";
    private static final String INVALID_TT_FORMAT =
            "Invalid day entered. Please use a valid day like 'monday' or 'mon' (case-insensitive).";

    /**
     * Parses the given user input and returns a ListCommand.
     * The command can either list all entries, filter by tuition time,
     * or filter by a tag keyword depending on the user's input.
     *
     * @param args the input arguments after the command word
     * @return a ListCommand based on the parsed input
     * @throws ParseException if the input format is invalid
     */
    public ListCommand parse(String args) throws ParseException {
        String keyword = args.trim();
        if (keyword.isEmpty()) {
            return new ListCommand();
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TUITION_TIME);

        if (argMultimap.getValue(PREFIX_TUITION_TIME).isPresent()) {
            return parseTuitionTime(keyword, argMultimap);
        }

        return new ListCommand(new TagsContainKeywordPredicate(keyword), MESSAGE_SUCCESS_1, keyword);
    }

    /**
     * Parses and validates the tuition time input, and returns a ListCommand
     * that filters entries based on the given tuition day keyword.
     *
     * @param keyword the raw input keyword string
     * @param argMultimap the tokenized input arguments containing the tuition time
     * @return a ListCommand that filters by tuition time
     * @throws ParseException if the tuition time keyword is invalid
     */
    private ListCommand parseTuitionTime(String keyword, ArgumentMultimap argMultimap) throws ParseException {
        keyword = argMultimap.getValue(PREFIX_TUITION_TIME).get().trim();
        String standardisedTime = DayMapping.map(keyword.toLowerCase());

        if (standardisedTime == null) {
            throw new ParseException(INVALID_TT_FORMAT);
        }

        return new ListCommand(new TuitionTimeContainsKeywordPredicate(keyword), MESSAGE_SUCCESS_2, standardisedTime);
    }


}
