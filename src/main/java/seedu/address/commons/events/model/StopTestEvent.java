package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.test.TriviaTest;

/**
 * Stop a test that is running.
 */
public class StopTestEvent extends BaseEvent {
    private TriviaTest test;

    public StopTestEvent(TriviaTest test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public TriviaTest getTest() {
        return test;
    }
}
