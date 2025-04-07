package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_TT_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MISSING_KEYWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUITION_TIME;

import java.util.function.Predicate;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.TagsContainKeywordPredicate;
import seedu.address.model.person.TuitionTime;
import seedu.address.model.person.TuitionTimeContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    private static final String MESSAGE_SUCCESS_1 = "Listed all persons with subject t/";
    private static final String MESSAGE_SUCCESS_2 = "Listed all persons with tuition time on tt/";
    private static final String MESSAGE_SUCCESS_3 = "Listed all persons with subject t/ and tuition time on tt/";
    private String message;

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
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        Predicate<Person> pred;
        if (argMultimap.getValue(PREFIX_TUITION_TIME).isPresent() && argMultimap.getValue(PREFIX_TAG).isPresent()) {
            this.message = MESSAGE_SUCCESS_3;
            pred = parseTuitionTime(argMultimap).and(parseTags(argMultimap));
        } else if (argMultimap.getValue(PREFIX_TUITION_TIME).isPresent()) {
            this.message = MESSAGE_SUCCESS_2;
            pred = parseTuitionTime(argMultimap);
        } else if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            this.message = MESSAGE_SUCCESS_1;
            pred = parseTags(argMultimap);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
        return new ListCommand(pred, this.message, "");
    }

    /**
     * Parses and validates the tuition time input, and returns a ListCommand
     * that filters entries based on the given tuition day keyword.
     *
     * @param argMultimap the tokenized input arguments containing the tuition time
     * @return Person Predicate that filters by tuition time
     * @throws ParseException if the tuition time keyword is invalid
     */
    private Predicate<Person> parseTuitionTime(ArgumentMultimap argMultimap) throws ParseException {
        String keyword = argMultimap.getValue(PREFIX_TUITION_TIME).get().trim();
        assert keyword != null;
        if (keyword.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_MISSING_KEYWORD, ListCommand.MESSAGE_USAGE));
        }
        try {
            TuitionTime tuitionTime = ParserUtil.parseTuitionTime(keyword);
            Predicate<Person> tuitionTimeEqualsPredicate = person -> person.getTuitionTime().equals(tuitionTime);
            this.message = this.message.replace(PREFIX_TUITION_TIME.toString(), tuitionTime.toString());
            return tuitionTimeEqualsPredicate;
        } catch (ParseException pe) {
            String standardisedTime = DayMapping.map(keyword.toLowerCase());

            if (standardisedTime == null) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_TT_FORMAT, ListCommand.MESSAGE_USAGE));
            }
            this.message = this.message.replace(PREFIX_TUITION_TIME.toString(), standardisedTime);
            return new TuitionTimeContainsKeywordPredicate(keyword);
        }
    }

    /**
     * Parses keyword for tag and returns a ListCommand that filters entries based on the given tag keyword.
     *
     * @param argMultimap the tokenized input arguments containing the keyword
     * @return Person Predicate that filters by tags
     */
    private Predicate<Person> parseTags(ArgumentMultimap argMultimap) throws ParseException {
        String keyword = argMultimap.getValue(PREFIX_TAG).get().trim();
        if (keyword.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_MISSING_KEYWORD, ListCommand.MESSAGE_USAGE));
        }
        this.message = this.message.replace(PREFIX_TAG.toString(), keyword);
        return new TagsContainKeywordPredicate(keyword);
    }

}
