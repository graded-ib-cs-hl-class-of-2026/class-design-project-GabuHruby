public class Deck {
    private Card[] cards = new Card[100];
    private String deckName;
    private int currentCardIndex = 0;
    private int totalCards = 0;

    /**
     * Get the next card from the deck
     */
    public Card getNextCard() {
        if (currentCardIndex < totalCards) {
            Card nextCard = cards[currentCardIndex];
            currentCardIndex++; 
            return nextCard;
        } else {
            System.out.println("No more cards in the deck.");
            return null;
        }
    }

    /**
     * Shuffles the deck of cards using the Fisher-Yates shuffle algorithm
     */
    public void shuffle() {
        //fisher yates shuffle the deck
        for (int i = totalCards - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    public Card getCard(int index){
        return cards[index];
    }

    public String getCardTerm(int index){
        return cards[index].getQuestion();
    }   
    
    public String getDeckName() {
        return deckName;
    }

    public int getTotalCardCount(){
        return totalCards;
    }

    public void setCard(Card card) {
        cards[totalCards] = card;
        totalCards++;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    /**
     * Remove a card from the cards array
     * @param i
     */
    public void removeCard(int i){
        for (int j = i; j < totalCards - 1; j++) {
            cards[j] = cards[j + 1];
        }
        cards[totalCards-1] = null;
        totalCards--;
    }

    /**
     * reset deck to practice again
     * @return int
     */
    public void resetDeck() {
        currentCardIndex = 0;
    }

}

