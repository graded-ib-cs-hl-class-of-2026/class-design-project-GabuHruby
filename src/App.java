import java.util.HashMap;
public class App {

    // Declaring global colors tp be used in the program
    public String GREEN = "\033[0;32m"; // GREEN
    public String BLUE = "\033[0;34m"; // BLUE
    public String RESET = "\033[0m"; // Text Reset
    public String RED = "\033[0;31m"; // RED
    public String PURPLE = "\033[0;35m";// PURPLE
    public String BOLD = "\033[0;1m"; //BOLD 

    /*
     * The colors are ANSI escape codes that are used to change the color of the
     * text.
     * \033[ is the escape code, so all of the colors have it.
     * - Sometime it is written as ESC or \e[
     * The part after that is the color code.
     * - 0;32m is the color code for green
     * - 0;34m is the color code for blue
     * - 0;31m is the color code for red
     * - 0;35m is the color code for purple
     * - 0m is the color code for reset
     * learned from
     * https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-
     * using-system-out-println
     */

    // Borders and symbols https://www.w3.org/TR/xml-entity-names/025.html, and
    // https://www.alt-codes.net/arrow_alt_codes.php

    /*
     * HASHMAPS learned from: https://builtin.com/articles/hashmap-in-java#:~:text=HashMap%20is%20a%20data%20structure%20that%20uses%20the%20Map%20interface,data%20based%20on%20unique%20keys. 
     * "HashMap is a data structure that uses the Map interface and a hash table for storing key-value pairs. It’s a widely used data structure in Java that provides efficient access and manipulation of data based on unique keys."
     * In my own words, it is a data structure that allows you to assaign a key to a specific value, making it easier to access the value using the key.
     */
    private HashMap<String, Deck> decks;
    private final Printer printer = new Printer();

    /**
     * Main method to start the application.
     */
    public void Start() {
        //Get flashcards
        decks = getDecks("flashcards.txt");
        //Print Intro
        printIntro();
        printMenu();
    }    

    /**
     * Print intro to program
     */
    public void printIntro() {
        printer.output( BLUE + "╭───────────────────────────────────────────────────────────────────────────────────────────────╮\n" +
                        "│  _______  _        _______  _______           _______  _______  _______  ______   _______  _  │\n" + //
                        "│ (  ____ \\( \\      (  ___  )(  ____ \\|\\     /|(  ____ \\(  ___  )(  ____ )(  __  \\ (  ____ \\( ) │\n" + //
                        "│ | (    \\/| (      | (   ) || (    \\/| )   ( || (    \\/| (   ) || (    )|| (  \\  )| (    \\/| | │\n" + //
                        "│ | (__    | |      | (___) || (_____ | (___) || |      | (___) || (____)|| |   ) || (_____ | | │\n" + //
                        "│ |  __)   | |      |  ___  |(_____  )|  ___  || |      |  ___  ||     __)| |   | |(_____  )| | │\n" + //
                        "│ | (      | |      | (   ) |      ) || (   ) || |      | (   ) || (\\ (   | |   ) |      ) |(_) │\n" + //
                        "│ | )      | (____/\\| )   ( |/\\____) || )   ( || (____/\\| )   ( || ) \\ \\__| (__/  )/\\____) | _  │\n" + //
                        "│ |/       (_______/|/     \\|\\_______)|/     \\|(_______/|/     \\||/   \\__/(______/ \\_______)(_) │\n" + //
                        "╰───────────────────────────────────────────────────────────────────────────────────────────────╯\n");
                        printer.output("╭───────────────────────────────────────────────────────────────────────────────────────────────╮\n" +
                        "│" + RESET + "                              Welcome to the Flashcard App!                                    " + BLUE + "│\n" + //
                        "│" + RESET + "                      This app allows you to create and manage flashcards                      " + BLUE + "│\n" + //
                        "│" + RESET + "                       You can add, remove, and view decks of flashcards.                      " + BLUE + "│\n" + //
                        "│" + RESET + "                                   Let's get started!                                          " + BLUE + "│\n" + //
                        "╰───────────────────────────────────────────────────────────────────────────────────────────────╯\n" + RESET);
    }

    /**
     * Prints the main menu of the application.
     */
    public void printMenu() {
        while(true){
            if (decks.isEmpty()) {
                printer.output(BOLD + "No decks available. Please add a deck first." + RESET);
            } else {
                printer.output(BOLD + "\nAvailable Decks: " + RESET);
                for (String deckName : decks.keySet()) {
                    printer.output(BOLD + " ↪ " + RESET + deckName);
                }
            }
            printer.output("\n1. Choose Decks");
            printer.output("2. Add Deck");
            printer.output("3. Remove Deck");
            printer.output("4. Exit without saving changes");
            printer.output("5. Exit with saving changes");
            printer.output("Please select an option:");
            String choice = printer.input();
            
            switch (choice) {
                case "1":
                    chooseDecks();
                    break;
                case "2":
                    addDeck();
                    break;
                case "3":
                    removeDeck();
                    break;
                case "4":
                    printer.output("Exiting the application without saving changes");
                    printGoodbye(false);
                    return;
                case "5":
                    printer.output("Exiting the application and saving changes");
                    saveChanges();
                    printGoodbye(true);
                    return;
                default:
                    printer.output("⚠️ Invalid choice. Please try again.");
            }
        }
        
    }

    /**
     * Print goodbye message
     * * @param saved boolean to check if the changes were saved or not
     */
    public void printGoodbye(boolean saved) {
        String filler;
        String spaceBefore;
        String spaceAfter;
        if(!saved){ 
            filler = "without saving";
            spaceBefore = "         ";
            spaceAfter = "        ";
        }else{
            filler = "and saved";
            spaceBefore = "           ";
            spaceAfter = "           ";
        }

        printer.output(BLUE + "╭───────────────────────────────────────────────────────────────────────────────────────────────╮\n" +
                        "│" + RESET + "                               Thank you for using the Flashcard App!                          " + BLUE + "│\n" + //
                        "│" + RESET + "                                 I hope you had a great experience.                            " + BLUE + "│\n" + //
                        "│" + RESET + spaceBefore + "                   You Have left " + filler + " the changed data!             " + spaceAfter + BLUE + "│\n" + //
                        "│" + RESET + "                                         Have a great day!                                     " + BLUE + "│\n" + //
                        "╰───────────────────────────────────────────────────────────────────────────────────────────────╯" + RESET);
        printer.output(BLUE + "╭───────────────────────────────────────────────────────────────────────────────────────────────╮\n" +
                        "│               _______  _______  _______  ______   ______            _______  _                │\n" + //
                        "│              (  ____ \\(  ___  )(  ___  )(  __  \\ (  ___ \\ |\\     /|(  ____ \\( )               │\n" + //
                        "│              | (    \\/| (   ) || (   ) || (  \\  )| (   ) )( \\   / )| (    \\/| |               │\n" + //
                        "│              | |      | |   | || |   | || |   ) || (__/ /  \\ (_) / | (__    | |               │\n" + //
                        "│              | | ____ | |   | || |   | || |   | ||  __ (    \\   /  |  __)   | |               │\n" + //
                        "│              | | \\_  )| |   | || |   | || |   ) || (  \\ \\    ) (   | (      (_)               │\n" + //
                        "│              | (___) || (___) || (___) || (__/  )| )___) )   | |   | (____/\\ _                │\n" + //
                        "│              (_______)(_______)(_______)(______/ |/ \\___/    \\_/   (_______/(_)               │\n" + //
                        "│                                                                                               │\n" + //
                        "╰───────────────────────────────────────────────────────────────────────────────────────────────╯\n" + RESET);
    }

    /**
     * Method to choose a deck to practice with.
     */
    public void chooseDecks() {
        while(true){
            printer.output("\nPlease select a deck (press e to exit/cancel): ");
            int i = 1;
            for (String deckName : decks.keySet()) {
                printer.output(i + ". " + deckName);
                i++;
            }

            String choice = printer.input();
            if(choice.equals("e")){
                break;
            }
            try {
                int deckIndex = Integer.parseInt(choice);   
                if (deckIndex > 0 && deckIndex <= decks.size()){
                    String selectedDeckName = (String) decks.keySet().toArray()[deckIndex - 1];
                    Deck chosenDeck = decks.get(selectedDeckName);
    
                    deckMenu(chosenDeck);
                    break;
                } else {
                    printer.output("⚠️ Invalid choice. Please try again.");
                } 
            } catch (Exception e) {
                printer.output("⚠️ Invalid choice. Please try again.");
            }
            
            
        }
    }
    
    /**
     * This is the menu after you choose a deck
     * @param deck
     */
    public void deckMenu(Deck deck){
        while (true){
            printer.output("\nYou are inside the " + BOLD + deck.getDeckName().toUpperCase() + RESET + " deck" +
            "\n1. Practice deck" +
            "\n2. Add Card" +
            "\n3. Remove Card" +
            "\n4. Go Back" +
            "\nPlease select an option:");

            String response = printer.input();

            switch(response){
                case "1":
                    practiceDeck(deck);
                    break;
                case "2":
                    addCard(deck);
                    break;
                case "3":
                    removeCard(deck);
                    break;
                case "4":
                    return;
                default:
                    printer.output("⚠️ Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Allows you to remove a card from a deck
     * @param deck
     */
    public void removeCard(Deck deck){
        while(true){
            if(deck.getTotalCardCount() == 0){
                printer.output("THERE ARE NO CARDS IN THIS DECK ⚠️");
                break;
            }
            
            printer.output("\nPlease select a card to remove(press e to exit/cancel): ");
            for (int i = 0; i < deck.getTotalCardCount(); i++) {
                printer.output(i+1 + ". " + deck.getCardTerm(i));
            }
            String response = printer.input();
            if(response.equals("e")){
                break;
            }

            int indexCard = Integer.parseInt(response);

            if(indexCard > 0 && indexCard <= deck.getTotalCardCount()){
                deck.removeCard(indexCard - 1);
                
                printer.output("Card removed, do you want to remove another one? (y/n)");
                response = printer.input();

                if(response.equals("n")){
                    break;
                }else if(!response.equals("y")){
                    printer.output("⚠️ Invalid choice. Returning...");
                    break;
                }

            }else{
                printer.output("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Allows you to add a card to the deck
     * @param deck
     */
    public void addCard(Deck deck){
        while(true){
            if(deck.getTotalCardCount() == 100){
                printer.output("Maximum card limit reached ⚠️");
                break;
            }
            printer.output("\nAdd a question/term (press e to exit/cancel):");
            String question = printer.input();

            if(question.equals("e")){
                break;
            }else{
                printer.output("\nAdd a answer/definition (press e to exit/cancel):");
                String answer = printer.input();

                if(answer.equals("e")){
                    break;
                }else{
                    Card card = new Card(question, answer);
                    deck.setCard(card);
                }
            }
        }
    }

    /**
     * Method to practice with a selected deck.
     * @param deck The deck to practice with.
     */
    public void practiceDeck(Deck deck) {
        printer.output("\nStarting practice with deck: " + deck.getDeckName() + "(press e to exit/cancel)");
        if(deck.getTotalCardCount() == 0){
            printer.output(BOLD + "THERE ARE NO CARDS IN THIS DECK ⚠️" + RESET);
            deck.resetDeck();
        }else{
        Card card;
        deck.shuffle();
            while ((card = deck.getNextCard()) != null) {
                printer.output("Question: " + card.getQuestion());
                String answer = printer.input();
                if (answer.equals(card.getAnswer())) {
                    printer.output("\nCorrect! ✅" + "\n");
                } else if (answer.equals("e")){
                    break;
                }else {
                    printer.output("\nIncorrect ❌. The correct answer is: " + card.getAnswer() + "\n");
                }
            }
            deck.resetDeck();
        }
    }

    /**
     * This method creates a new deck and adds it to the decks list
     */
    public void addDeck() {
        printer.output("\nWhat is the name of the deck you want to create? (press e to exit/cancel)");
        String deckName = printer.input();
        if(deckName.equals("e")){
            return;
        }

        Deck deck = new Deck();
        deck.setDeckName(deckName);
        decks.put(deckName, deck);
        deckMenu(deck);
    }

    /**
     * This method removes a deck from the decks list
     */
    public void removeDeck() {
        while(true){
            if(decks.isEmpty()){
                printer.output("THERE ARE NO DECKS ⚠️");
                break;
            }
            
            printer.output("\nPlease select a deck to remove(press e to exit/cancel): ");
            int i = 0;
            for (String key : decks.keySet()) {
                printer.output(i + 1 + ". " + key);
                i++;
            }
            String deckToRemove = printer.input();
            if(deckToRemove.equals("e")){
                break;
            }
            try {
                int deckToRemoveIndex = Integer.parseInt(deckToRemove);
                if (deckToRemoveIndex <= 0 || deckToRemoveIndex > decks.size()) {
                    printer.output("⚠️ Invalid input. Please enter a valid number.");
                } else {
                    decks.remove((String) decks.keySet().toArray()[deckToRemoveIndex - 1]);
                }
            } catch (NumberFormatException e) {
                printer.output("⚠️ Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Method to get flashcards from a file.
     * @return Flashcards[] array of flashcards
     */ 
    public HashMap<String, Deck> getDecks(String filename){
        //create a hashmap of decks
        decks = new HashMap<>();
        try {
            //save a file into the printer object
            printer.openFile(filename);

            //run through the whole file to set the decks
            while (printer.fileHasNextLine()) {
                Deck deck = new Deck();

                String deckName = printer.getNextLine();
                deck.setDeckName(deckName);
                while(printer.fileHasNextLine()){
                    String question = printer.getNextLine();
                    if(question.equals("")){
                        break;
                    }
                    if(printer.fileHasNextLine()){
                        String answer = printer.getNextLine();
                        if(answer.equals("")){
                            break;
                        }
                        Card card = new Card(question, answer);
                        deck.setCard(card);
                    }
                }
                decks.put(deck.getDeckName(), deck);
            }
            printer.closeFile();

        } catch (Exception e) {
            printer.output("Error reading file: " + e.getMessage());
        }


        return decks;
    }

    /**
     * Exits the program and saves the changes to the file.
     */
    public void saveChanges(){
        String textToSave = "";
        for (String deckName : decks.keySet()) {
            textToSave += deckName + "\n";
            for (int i = 0; i < decks.get(deckName).getTotalCardCount(); i++) {
                textToSave += decks.get(deckName).getCard(i).getQuestion() + "\n";
                textToSave += decks.get(deckName).getCard(i).getAnswer() + "\n";
            }
            textToSave += "\n";
        }
        try {
            printer.saveToFile("flashcards.txt", textToSave);
        } catch (Exception e) {
            printer.output("Error saving file: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.Start();
    }
}
