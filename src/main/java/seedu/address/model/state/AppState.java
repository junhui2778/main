package seedu.address.model.state;

/**
 * Defines the state of the application with their respective constraints on the type of command it can run.
 */
public class AppState {
    private static State currentState = State.NORMAL;

    public static void setAppState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }

    public static boolean isInTestingState() {
        return currentState == State.TEST || currentState == State.TESTM;
    }
}
