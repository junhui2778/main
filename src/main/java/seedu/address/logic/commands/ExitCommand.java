package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.model.StopTestEvent;
import seedu.address.commons.events.ui.ExitAppRequestEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.state.AppState;
import seedu.address.model.test.TriviaTest;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_FROM_APP = "Exiting Address Book as requested ...";
    public static final String MESSAGE_EXIT_FROM_TEST = "Exiting from test as requested ...";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        if (AppState.isInTestingState()) {
            TriviaTest currentTest = model.getCurrentRunningTest();
            currentTest.stopTest();
            EventsCenter.getInstance().post(new StopTestEvent(currentTest));
            return new CommandResult(MESSAGE_EXIT_FROM_TEST);
        } else {
            EventsCenter.getInstance().post(new ExitAppRequestEvent());
            return new CommandResult(MESSAGE_EXIT_FROM_APP);
        }
    }

}
