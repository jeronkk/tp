package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.TuitionTimeUtil;

/**
    * Sorts the person list based on a specified field and order (ascending or descending).
    * Supported fields: name, phone, email, address, tuition.
    */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all persons by %s (%s).";
    public static final String MESSAGE_INVALID_FIELD = "Invalid sort field. Valid fields: name. phone, "
                                    + "email, address, tuition";

    private final String sortField;
    private final boolean ascending;

    /**
        * Creates a SortCommand.
        *
        * @param sortField The field to sort by (e.g. "name", "phone", "tuition").
        * @param ascending True to sort in ascending order, false for descending.
        */
    public SortCommand(String sortField, boolean ascending) {
        this.sortField = sortField.toLowerCase();
        this.ascending = ascending;
    }

    /**
        * Executes the sort command by sorting the filtered person list in the model
        * according to the specified field and order.
        *
        * @param model The model which contains the person list to be sorted.
        * @return A {@code CommandResult} indicating the outcome of the command.
        * @throws CommandException If an invalid sort field is provided.
        */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert model != null : "Model should not be null";

        Comparator<Person> comparator;

        switch (sortField.trim().toLowerCase()) {
        case "name":
            comparator = Comparator.comparing(p -> p.getName().fullName.toLowerCase());
            break;
        case "phone":
            comparator = Comparator.comparing(p -> p.getPhone().value);
            break;
        case "email":
            comparator = Comparator.comparing(p -> p.getEmail().value.toLowerCase());
            break;
        case "address":
            comparator = Comparator.comparing(p -> p.getAddress().value.toLowerCase());
            break;
        case "tuition":
        case "tuitionTime":
            comparator = Comparator.comparing(p -> TuitionTimeUtil.getSortKey(p.getTuitionTime().value));
            break;
        case "tag":
        case "tags":
            comparator = Comparator
                    .<Person>comparingInt(p -> p.getTags().size())
                            .thenComparing(p -> getSortedTagsString(p));
            break;
        default:
            throw new CommandException(MESSAGE_INVALID_FIELD);
        }

        assert comparator != null : "Comparator should have been set for sortField: " + sortField;

        if (!ascending) {
            comparator = comparator.reversed();
        }

        model.sortFilteredPersonList(comparator);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        String direction = ascending ? "ascending" : "descending";
        return new CommandResult(String.format(MESSAGE_SUCCESS, sortField, direction));
    }

    private static String getSortedTagsString(Person p) {
        return p.getTags().stream()
                .map(tag -> tag.tagName.toLowerCase())
                .sorted()
                .reduce((a, b) -> a + "," + b)
                .orElse("");
    }

    /**
        * Checks if two SortCommand instances are equal by comparing their sort fields and order.
        *
        * @param other The object to compare against.
        * @return True if both commands sort by the same field and order; false otherwise.
        */
    @Override
    public boolean equals(Object other) {
        return other instanceof SortCommand
                                        && ((SortCommand) other).sortField.equals(this.sortField)
                                        && ((SortCommand) other).ascending == this.ascending;
    }
}
