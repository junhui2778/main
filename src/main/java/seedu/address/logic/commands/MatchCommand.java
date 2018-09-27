package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.events.model.StopTestEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.test.TriviaTest;
import seedu.address.model.test.matchtest.MatchAttempt;
import seedu.address.model.test.matchtest.MatchTest;

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

    public static final String MESSAGE_MATCH_SUCCESS = "Perfect Match!";
    public static final String MESSAGE_MATCH_FAILURE = "Wrong Match!";
    public static final String MESSAGE_TEST_COMPLETED = "You have completed the test!";
    public static final String MESSAGE_NOT_IN_MATCHING_TEST = "The undergoing test is not a matching test";
    public static final String MESSAGE_INDEX_OUT_OF_BOUND = "The index specified is out of bound.";

    private final Index questionIndex;
    private final Index answerIndex;

    public MatchCommand(Index questionIndex, Index answerIndex) {
        this.questionIndex = questionIndex;
        this.answerIndex = answerIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        TriviaTest test = model.getCurrentRunningTest();
        if (!(test instanceof MatchTest)) {
            throw new CommandException(MESSAGE_NOT_IN_MATCHING_TEST);
        }

        MatchTest testM = (MatchTest) test;
        try {
            MatchAttempt attempt = testM.addAttempt(questionIndex, answerIndex);
            if (attempt.isCorrect()) {
                testM.removeCardFromUi(attempt);
            } else {
                throw new CommandException(MESSAGE_MATCH_FAILURE);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INDEX_OUT_OF_BOUND);
        }

        if (testM.isEndOfTest()) {
            testM.stopTest();
            EventsCenter.getInstance().post(new StopTestEvent(model.getCurrentRunningTest()));
            return new CommandResult(MESSAGE_TEST_COMPLETED);
        }

        return new CommandResult(String.format(MESSAGE_MATCH_SUCCESS));
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
