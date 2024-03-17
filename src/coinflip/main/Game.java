package coinflip.main;

import java.io.*;
import java.util.Scanner;

/**
 * The Game class represents a simple coin flipping game.
 * It allows the player to play the game, view the top score, and exit the game.
 */
public class Game {

    // Variable to store the top score
    private int topScore = 0;

    // File path for storing the top score
    private final String SCORE_FILE = "src/coinflip/main/highscore.txt";

    /**
     * Prints the main menu of the game.
     * This method displays the options available to the player in the game menu.
     */
    private void printMenu() {
        Abbreviations.printMessage("====== MENU ======");
        Abbreviations.printMessage("1. Start Game");
        Abbreviations.printMessage("2. Show Top Score");
        Abbreviations.printMessage("3. Exit");
        Abbreviations.printMessage("==================");
    }

    /**
     * Starts the game menu.
     * This method loads the top score, initializes the game loop, and handles user inputs.
     * It allows the user to choose between starting the game, viewing the top score, or exiting the menu.
     *
     * @throws InterruptedException if the thread is interrupted while sleeping or waiting
     */
    public void start() throws InterruptedException {
        // Load the top score from the file
        loadTopScore();

        // Initialize variables
        boolean exitMenu = false;
        Scanner userInput = new Scanner(System.in);

        // Start the game loop
        while (!exitMenu) {
            // Display the menu options
            printMenu();

            // Read user input
            String choice = userInput.nextLine();

            // Process user choice
            switch (choice) {
                case "1" ->
                    // Start a new game and update the top score
                        topScore = playGame(userInput);
                case "2" -> {
                    // Display the top score
                    showTopScore();
                    // Start the menu again
                    start();
                }
                case "3" ->
                        exitMenu = true;
                default ->
                        Abbreviations.printMessage("Invalid choice. Press 1, 2, or 3");
            }
        }

        Abbreviations.printMessage("Quitting...");

        saveTopScore();

        userInput.close();
    }


    /**
     * Plays the coin flipping game.
     * This method allows the player to play the coin flipping game until they choose to stop.
     * It keeps track of the player's score and updates the top score if a new high score is achieved.
     *
     * @param userInput the Scanner object to read user input from
     * @return the player's final score
     * @throws InterruptedException if the thread is interrupted while sleeping or waiting
     */
    private int playGame(Scanner userInput) throws InterruptedException {
        // Initialize score
        int score = 0;

        // Display introductory message
        Abbreviations.printMessage("What's the most you ever lost on a coin toss?");

        // Main game loop
        do {
            // Get user's choice
            String choice = GameOperations.getChoiceFromUser(userInput);

            // Check if the choice is valid
            boolean validChoice = GameOperations.isValid(choice);
            if (validChoice) {
                // Flip the coin
                String coinFace = GameOperations.flipCoin();

                // Toss the coin and display the result
                GameOperations.tossing(choice, Coin.CoinSide.valueOf(coinFace));

                // Check if the user guessed correctly
                if (coinFace.equalsIgnoreCase(choice)) {
                    Abbreviations.printMessage("Well done!");
                    score += 1;
                } else {
                    Abbreviations.printMessage("Oops... Will you hold still please?");
                    score = 0;
                }
            } else {
                // Invalid choice, prompt the user to try again
                Abbreviations.printMessage("What was that? One more time. Make your choice!");
                Abbreviations.delay(2);
                continue; // Skip the rest of the loop and start again
            }

            // Ask the user if they want to play again
            String playAgain = GameOperations.playAgain(score, userInput);

            // Check if the user wants to play again
            if (playAgain.equals("y") || playAgain.equals("yes")){
                Abbreviations.printMessage("Again...");
            } else {
                // User chooses to exit the game
                Abbreviations.printMessage("Goodbye... for now");
                break;
            }

            // Delay before the next iteration
            Abbreviations.delay(1);
        } while (true); // Continue until the user chooses to stop playing

        // Check if the player achieved a new high score
        if (score > topScore) {
            topScore = score;
            Abbreviations.printMessage("New Top Score: " + topScore + "!");
        }

        return score;
    }


    /**
     * Displays the top score.
     * This method prints the current top score to the console.
     */
    private void showTopScore() {
        Abbreviations.printMessage("Top Score: " + topScore);
    }

    /**
     * Loads the top score from the file.
     * This method reads the top score from the file specified by SCORE_FILE.
     * If the file does not exist or an error occurs while reading, an exception is thrown.
     */
    private void loadTopScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            // Read the top score from the file
            String line = reader.readLine();
            if (line != null) {
                topScore = Integer.parseInt(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + SCORE_FILE, e);
        } catch (IOException | NumberFormatException e) {
            Abbreviations.printMessage("Error loading highest score: " + e.getMessage());
        }
    }

    /**
     * Saves the top score to the file.
     * This method writes the current top score to the file specified by SCORE_FILE.
     * If an error occurs while writing, an exception is thrown.
     */
    private void saveTopScore() {
        // Load the current top score from the file
        int currentTopScore = loadTopScoreFromFile();

        if (topScore > currentTopScore) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
                // Write the top score to the file
                writer.write(String.valueOf(topScore));
            } catch (IOException e) {
                Abbreviations.printMessage("Error saving highest score: " + e.getMessage());
            }
        }
    }

    /**
     * Loads the top score from the file.
     * This method reads the top score from the file specified by SCORE_FILE.
     * If the file does not exist or an error occurs while reading, returns 0.
     */
    private int loadTopScoreFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            // Read the top score from the file
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (FileNotFoundException e) {
            Abbreviations.printMessage("File not found: " + SCORE_FILE);
        } catch (IOException | NumberFormatException e) {
            Abbreviations.printMessage("Error loading highest score: " + e.getMessage());
        }
        return 0; // Return 0 if there was an error loading the top score
    }
}