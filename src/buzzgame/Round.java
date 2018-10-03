package buzzgame;

import java.util.Random;

/**
 *
 * Manages the current status of each individual question and round.
 *
 * @author Τιμολέων Λατινόπουλος
 * @author Δημήτρης Σκουλής
 */
public class Round {

    private Random random;
    private int answer;
    private boolean[] displayedAnswers;
    private String[] questionsAndAnswers;
    private RoundData current;

    /**
     *
     * The constructor of the class.
     *
     * @param current contains all the data of the current game inside an
     * instance of the class RoundData
     */
    public Round(RoundData current) {
        random = new Random();
        questionsAndAnswers = new String[6];
        this.current = current;
    }

    /**
     *
     * Gives the current category of the current question.
     *
     * @return the current category of the current question
     */
    public int getCategory() {
        return current.getCategory();
    }

    /**
     *
     * Chooses randomly a category and a question from that category and checks
     * if it has been played. If it has, it chooces another pair of random
     * numbers. If it hasn't, it sets the specific question as played.
     */
    public void getRandom() {
        do {
            current.setCategory(random.nextInt(5));
            current.setQuestion(random.nextInt(23));
        } while (current.getRound(6) != null);//null indicates that the question hasn't been played

        current.setRound();//"" indicates that the question has been played
    }

    /**
     *
     * Creates a String[] variable and inserts the question in the first place
     * and the answers in random order after that. It also saves the correct
     * answer of the current question.
     *
     * @return the StringBuilder variable
     */
    public String[] printRound() {
        //resets the displayedAnswers and the str
        displayedAnswers = new boolean[4];

        questionsAndAnswers[0] = current.getRound(0);
        for (int j = 1; j <= 4; j++) {
            do {
                answer = random.nextInt(4);
            } while (displayedAnswers[answer]);

            displayedAnswers[answer] = true;
            questionsAndAnswers[j] = current.getRound(answer + 1);

            if (answer == 0) {
                current.setCorrectChoice(j);//keeps the correct answer
            }
        }
        questionsAndAnswers[5] = current.getRound(5);
        return questionsAndAnswers;
    }

    /**
     *
     * Checks if the answer given is correct.
     *
     * @param aChoice contains the choice given from the player
     * @return true if the answer given is correct, false if it's wrong
     */
    public boolean isChoiceCorrect(int aChoice) {
        if (aChoice == current.getCorrectChoice()) {
            return true;
        }
        return false;
    }

    /**
     *
     * Picks a number of a specific type of round in a way so that it keeps a
     * normal sequence and doesn't repeat itself.
     *
     * @param flag is true if the different types of rounds have been played
     * once in sequence and then it becomes false to indicate the start of a new
     * sequence
     * @param playedRounds indicates if a value has been played in this specific
     * sequence or not
     * @return the type of round that will be played
     */
    public int chooseRound(boolean flag, boolean playedRounds[], int limit) {
        int roundNum;
        if (flag) {
            for (int j = 0; j < limit; j++) {
                playedRounds[j] = false;
            }
        }
        do {
            roundNum = random.nextInt(limit);
        } while (playedRounds[roundNum]);
        playedRounds[roundNum] = true;
        return roundNum;
    }

    /**
     *
     * Resets the sequence of rounds by returning true if it has been finished.
     * Otherwise it returns false.
     *
     * @param playedRounds indicates if a value has been played in this specific
     * sequence or not
     * @return true if the sequence has been completed or false otherwise
     */
    public boolean resetRounds(boolean playedRounds[], int limit) {
        boolean flag = false;
        int count = 0;
        for (int j = 0; j < limit; j++) {
            if (playedRounds[j]) {
                count++;
            }
        }
        if (count == limit) {
            flag = true;
        }
        return flag;
    }
}
