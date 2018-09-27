package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PHYSICS;
import static seedu.address.logic.commands.ExitCommand.MESSAGE_EXIT_FROM_APP;
import static seedu.address.logic.commands.ExitCommand.MESSAGE_EXIT_FROM_TEST;

import org.junit.Rule;
import org.junit.Test;

import seedu.address.commons.events.model.StartTestEvent;
import seedu.address.commons.events.ui.ExitAppRequestEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.state.AppState;
import seedu.address.model.state.State;
import seedu.address.model.tag.Tag;
import seedu.address.model.test.matchtest.MatchTest;
import seedu.address.testutil.TypicalCards;
import seedu.address.ui.testutil.EventsCollectorRule;

public class ExitCommandTest {
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private Model model = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_exit_success() {
        // during NORMAL AppState
        AppState.setAppState(State.NORMAL);
        CommandResult result = new ExitCommand().execute(model, commandHistory);
        assertEquals(MESSAGE_EXIT_FROM_APP, result.feedbackToUser);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ExitAppRequestEvent);
        assertTrue(eventsCollectorRule.eventsCollector.getSize() == 1);

        // during TEST/TESTM AppState
        AppState.setAppState(State.TESTM);
        MatchTest matchTest = new MatchTest(new Tag(VALID_TAG_PHYSICS), TypicalCards.getTypicalCards());
        matchTest.startTest();

        assertTrue(AppState.isInTestingState());
        model.handleStartTestEvent(new StartTestEvent(matchTest));
        result = new ExitCommand().execute(model, commandHistory);
        assertEquals(MESSAGE_EXIT_FROM_TEST, result.feedbackToUser);
    }
}
