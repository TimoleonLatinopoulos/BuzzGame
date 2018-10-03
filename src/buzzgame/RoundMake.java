package buzzgame;

import java.util.Random;

/**
 *
 * This class manages every variable that will be used in the frame of the game,
 * such as the question and its possible answers, the round etc.
 *
 * @author Τιμολέων Λατινόπουλος
 * @author Δημήτρης Σκουλής
 */
public class RoundMake {

    private Round round;
    private RoundData current;
    private String[] questionsAndAnswers;
    private boolean playedRounds[], flag;
    private int roundNum, limit;

    /**
     *
     * The constructor of the class.
     *
     * @param language is language that the user has selected if true the
     * language is english and if false greek
     * @param limit determines how many types of rounds there are in this game
     * mode. The single game mode has three type of rounds. On the other hand,
     * the multiplayer game mode has five type of rounds
     *
     */
    public RoundMake(boolean language, int limit) {
        this.limit = limit;
        playedRounds = new boolean[limit];
        questionsAndAnswers = new String[6];
        flag = false;
        current = new RoundData(language);
        round = new Round(current);
    }

    /**
     *
     * Gives the type of the round that is currently playing.
     *
     * @return the type of the round that is currently playing
     */
    public int getRoundNum() {
        return roundNum;
    }

    /**
     *
     * Gives the category of the question.
     *
     * @return the category of the question
     */
    public int getCategory() {
        return round.getCategory();
    }

    /**
     *
     * Gives the question or one of the four possible answers of the question.
     *
     * @param num determines what it will be returned question or possible
     * answer of the question
     * @return the question or one of the four possible answers of the question
     */
    public String getQuestionsAndAnswers(int num) {
        return questionsAndAnswers[num];
    }

    /**
     *
     * Checks if the choice that the player selected is the correct one or not.
     *
     * @param num is the choice that the player selected as the correct answer
     * @return if the choice that the player selected is the correct one or not
     */
    public boolean checkChoice(int num) {
        return round.isChoiceCorrect(num);
    }

    /**
     *
     * Checks how many questions have been played in that round and calls the
     * appropriate fuctions.
     *
     * @param questions the number of the questions that have been played in
     * that round
     */
    public void startRound(int questions) {
        if (questions == 0) {
            roundNum = round.chooseRound(flag, playedRounds, limit);
        }
        round.getRandom();
        questionsAndAnswers = round.printRound();
        if (questions == 0) {
            flag = round.resetRounds(playedRounds, limit);
        }
    }
}
