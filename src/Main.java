import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int score = 0;
        do {
            System.out.println("Hello! This is CoinFlip! Make your choice!");
            System.out.println("'heads' or 'tails'?");

            Scanner option = new Scanner(System.in);

            String choice = option.nextLine();  // Read user input

            String optionHead = "heads";
            String optionTails = "tails";

            if(choice.equals(optionHead) || choice.equals(optionTails)) {
                String[] choices = {"heads", "tails"};
                String coinToss = (choices[new Random().nextInt(choices.length)]);

                System.out.println("You chose "+choice);
                System.out.println("Flipping coin...");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("The coin landed on... "+coinToss+"!");
                TimeUnit.SECONDS.sleep(1);

                if (coinToss.equals(choice)) {
                    System.out.println("Congratulations! You won!");
                    score+=1;
                    System.out.println("Your score is: "+score+"!");
                    TimeUnit.SECONDS.sleep(2);
                }
                else {
                    System.out.println("Better luck next time!");
                    System.out.println("Your score is: "+score+"!");
                    TimeUnit.SECONDS.sleep(2);
                }
            }
            else{
                System.out.println("Wrong input!");
                System.out.println("Your score is: "+score+"!");
                TimeUnit.SECONDS.sleep(2);
                continue;
            }

            System.out.println("One more time? Press 'y' for YES, and 'n' for NO");
            String optionRestart = option.nextLine();
            if(optionRestart.equals("y")) {
                System.out.println("Restarting game...");
                TimeUnit.SECONDS.sleep(1);
                break;
            }
            else {
                System.out.println("Quitting...");
                TimeUnit.SECONDS.sleep(1);
                break;
            }
        } while (true);
    }
}