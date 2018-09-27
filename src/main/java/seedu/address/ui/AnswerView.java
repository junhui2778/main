package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.card.Answer;

/**
 * An UI component to display information of {@code Answer}
 */
public class AnswerView extends UiPart<Region> {
    private static final String FXML = "AnswerView.fxml";

    public final Answer answer;

    @FXML
    private HBox answerViewPane;
    @FXML
    private Label id;
    @FXML
    private Label answerText;

    public AnswerView(Answer answer, int displayedIndex) {
        super(FXML);
        this.answer = answer;
        id.setText(displayedIndex + ". ");
        answerText.setText(answer.value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AnswerView)) {
            return false;
        }

        // state check
        AnswerView card = (AnswerView) other;
        return id.getText().equals(card.id.getText())
                && answer.equals(card.answer);
    }
}
