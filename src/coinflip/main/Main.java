package coinflip.main;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int score = 0;
        do {
            printMessage("Hello! This is CoinFlip! Make your choice!");
            printMessage("'heads' or 'tails'?");

            Scanner option = new Scanner(System.in);

            String choice = option.nextLine();  // Read user input

            String optionHead = "heads";
            String optionTails = "tails";

            if(choice.equals(optionHead) || choice.equals(optionTails)) {
                //String[] choices = {"heads", "tails"};
                //String coinToss = (choices[new Random().nextInt(choices.length)]);

                String coinFace = flipCoin();

                printMessage("You chose "+choice);
                printMessage("Flipping coin...");
                delay(2);
                printMessage("The coin landed on... "+coinFace+"!");
                delay(1);

                if (coinFace.equals(choice)) {
                    printMessage("Congratulations! You won!");
                    score+=1;
                    printMessage("Your score is: "+score+"!");
                    delay(2);
                }
                else {
                    printMessage("Better luck next time!");
                    printMessage("Your score is: "+score+"!");
                    delay(2);
                }
            }
            else{
                printMessage("Wrong input!");
                printMessage("Your score is: "+score+"!");
                delay(2);
                continue;
            }

            printMessage("One more time? Press 'y' for YES, and 'n' for NO");
            String optionRestart = option.nextLine();
            if(optionRestart.equals("y")) {
                printMessage("Restarting game...");
                delay(1);
                break;
            }
            else {
                printMessage("Quitting...");
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
        String resultOfFlip = choices[new Random().nextInt(choices.length)];
        return resultOfFlip;
    }
}