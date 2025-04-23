public class Deck {
    private Card[] cards = new Card[100];
    private String deckName;
    private int currentCardIndex = 0;
    private int totalCards = 0;

    /**
     * Get cards from the deck
     */
    public Card getNextCard() {
        //fisher yates shuffle the deck
        for (int i = totalCards - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }

        if (currentCardIndex < totalCards) {
            return cards[currentCardIndex++];
        } else {
            System.out.println("No more cards in the deck.");
            return null;
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
        Card[] TEMP_cards = cards;
        Card[] new_arr = new Card[100];

        int k = 0;
        int j = 0;

        while(k < totalCards){
            if(k == i){
                k++;
            }else{
                new_arr[j] = TEMP_cards[k];
                k++;
                j++;
            }
        }
        cards = new_arr;
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

