package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NO_TAG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PHYSICS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalTriviaBundle;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.model.test.matchtest.MatchTest;

public class TestMCommandTest {
    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTriviaBundle(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getTriviaBundle(), new UserPrefs());
    }

    @After
    public void setToNormalState() {
        if (model.getCurrentRunningTest() != null) {
            model.getCurrentRunningTest().stopTest();
        }
    }

    @Test
    public void execute_testStartedSuccessfully() {
        assertCommandSuccess(new TestMCommand(new Tag(VALID_TAG_PHYSICS)), model, commandHistory,
                TestMCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_testDidNotStart() {
        assertCommandFailure(new TestMCommand(new Tag(VALID_TAG_NO_TAG)), model, commandHistory,
                MatchTest.MESSAGE_MATCH_TEST_CONSTRAINS);
    }
}
