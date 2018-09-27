package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * The MatchCommand can only be used in a Matching test.
 */
public class MatchCommand extends Command {
    public static final String COMMAND_WORD = "match";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Match a question to its answer. "
            + "Parameters: "
            + PREFIX_QUESTION + "INDEX_OF_QUESTION "
            + PREFIX_ANSWER + "INDEX_OF_ANSWER"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_QUESTION + "1 "
            + PREFIX_ANSWER + "4";

    public static final String MESSAGE_SUCCESS = "The question and answer matched correctly!";
    private static final String MESSAGE_NOT_IN_MATCHING_TEST = "The undergoing test is not a matching test";

    private final Index questionIndex;
    private final Index answerIndex;

    public MatchCommand(Index questionIndex, Index answerIndex) {
        this.questionIndex = questionIndex;
        this.answerIndex = answerIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        // TODO add logic to remove the cards when answered corrected
        // TODO add logic to signal a wrong match
        // TODO remember to change the AppState back to NORMAL

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        // All the tests are different even if they have the same parameters.
        return other == this
                || (other instanceof MatchCommand // instanceof handles nulls
                && questionIndex.equals(((MatchCommand) other).questionIndex)
                && answerIndex.equals(((MatchCommand) other).answerIndex));
    }
}
