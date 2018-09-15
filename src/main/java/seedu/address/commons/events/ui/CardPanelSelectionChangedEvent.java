package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.card.Card;

/**
 * Represents a selection change in the Person List Panel
 * TODO selection command is not implemented yet.
 */
public class CardPanelSelectionChangedEvent extends BaseEvent {

    private final Card newSelection;

    public CardPanelSelectionChangedEvent(Card newSelection) {
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public Card getNewSelection() {
        return newSelection;
    }
}