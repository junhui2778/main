package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;

/**
 * Stop a test that is running.
 */
public class StopTestEvent extends BaseEvent {
    public StopTestEvent() {}

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
