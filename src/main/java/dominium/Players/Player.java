package dominium.Players;

import dominium.CardStack;
import dominium.Cards.Card;
import dominium.Cards.TreasureCard;
import dominium.Cards.VictoryCard;

import java.util.List;

public abstract class Player {
    protected String name;
    protected CardStack handCards;
    protected CardStack discardedCards;
    protected CardStack deckCards;
    protected int buys = 0;
    protected int coins = 0;
    protected int turns = 0;
    protected int actions = 0;

    public String getName() {
        return name;
    }

    public Player(String name) {
        this.name = name;

        this.handCards = new CardStack();
        this.discardedCards = new CardStack();
        this.deckCards = new CardStack();
    }

    public abstract Card selectCard(List<Card> cards);

    public void discardCard(Card card) {
        ensureCardIsInStack(card, handCards);

        handCards.remove(card);
        discardedCards.push(card);
    }

    public void discardAllCards() {
        discardedCards.addAll(handCards);
        handCards.removeAllElements();
    }

    public void drawCards(int number) {
        if (notEnoughCardsInDeck(number)) {
            discardedCards.shuffle();
            deckCards.addAll(discardedCards);
            discardedCards.removeAllElements();
        }

        for (int i = 0; i < number; i++) {
            handCards.push(deckCards.pop());
        }
    }

    private boolean notEnoughCardsInDeck(int number) {
        return deckCards.size() < number;
    }

    public CardStack handCards() {
        return handCards;
    }

    public CardStack discardedCards() {
        return discardedCards;
    }

    public CardStack deckCards() {
        return deckCards;
    }

    public int victoryPoints() {
        return victoryPointsInCardStack(handCards)
                + victoryPointsInCardStack(discardedCards)
                + victoryPointsInCardStack(deckCards);
    }

    public int coins() {
        return coins;
    }

    public void spendCoins(int number) {
        if (number > coins) {
            throw new IllegalArgumentException("Cannot spend more than available.");
        }

        coins -= number;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoinsFromTreasureCardsOnHand() {
        coins = handCards
                .stream()
                .filter(card -> card instanceof TreasureCard)
                .mapToInt(card -> ((TreasureCard) card).getValue())
                .sum();
    }

    public void setCoins(int number) {
        coins = number;
    }

    public int getBuys() {
        return buys;
    }

    public void setBuys(int buys) {
        this.buys = buys;
    }

    public int getActions() {
        return actions;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }

    public int turns() {
        return turns;
    }

    public void incrementTurns() {
        turns++;
    }

    private void ensureCardIsInStack(Card card, CardStack stack) {
        if (!stack.contains(card)) {
            throw new IllegalArgumentException("Card cannot be used, because it is not in the stack.");
        }
    }

    private int victoryPointsInCardStack(CardStack cardStack) {
        return cardStack
                .stream()
                .filter(card -> card instanceof VictoryCard)
                .mapToInt(card -> ((VictoryCard) card).getVictoryPoints())
                .sum();
    }
}
