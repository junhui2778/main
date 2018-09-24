package seedu.address.model.card;

import java.util.function.Predicate;

/**
 * Tests that a {@code Card}'s {@code Tag} matches any of the keywords given.
 */
public class TagIsKeywordPredicate implements Predicate<Card> {
    private final String keyword;

    public TagIsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Card card) {
        return card.getTags().stream()
                .anyMatch(tag -> tag.tagName.toLowerCase().equals(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagIsKeywordPredicate// instanceof handles nulls
                && keyword.equals(((TagIsKeywordPredicate) other).keyword)); // state check
    }
}
