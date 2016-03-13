package dominium.Players;

import dominium.CardStack;
import dominium.Cards.*;

public class FirstMoneyThenPointsPlayer extends AIPlayer {
    private int goldOrSilverCards = 0;
    private int numberOfRounds = 0;

    public FirstMoneyThenPointsPlayer(String name) {
        super(name);
    }

    @Override
    public Card selectCard(CardStack cards) {
        ++numberOfRounds;

        if (cards.isEmpty()) {
            return null;
        }

        Card cardToPick = null;
        if (hasCard(cards, Province.class)) {
            cardToPick = findCard(cards, Province.class);
        } else if (hasCard(cards, Gold.class) && goldOrSilverCards <= 5) {
            cardToPick = findCard(cards, Gold.class);
            ++goldOrSilverCards;
        } else if (hasCard(cards, Duchy.class)) {
            cardToPick = findCard(cards, Duchy.class);
        } else if (hasCard(cards, Silver.class) && numberOfRounds <= 10) {
            cardToPick = findCard(cards, Silver.class);
            ++goldOrSilverCards;
        } else if (hasCard(cards, Estate.class) && numberOfRounds >= 15) {
            cardToPick = findCard(cards, Estate.class);
        } else {
            cardToPick = cards.get((int) (Math.random() * cards.size()));
        }

        return cardToPick;
    }
}
