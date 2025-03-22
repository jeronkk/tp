package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class SortCommand extends Command {

	public static final String COMMAND_WORD = "sort";

	public static final String MESSAGE_SUCCESS = "Sorted all persons by %s (%s).";
	public static final String MESSAGE_INVALID_FIELD = "Invalid sort field. Valid fields: name. phone, "
									+ "email, address, tuition";

	private final String sortField;
	private final boolean ascending;

	public SortCommand(String sortField, boolean ascending) {
		this.sortField = sortField.toLowerCase();
		this.ascending = ascending;
	}

	@Override
	public CommandResult execute (Model model) throws CommandException {
		requireNonNull(model);

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
			comparator = Comparator.comparing(p -> p.getTuitionTime().value.toLowerCase());
			break;
		/*case "tag":
		case "tags":
			comparator = Comparator.comparing(p -> p.getTags().toLowerCase());
			break;*/
		default:
			throw new CommandException(MESSAGE_INVALID_FIELD);
		}

		if (!ascending) {
			comparator = comparator.reversed();
		}

		model.sortFilteredPersonList(comparator);
		model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

		String direction = ascending ? "ascending" : "descending";
		return new CommandResult(String.format(MESSAGE_SUCCESS, sortField, direction));
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof SortCommand
										&& ((SortCommand) other).sortField.equals(this.sortField)
										&& ((SortCommand) other).ascending == this.ascending;
	}
}
