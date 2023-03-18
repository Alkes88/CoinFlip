package coinflip.main;

import static coinflip.main.Game.printMessage;

public class TopScoreKeeper {
    private int topScore;
    public TopScoreKeeper() {
        topScore = 0;
    }

    public void updateTopScore(int score) {
        if (score > topScore) {
            topScore = score;
            printMessage("New TopScore: "+topScore+"!");
        }
    }
}
