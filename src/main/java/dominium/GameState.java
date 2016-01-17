package dominium;

import dominium.Cards.Card;

import java.util.Collection;

public class GameState {
    public static final int BUY_PHASE = 0;
    public static final int CLEAN_UP_PHASE = 1;
    public int actionsLeft;
    public int buysLeft;
    public int coinsLeft;
    public int currentPhase;
    private Collection<Collection<Card>> kingdomCards;

    private Collection<Card> trashedCards;

    public GameState(Collection<Collection<Card>> kingdomCards) {
        actionsLeft = 1;
        buysLeft = 1;
        coinsLeft = 0;
        currentPhase = BUY_PHASE;
        this.kingdomCards = kingdomCards;
    }

    public Collection<Card> getTrashedCards() {
        return trashedCards;
    }

    public void setTrashedCards(Collection<Card> trashedCards) {
        this.trashedCards = trashedCards;
    }
}
