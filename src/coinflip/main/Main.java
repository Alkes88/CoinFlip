package coinflip.main;

import java.util.*;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        int score = 0;
        printMessage("What's the most you ever lost on a coin toss?");
        do {
            Scanner userInput = new Scanner(System.in);
            String choice = getChoiceFromUser(userInput);

            CoinSide validChoice = isValid(choice);

            if(validChoice != null) {
                String coinFace = flipCoin();

                printMessage("You chose "+choice);
                printMessage("Flipping coin...");
                delay(2);
                printMessage("The coin landed on... "+coinFace+"!");
                delay(1);

                if (coinFace.equals(choice)) {
                    printMessage("Well done!");
                    score+=1;
                    delay(2);
                }
                else {
                    printMessage("Oops... Will you hold still please?");
                    delay(2);
                }
            }
            else{
                printMessage("What was that? One more time. Make your choice!");
                delay(2);
                continue;
            }

            printMessage("Your score is: "+score);
            printMessage("One more time? 'y' or 'n'");
            String optionRestart = userInput.nextLine();
            if(optionRestart.equals("y")) {
                printMessage("Again...");
                delay(1);
            }
            else {
                printMessage("Goodbye... for now");
                delay(1);
                break;
            }
        } while (true);
    }
    private static void printMessage(String message) {
        System.out.println(message);
    }

    private static void delay(int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
    }
    private static String flipCoin(){
        String[] choices = {"heads", "tails"};
        return choices[new Random().nextInt(choices.length)];
    }
    private static String getChoiceFromUser(Scanner userInput){
        printMessage("You stand to win everything. Call it!");
        printMessage("It's either 'heads' or 'tails'");
        return userInput.nextLine();
    }
     enum CoinSide{
        HEADS,
        TAILS
    }
    private static CoinSide isValid(String choice){
        if ((choice.equalsIgnoreCase(CoinSide.HEADS.toString())) || (choice.equalsIgnoreCase(CoinSide.TAILS.toString()))){
            int randomSide = (int) (Math.random() * 2);
            return (randomSide == 0) ? CoinSide.HEADS : CoinSide.TAILS;
        }
        else{
            return null;
        }
    }

    //private static guessedCorrectly(){
    //}

    //private static playAgain(){
    //}
}