package buzzgame;

/**
 *
 * Contains the data for each player of the game.
 *
 * @author Τιμολέων Λατινόπουλος
 * @author Δημήτρης Σκουλής
 */
public class Player {

    private int points;

    /**
     *
     * The empty constructor of the class.
     */
    public Player() {
        points = 0;
    }

    /**
     *
     * Adds a specific amount of points to the player's score.
     *
     * @param points the points of the player to be added
     */
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     *
     * Subtracts a specific amount of points from the player's score.
     *
     * @param points the points of the player to be subtracted
     */
    public void subPoints(int points) {
        this.points -= points;
    }

    /**
     *
     * Gives the current points of the player.
     *
     * @return the current points of the player
     */
    public int getPoints() {
        return points;
    }
}
