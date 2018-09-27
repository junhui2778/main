package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.test.matchtest.MatchTest;

/**
 * The command which will executing the matching test of trivia.
 */
public class TestMCommand extends Command {
    public static final String COMMAND_WORD = "testM";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts a matching test. "
            + "Parameters: "
            + PREFIX_TAG + "TAG "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "Physics ";

    public static final String MESSAGE_SUCCESS = "Matching test started.";

    private final Tag tag;

    public TestMCommand(Tag tag) {
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        try {
            MatchTest test = new MatchTest(tag, model);
            test.startTest();
            return new CommandResult(String.format(MESSAGE_SUCCESS));
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TestMCommand // instanceof handles nulls
                && tag.equals(((TestMCommand) other).tag)); // state check
    }

}
