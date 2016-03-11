package dominium;

import dominium.Cards.Card;

import java.util.Collections;
import java.util.Stack;

public class CardStack extends Stack<Card> {
    public void shuffle() {
        Collections.shuffle(this);
    }

    public boolean hasCard(Class cardClass) {
        return stream().anyMatch(card -> card.getClass() == cardClass);
    }
}
