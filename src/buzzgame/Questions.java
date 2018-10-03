package buzzgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

/**
 * Reads and contains the data of the questions and the answers. A
 * three-dimensional array is used for the data. The first dimension determines
 * the category, the second the question and the third contains the data. The
 * first place of the third dimension contains the question, the second contains
 * the correct answer, the third to fifth contain the rest of the answers and
 * the sixth contains null to indicate that the question hasn't been played
 *
 * @author Τιμολέων Λατινόπουλος
 * @author Δημήτρης Σκουλής
 */
public class Questions {

    private String[][][] data;
    private BufferedReader in;

    /**
     *
     * The empty constructor of the class.
     */
    public Questions() {
        data = new String[5][23][7];
    }

    /**
     *
     * Opens the appropriate stream and saves the questions and the answers in
     * the matrix created.
     */
    public void createData(boolean language) {
        if (language == false) {
            try {
                in = new BufferedReader(new FileReader("ερωτησεις.txt"));
            } catch (IOException e) {
                System.out.println("Can't open file");
            }
        } else {
            try {
                in = new BufferedReader(new FileReader("questions.txt"));
            } catch (IOException e) {
                System.out.println("Can't open file");
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 20; j++) {
                for (int k = 0; k < 5; k++) {
                    try {
                        data[i][j][k] = in.readLine();
                    } catch (IOException e) {
                        System.out.println("Can't read file");
                    }
                }
            }
        }
        
        if (language == false) {
            try {
                in = new BufferedReader(new FileReader("εικόνες.txt"));
            } catch (IOException e) {
                System.out.println("Can't open file");
            }
        } else {
            try {
                in = new BufferedReader(new FileReader("pictures.txt"));
            } catch (IOException e) {
                System.out.println("Can't open file");
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 20; j < 23; j++) {
                for (int k = 0; k < 6; k++) {
                    try {
                        data[i][j][k] = in.readLine();
                    } catch (IOException e) {
                        System.out.println("Can't read file");
                    }
                }
            }
        }
    }

    /**
     *
     * Gives the matrix containing the questions and answers.
     *
     * @return the matrix containing the data
     */
    public String[][][] getData() {
        return data;
    }
}
