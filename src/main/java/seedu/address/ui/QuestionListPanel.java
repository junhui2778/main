package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.card.Question;

/**
 * The panel which contains all the questions in a particular test.
 */
public class QuestionListPanel extends UiPart<Region> {
    private static final String FXML = "QuestionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(QuestionListPanel.class);

    @FXML
    private ListView<Question> testMQuestionListView;

    public QuestionListPanel(ObservableList<Question> questionList) {
        super(FXML);
        setConnections(questionList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Question> questionList) {
        this.testMQuestionListView.setItems(questionList);
        testMQuestionListView.setCellFactory(listView -> new QuestionListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Card} using a {@code QuestionView}.
     */
    class QuestionListViewCell extends ListCell<Question> {
        @Override
        protected void updateItem(Question question, boolean empty) {
            super.updateItem(question, empty);

            if (empty || question == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new QuestionView(question, getIndex() + 1).getRoot());
            }
        }
    }
}
