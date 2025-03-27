package seedu.address.logic.parser;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
    * Parses input arguments and creates a new SortCommand.
    */
public class SortCommandParser implements Parser<SortCommand> {

    @Override
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim().toLowerCase();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException("Please specify a field to sort by. E.g., sort by name");
        }

        String[] tokens = trimmedArgs.split("\\s+");
        String field;
        boolean ascending = true;

        if (tokens.length == 1) {
            field = tokens[0];
        } else if (tokens.length == 2 && tokens[0].equals("by")) {
            field = tokens[1];
        } else if (tokens.length == 2) {
            field = tokens[0];
            ascending = tokens[1].equals("asc");
            if (!ascending && !tokens[1].equals("desc")) {
                throw new ParseException("Invalid sort order. Use 'asc' or 'desc'.");
            }
        } else if (tokens.length == 3 && tokens[0].equals("by")) {
            field = tokens[1];
            ascending = tokens[2].equals("asc");
            if (!ascending && !tokens[2].equals("desc")) {
                throw new ParseException("Invalid sort order. Use 'asc' or 'desc'.");
            }
        } else {
            throw new ParseException("Invalid sort format. Try: sort by name desc");
        }

        return new SortCommand(field, ascending);
    }
}
