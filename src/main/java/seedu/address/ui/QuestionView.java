package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.card.Question;

/**
 * An UI component to display information of {@code Question}
 */
public class QuestionView extends UiPart<Region> {
    private static final String FXML = "QuestionView.fxml";

    public final Question question;

    @FXML
    private HBox questionViewPane;
    @FXML
    private Label id;
    @FXML
    private Label questionText;

    public QuestionView(Question question, int displayedIndex) {
        super(FXML);
        this.question = question;
        id.setText(displayedIndex + ". ");
        questionText.setText(question.value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof QuestionView)) {
            return false;
        }

        // state check
        QuestionView card = (QuestionView) other;
        return id.getText().equals(card.id.getText())
                && question.equals(card.question);
    }
}
