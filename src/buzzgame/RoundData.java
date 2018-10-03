package buzzgame;

/**
 * Contains the data of the game, as well as the current state of the question
 * played.
 *
 * @author Τιμολέων Λατινόπουλος
 * @author Δημήτρης Σκουλής
 */
public class RoundData {

    private int category, question, correctChoice;
    private Questions data;
    private String[][][] round;

    /**
     *
     * The constructor of the class.
     *
     * @param language defines the language choosen by the player, which is
     * english if true or greek if false
     */
    public RoundData(boolean language) {
        data = new Questions();
        data.createData(language);
        round = data.getData();
    }

    /**
     *
     * Gives the current category of the current question.
     *
     * @return the current category of the current question
     */
    public int getCategory() {
        return category;
    }

    /**
     *
     * Gives the current question or answers.
     *
     * @param num decides between the question, the answers or the picture if
     * there is one according to the position they are located in round[][][]
     * @return the current question or answers
     */
    public String getRound(int num) {
        return round[category][question][num];
    }

    /**
     *
     * Gives the correct choice of the current question.
     *
     * @return the correct choice of the current question
     */
    public int getCorrectChoice() {
        return correctChoice;
    }

    /**
     *
     * Sets the current category to the specified number.
     *
     * @param category decides which of the five categories of questions will be
     * choosen
     */
    public void setCategory(int category) {
        this.category = category;
    }

    /**
     *
     * Sets the current question to the specified number.
     *
     * @param question decides which of the twenty questions of a specified
     * category will be choosen
     */
    public void setQuestion(int question) {
        this.question = question;
    }

    /**
     *
     * Sets the current question as played, which is indicated by the 7th
     * position of the round[][][] matrix (set as "", originally null).
     */
    public void setRound() {
        this.round[category][question][6] = "";
    }

    /**
     *
     * Sets the correct choice of the current question.
     *
     * @param correctChoice is the correct choice of the current question
     */
    public void setCorrectChoice(int correctChoice) {
        this.correctChoice = correctChoice;
    }
}
