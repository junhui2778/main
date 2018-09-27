package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.test.TriviaTest;

/**
 * Indicates a new test will be starting.
 */
public class StartTestEvent extends BaseEvent {
    private TriviaTest test;

    public StartTestEvent(TriviaTest test) {
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
