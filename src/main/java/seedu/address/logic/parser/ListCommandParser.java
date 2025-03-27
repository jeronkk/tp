package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_TT_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MISSING_KEYWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
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
        if (args.trim().isEmpty()) {
            return new ListCommand();
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TUITION_TIME, PREFIX_TAG);

        if (argMultimap.getValue(PREFIX_TUITION_TIME).isPresent()) {
            return parseTuitionTime(argMultimap);
        } else if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            return parseTags(argMultimap);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses and validates the tuition time input, and returns a ListCommand
     * that filters entries based on the given tuition day keyword.
     *
     * @param argMultimap the tokenized input arguments containing the tuition time
     * @return a ListCommand that filters by tuition time
     * @throws ParseException if the tuition time keyword is invalid
     */
    private ListCommand parseTuitionTime(ArgumentMultimap argMultimap) throws ParseException {
        String keyword = argMultimap.getValue(PREFIX_TUITION_TIME).get().trim();
        if (keyword.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_MISSING_KEYWORD, ListCommand.MESSAGE_USAGE));
        }

        String standardisedTime = DayMapping.map(keyword.toLowerCase());

        if (standardisedTime == null) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_TT_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        return new ListCommand(new TuitionTimeContainsKeywordPredicate(keyword), MESSAGE_SUCCESS_2, standardisedTime);
    }

    /**
     * Parses keyword for tag and returns a ListCommand that filters entries based on the given tag keyword.
     *
     * @param argMultimap the tokenized input arguments containing the keyword
     * @return ListCommand that filters by tags
     */
    private ListCommand parseTags(ArgumentMultimap argMultimap) throws ParseException {
        String keyword = argMultimap.getValue(PREFIX_TAG).get().trim();
        if (keyword.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_MISSING_KEYWORD, ListCommand.MESSAGE_USAGE));
        }
        return new ListCommand(new TagsContainKeywordPredicate(keyword), MESSAGE_SUCCESS_1, keyword);
    }

}
