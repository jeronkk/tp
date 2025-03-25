package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all students";
    public static final String EMPTY_LIST = "No student found.";

    private final Predicate<Person> predicate;
    private final String resultMessage;

    /**
     * Generic list command to list all persons
     */
    public ListCommand() {
        this.predicate = PREDICATE_SHOW_ALL_PERSONS;
        this.resultMessage = MESSAGE_SUCCESS;
    }

    /**
     * List command to list all persons with tags matching the given keyword
     */
    public ListCommand(Predicate<Person> predicate, String successMessage, String keyword) {
        this.predicate = predicate;
        this.resultMessage = successMessage + keyword;
    }

    /**
     * Executes the ListCommand by updating the model's filtered person list based on the provided predicate.
     * Returns a CommandResult indicating success or an empty result if no persons match the filter.
     *
     * @param model the model containing the person list to be filtered
     * @return the result of command execution with an appropriate message
     * @throws NullPointerException if the model is null
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        assert predicate != null : "Predicate should be initialized";
        model.updateFilteredPersonList(this.predicate);

        assert model.getFilteredPersonList() != null : "Filtered list should not be null";
        if (model.getFilteredPersonList().isEmpty()) {
            return new CommandResult(EMPTY_LIST);
        } else {
            return new CommandResult(resultMessage);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListCommand)) {
            return false;
        }

        ListCommand otherListCommand = (ListCommand) other;
        return predicate.equals(otherListCommand.predicate);
    }
}
