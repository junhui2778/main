package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PHYSICS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.Q_DENISTY_FORMULA;
import static seedu.address.testutil.TypicalCards.Q_EARTH_ROUND;
import static seedu.address.testutil.TypicalCards.Q_FORCE_FORMULA;
import static seedu.address.testutil.TypicalCards.getTypicalTriviaBundle;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.events.model.StopTestEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.card.Card;
import seedu.address.model.tag.Tag;
import seedu.address.model.test.TriviaTest;

public class MatchCommandTest {
    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTriviaBundle(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getTriviaBundle(), new UserPrefs());

        try {
            new TestMCommand(new Tag(VALID_TAG_PHYSICS)).execute(model, commandHistory);
            new TestMCommand(new Tag(VALID_TAG_PHYSICS)).execute(expectedModel, commandHistory);
        } catch (CommandException e) {
            throw new AssertionError();
        }
    }

    @After
    public void setToNormalState() {
        if (model.getCurrentRunningTest() != null) {
            model.getCurrentRunningTest().stopTest();
        }
    }

    @Test
    public void execute_matchSuccess() {
        Index[] correctIndexesEarth = getIndexes(model.getCurrentRunningTest(), Q_EARTH_ROUND);
        assertCommandSuccess(new MatchCommand(correctIndexesEarth[0], correctIndexesEarth[1]), model, commandHistory,
                MatchCommand.MESSAGE_MATCH_SUCCESS, expectedModel);

        Index[] correctIndexesForce = getIndexes(model.getCurrentRunningTest(), Q_FORCE_FORMULA);
        assertCommandSuccess(new MatchCommand(correctIndexesForce[0], correctIndexesForce[1]), model, commandHistory,
                MatchCommand.MESSAGE_MATCH_SUCCESS, expectedModel);

        Index[] correctIndexesDensity = getIndexes(model.getCurrentRunningTest(), Q_DENISTY_FORMULA);
        assertCommandSuccess(new MatchCommand(correctIndexesDensity[0], correctIndexesDensity[1]), model,
                commandHistory, MatchCommand.MESSAGE_TEST_COMPLETED, expectedModel);
    }

    @Test
    public void execute_matchFailure() {
        Index wrongCardCombiQ = getIndexes(model.getCurrentRunningTest(), Q_EARTH_ROUND)[0];
        Index wrongCardCombiA = getIndexes(model.getCurrentRunningTest(), Q_FORCE_FORMULA)[1];
        assertCommandFailure(new MatchCommand(wrongCardCombiQ, wrongCardCombiA), model, commandHistory,
                MatchCommand.MESSAGE_MATCH_FAILURE);
    }

    @Test
    public void execute_matchError() {
        assertCommandFailure(new MatchCommand(Index.fromZeroBased(100), Index.fromZeroBased(105)), model,
                commandHistory, MatchCommand.MESSAGE_INDEX_OUT_OF_BOUND);

        model.handleStopTestEvent(new StopTestEvent());
        assertCommandFailure(new MatchCommand(Index.fromZeroBased(1), Index.fromZeroBased(2)), model,
                commandHistory, MatchCommand.MESSAGE_NOT_IN_MATCHING_TEST);
    }

    /**
     * Used to obtain the base0 indexes of matching question and answer in the test.
     * @param test The matchTest that is ongoing.
     * @param card The card that is you want to match.
     * @return the an array of indexes of size 2. With the first and second indexes representing the question and
     * answer respectively.
     */
    private Index[] getIndexes(TriviaTest test, Card card) {
        return new Index[] {
                Index.fromZeroBased(test.getQuestions().indexOf(card.getQuestion())),
                Index.fromZeroBased(test.getAnswers().indexOf(card.getAnswer()))
        };
    }
}
