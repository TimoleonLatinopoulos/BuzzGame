package buzzgame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The class which does all the fuctions for the file which stores the
 * highscores
 *
 * @author Τιμολέων Λατινόπουλος
 * @author Δημήτριος Σκουλής
 */
public class IOClass {

    private BufferedWriter out;
    private BufferedReader in;
    private String forThefile1, forThefile2, highscore1Player, player1Wins, player2Wins;
    private int highscore, wins1, wins2;
    private Player player1, player2;

    /**
     *
     * The constructor of the class.
     *
     * @param player1 the parameter which gives access to the score of the first
     * player
     * @param player2 the parameter which gives access to the score of the
     * second player
     */
    public IOClass(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     *
     * It resets the file score.txt which contains the highscores by giving the
     * value 0 to both highscore of the single player game mode and score
     * between wins of the first player versus second player's wins.
     */
    public void resetScores() {
        try {
            out = new BufferedWriter(new FileWriter("score.txt"));
        } catch (IOException e) {
            System.out.println("error");
        }
        try {
            out.write("Single player - Highscore");
            out.flush();
            out.newLine();
            out.write("0");
            out.flush();
            out.newLine();
            out.write("Multiplayer - Wins player one vs player two");
            out.flush();
            out.newLine();
            out.write("0");
            out.flush();
            out.newLine();
            out.write("0");
            out.flush();
            out.newLine();
        } catch (IOException e) {
            System.out.println("error");
        }
        try {
            out.close();
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    /**
     * 
     * It changes the scores in file score according to the type of the game. If
     * the game mode is single player, at the end of the game it checks if the
     * player has made a new highscore and if he does it is changed in the file
     * score. If the game mode is multiplayer, at the end of the game it compares
     * the scores of the two players and adds one more win to the player who won
     * the game.
     *
     * @param numOfPlayers the number of players playing the game currently
     */
    public void writeScores(int numOfPlayers) {
        try {
            in = new BufferedReader(new FileReader("score.txt"));
        } catch (IOException er) {
            System.out.println("error");
        }
        try {
            forThefile1 = in.readLine();
            highscore1Player = in.readLine();
            forThefile2 = in.readLine();
            player1Wins = in.readLine();
            player2Wins = in.readLine();
        } catch (IOException er) {
            System.out.println("error");
        }
        if (numOfPlayers == 1) {
            highscore = Integer.parseInt(highscore1Player);
            if (highscore < player1.getPoints()) {
                highscore = player1.getPoints();
                highscore1Player = Integer.toString(highscore);
            }
        } else {
            if (player1.getPoints() > player2.getPoints()) {
                wins1 = Integer.parseInt(player1Wins) + 1;
                player1Wins = Integer.toString(wins1);
            } else {
                wins2 = Integer.parseInt(player2Wins) + 1;
                player2Wins = Integer.toString(wins2);
            }
        }
        try {
            in.close();
        } catch (IOException er) {
            System.out.println("error");
        }
        try {
            out = new BufferedWriter(new FileWriter("score.txt"));
        } catch (IOException er) {
            System.out.println("error");
        }
        try {
            out.write(forThefile1);
            out.flush();
            out.newLine();
            out.write(highscore1Player);
            out.flush();
            out.newLine();
            out.write(forThefile2);
            out.flush();
            out.newLine();
            out.write(player1Wins);
            out.flush();
            out.newLine();
            out.write(player2Wins);
            out.flush();
            out.newLine();
        } catch (IOException er) {
            System.out.println("error");
        }
        try {
            out.close();
        } catch (IOException er) {
            System.out.println("error");

        }
    }
}
