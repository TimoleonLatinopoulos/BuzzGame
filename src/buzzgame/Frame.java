package buzzgame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.Timer;

/**
 *
 * @author Τιμολέων Λατινόπουλος
 * @author Δημήτρης Σκουλής
 */
public class Frame extends JFrame implements KeyListener {

    private JFrame frame;
    private FlowLayout flow;
    private JButton next, button[];
    private JLabel text, text2, scoreText, scoreText2, timerText, imageLabel, thermometer1Text, thermometer2Text;
    private JTextArea question;
    private JTextField score, score2, timerField, thermometer1Field, thermometer2Field;
    private JMenuItem instructions, exit, resetHighscores, showHighscores;
    private JPanel center, grid, grid2, grid3, borderGrid, top, border, languagePane, enOrGrPane, playerPane;
    private ActionListener action;
    private ImageIcon image;
    private BufferedReader in;
    private RoundMake round;
    private IOClass io;
    private String category;
    private Player player1, player2;
    private Timer timer;
    private int keyID, bet1, bet2, action1, action2, count1, count2;
    private int rounds, questions, count, numOfPlayers, thermometer1 = 5, thermometer2 = 5;
    private boolean flagPlayer = true, questionsFlag = true, betFlag = true, thermometerFlag = false, player1Answered = false, player2Answered = false, first1 = false, first2 = false, first = true, time1 = false, time2 = false;
    private String input;

    /**
     *
     * The empty constructor of the class.
     *
     * Ιt creates the frame of the game and its properties. It also creates the
     * menu which the game has.
     */
    public Frame() {
        player1 = new Player();
        player2 = new Player();
        io = new IOClass(player1, player2);

        frame = new JFrame();
        frame.setTitle("Buzz");
        languagePane = new chooseLanguagePane();

        //Menu Bar
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        JMenu menu = new JMenu("File");
        menubar.add(menu);
        instructions = new JMenuItem("Instructions");
        menu.add(instructions);
        menu.addSeparator();
        showHighscores = new JMenuItem("Show highscores");
        menu.add(showHighscores);
        resetHighscores = new JMenuItem("Reset highscores");
        menu.add(resetHighscores);
        menu.addSeparator();
        exit = new JMenuItem("Exit");
        menu.add(exit);
        instructions.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    in = new BufferedReader(new FileReader("instructions.txt"));
                    input = "";
                    for (String line; (line = in.readLine()) != null;) {
                        input += line + "\n";
                    }
                    JOptionPane.showMessageDialog(frame, input, "Instructions", INFORMATION_MESSAGE);
                } catch (IOException e) {
                    System.out.println("Can't open file");
                }
            }
        });
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        resetHighscores.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int reply = JOptionPane.showConfirmDialog(null, "Would you like to reset the scores?", "Reset scores", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    io.resetScores();
                }
            }
        });
        showHighscores.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    in = new BufferedReader(new FileReader("score.txt"));
                    input = "";
                    for (String line; (line = in.readLine()) != null;) {
                        input += line + "\n";
                    }
                    JOptionPane.showMessageDialog(frame, input, "Scores", INFORMATION_MESSAGE);
                } catch (IOException e) {
                    System.out.println("Can't open file");
                }
            }
        });

        //Setup frame
        frame.setResizable(true);
        frame.setSize(1050, 450);
        frame.setLocationRelativeTo(null);
        frame.add(languagePane);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        //KeyListener
        addKeyListener(this);
        frame.setFocusable(true);
        frame.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //No fuction to this method
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyID = e.getKeyChar();
        if (numOfPlayers == 2) {
            if (questionsFlag == false) {
                playerAction();
            } else if (betFlag == false) {
                betAction();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //No fuction to this method
    }

    /**
     *
     * During multiplayer game, it takes the bets of the players during the bet
     * round from the keyboard.
     */
    public void betAction() {
        if (keyID >= 49 && keyID <= 52 && player1Answered == false) {
            action1 = keyID - 48;
            player1Answered = true;
        } else if (keyID >= 54 && keyID <= 57 && player2Answered == false) {
            action2 = keyID - 53;
            player2Answered = true;
        }

        if (player1Answered == true && player2Answered == true) {
            if (action1 == 1) {
                bet1 = 250;
            } else if (action1 == 2) {
                bet1 = 500;
            } else if (action1 == 3) {
                bet1 = 750;
            } else if (action1 == 4) {
                bet1 = 1000;
            }
            if (action2 == 1) {
                bet2 = 250;
            } else if (action2 == 2) {
                bet2 = 500;
            } else if (action2 == 3) {
                bet2 = 750;
            } else if (action2 == 4) {
                bet2 = 1000;

            }
            next.setVisible(true);

            betFlag = false;
            action1 = -1;
            action2 = -1;
            player1Answered = false;
            player2Answered = false;
        }
    }

    /**
     *
     * During multiplayer game, it takes the choices of the players and
     * accordingly adds or subtracts points from each one depending on the type
     * of round that is played.
     */
    public void playerAction() {
        if (keyID >= 49 && keyID <= 52 && player1Answered == false) {
            action1 = keyID - 48;
            player1Answered = true;
            if (first2 == false && round.checkChoice(action1)) {
                first1 = true;
            }
        } else if (keyID >= 54 && keyID <= 57 && player2Answered == false) {
            action2 = keyID - 53;
            player2Answered = true;
            if (first1 == false && round.checkChoice(action2)) {
                first2 = true;
            }
        }

        switch (round.getRoundNum()) {
            case 0:
                if (player1Answered == true && player2Answered == true) {
                    if (round.checkChoice(action1) == true) {
                        player1.addPoints(1000);
                    }
                    if (round.checkChoice(action2) == true) {
                        player2.addPoints(1000);
                    }
                }
                break;
            case 1:
                if (player1Answered == true && player2Answered == true) {
                    if (round.checkChoice(action1) == true) {
                        player1.addPoints(bet1);
                    } else if (round.checkChoice(action1) == false) {
                        player1.subPoints(bet1);
                    }
                    if (round.checkChoice(action2) == true) {
                        player2.addPoints(bet2);
                    } else if (round.checkChoice(action2) == false) {
                        player2.subPoints(bet2);
                    }
                }
                break;
            case 2:
                if (round.checkChoice(action1) == true) {
                    count1 = count;
                }
                if (round.checkChoice(action2) == true) {
                    count2 = count;
                }
                if (round.checkChoice(action1) == true && time1 == false) {
                    player1.addPoints((int) (count1 * 0.2));
                    time1 = true;
                }
                if (round.checkChoice(action2) == true && time2 == false) {
                    player2.addPoints((int) (count2 * 0.2));
                    time2 = true;
                }
                if (player1Answered == true && player2Answered == true) {
                    time1 = false;
                    time2 = false;
                    timer.stop();
                }
                break;
            case 3:
                if (player1Answered == true && player2Answered == true) {
                    if (round.checkChoice(action1) == true) {
                        if (first1 == true) {
                            player1.addPoints(1000);
                        } else {
                            player1.addPoints(500);
                        }
                    }
                    if (round.checkChoice(action2) == true) {
                        if (first2 == true) {
                            player2.addPoints(1000);
                        } else {
                            player2.addPoints(500);
                        }
                    }
                }
                break;
            case 4:
                if (player1Answered == true && player2Answered == true) {
                    if (round.checkChoice(action1) == true) {
                        thermometer1--;
                    }
                    if (round.checkChoice(action2) == true) {
                        thermometer2--;
                    }
                }
                break;
        }

        if (player1Answered == true && player2Answered == true) {
            endOfQuestion();
        }
    }

    /**
     *
     * During multiplayer game, it changes the color of the answers and sets all
     * the textFields to their updated values. It also adds points at the end of
     * the thermometer round and resets some values that are used for each
     * round.
     */
    public void endOfQuestion() {
        if (action1 != 0 && !round.checkChoice(action1)) {
            button[action1 - 1].setBackground(Color.red);
        }
        if (action2 != 0 && !round.checkChoice(action2)) {
            button[action2 - 1].setBackground(Color.red);
        }
        for (int i = 1; i <= 4; i++) {
            if (round.checkChoice(i)) {
                button[i - 1].setBackground(Color.green);
            }
        }
        if (thermometerFlag == true) {
            thermometer1Field.setText("" + thermometer1);
            thermometer2Field.setText("" + thermometer2);
            thermometerFlag = false;
        }

        next.setVisible(true);
        for (int i = 0; i < 4; i++) {
            button[i].setEnabled(false);
        }

        if (round.getRoundNum() != 4) {
            if (questions == 4) {
                rounds++;
                questions = -1;
            }
        } else {
            if (thermometer1 == 0 || thermometer2 == 0) {
                rounds++;
                questions = -1;
                if (thermometer1 == thermometer2) {
                    if (first1 == true) {
                        player1.addPoints(5000);
                    } else {
                        player2.addPoints(5000);
                    }
                } else {
                    if (thermometer1 == 0) {
                        player1.addPoints(5000);
                    } else if (thermometer2 == 0) {
                        player2.addPoints(5000);
                    }
                }
            }
        }
        if (rounds == 5) {
            io.writeScores(numOfPlayers);
        }
        score.setText("" + player1.getPoints());
        score2.setText("" + player2.getPoints());

        time1 = false;
        time2 = false;
        questionsFlag = true;
        action1 = 0;
        action2 = 0;
        player1Answered = false;
        player2Answered = false;
        first1 = false;
        first2 = false;
    }

    /**
     *
     * Contains the part of the game in which the user selects the language.
     */
    public class chooseLanguagePane extends JPanel {

        public chooseLanguagePane() {
            //Main Layout
            flow = new FlowLayout(FlowLayout.CENTER, 10, 50);//flow layout

            //Text Label
            text = new JLabel("Choose language / Επέλεξε γλώσσα:");
            text.setFont(new java.awt.Font("Tahoma", 0, 18));
            text.setHorizontalAlignment(SwingConstants.CENTER);

            //Language Panel
            center = new JPanel();
            center.setLayout(flow);
            //Button Panel
            grid = new JPanel();
            grid.setLayout(new GridLayout(1, 2, 30, 0));//Grid Layout

            grid2 = new JPanel();
            grid2.setLayout(new GridLayout(2, 1, 30, 50));//Grid Layout

            //New Buttons
            button = new JButton[2];
            button[0] = new JButton("English");
            button[0].setActionCommand("button1Command");
            button[1] = new JButton("Ελληνικά");
            button[1].setActionCommand("button2Command");

            //Action Listener
            action = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    String action = e.getActionCommand();
                    if (action.equals("button1Command")) {
                        enOrGrPane = new languagePane(true);
                    } else if (action.equals("button2Command")) {
                        enOrGrPane = new languagePane(false);
                    }
                    frame.remove(languagePane);
                    frame.add(enOrGrPane);
                    frame.revalidate();
                    frame.repaint();
                }
            };
            for (int i = 0; i < 2; i++) {
                button[i].addActionListener(action);
                grid.add(button[i]);
            }

            //Add to Panel
            grid2.add(text);
            grid2.add(grid);
            center.add(grid2);
            this.add(center);
        }
    }

    /**
     *
     * Contains the part of the game in which the game mode is selected.
     */
    public class languagePane extends JPanel {

        public languagePane(final boolean language) {
            //Text Label
            if (language == true) {
                text.setText("  Choose the number of players:  ");
            } else {
                text.setText("Διάλεξε τον αριθμό των παικτών:");
            }
            this.add(text);

            center.removeAll();
            grid.removeAll();
            grid2.removeAll();

            //New Buttons
            if (language == true) {
                button[0] = new JButton("1 player");
                button[1] = new JButton("2 players");
            } else {
                button[0] = new JButton("1 παίκτης");
                button[1] = new JButton("2 παίκτες");
            }
            button[0].setActionCommand("button1Command");
            button[1].setActionCommand("button2Command");

            //Action Listener
            action = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    String action = e.getActionCommand();
                    if (action.equals("button1Command")) {
                        playerPane = new playerPanel(language, true, 0, 0, 1);
                        frame.remove(enOrGrPane);
                        frame.add(playerPane);
                        frame.revalidate();
                        frame.repaint();
                    } else if (action.equals("button2Command")) {
                        playerPane = new playerPanel(language, true, 0, 0, 2);
                        frame.remove(enOrGrPane);
                        frame.add(playerPane);
                        frame.revalidate();
                        frame.repaint();
                    }
                }
            };
            for (int i = 0; i < 2; i++) {
                button[i].addActionListener(action);
                grid.add(button[i]);
            }

            //Add to Panel
            grid2.add(text);
            grid2.add(grid);
            center.add(grid2);
            this.add(center);
        }
    }

    /**
     *
     * It contains the main panel of the game, the panel that contains the
     * questions and the possible answers of them.
     */
    public class playerPanel extends JPanel {

        private boolean lang, flag;

        /**
         *
         * It is the main method of the whole program. It has all the
         * information about the program such as the questions that have been
         * used, the rounds that have been played, etc. It gets the type of
         * round and calls its method to be set the properties of the panel of
         * this round.
         *
         * @param aLang symbolizes the language that the user has selected if
         * true the language is english and if false greek
         * @param aFlag symbolizes if it is the first question of the game
         * @param aRounds symbolizes how many rounds have been played
         * @param aQuestions contains the number of questions that have been
         * played in this round
         * @param aNumOfPlayers symbolizes the number of players
         */
        public playerPanel(boolean aLang, boolean aFlag, int aRounds, int aQuestions, int aNumOfPlayers) {
            //local variables
            lang = aLang;
            flag = aFlag;
            rounds = aRounds;
            questions = aQuestions;
            numOfPlayers = aNumOfPlayers;

            border = new JPanel();
            borderGrid = new JPanel();
            borderGrid.setLayout(new GridLayout(2, 1, 10, 10));

            grid2 = new JPanel();
            grid2.setLayout(new GridLayout(numOfPlayers, 2, 10, 10));//Grid Layout

            score = new JTextField("" + player1.getPoints());
            score.setEnabled(false);
            score.setFont(new java.awt.Font("Tahoma", 0, 16));
            if (lang == true) {
                scoreText = new JLabel("Score 1:");
            } else {
                scoreText = new JLabel("Σκορ 1:");
            }
            scoreText.setFont(new java.awt.Font("Tahoma", 0, 16));

            if (numOfPlayers == 2) {
                score2 = new JTextField("" + player2.getPoints());
                score2.setEnabled(false);
                score2.setFont(new java.awt.Font("Tahoma", 0, 16));
                if (lang == true) {
                    scoreText2 = new JLabel("Score 2:");
                } else {
                    scoreText2 = new JLabel("Σκορ 2:");
                }
                scoreText2.setFont(new java.awt.Font("Tahoma", 0, 16));
            }

            grid2.add(scoreText);
            grid2.add(score);
            if (numOfPlayers == 2) {
                grid2.add(scoreText2);
                grid2.add(score2);
            }

            if (flag) {
                if (numOfPlayers == 1) {
                    round = new RoundMake(lang, 3);
                } else if (numOfPlayers == 2) {
                    round = new RoundMake(lang, 5);
                }
                flag = false;
            }

            round.startRound(questions);

            //Main Panel
            top = new JPanel();
            top.setLayout(new GridLayout(4, 1, 30, 20));

            //Round Type
            switch (round.getRoundNum()) {
                case 0:
                    makeCorrectChoicePanel();
                    break;
                case 1:
                    makeBetPanel();
                    break;
                case 2:
                    makeTimerPanel();
                    break;
                case 3:
                    makeQuickAnswer();
                    break;
                case 4:
                    makeΤhermometer();
                    break;
            }

        }

        /**
         *
         * Creates the correct choice round.
         */
        public void makeCorrectChoicePanel() {
            if (lang == true) {
                text.setText("Round " + (rounds + 1) + ": Correct Answer");
            } else {
                text.setText("Γύρος " + (rounds + 1) + ": Σωστή Απάντηση");
            }
            top.add(text);
            border.add(top, BorderLayout.CENTER);

            makeQuestionsAndAnswersPanel();
        }

        /**
         *
         * Creates the bet panel and the bet round.
         */
        public void makeBetPanel() {
            betFlag = false;

            if (lang == true) {
                text.setText("Round " + (rounds + 1) + ": Bet");
            } else {
                text.setText("Γύρος " + (rounds + 1) + ": Ποντάρισμα");
            }
            top.add(text);

            //Button Panel
            grid = new JPanel();
            grid.setLayout(new GridLayout(2, 2, 30, 20));//Grid Layout

            switch (round.getCategory()) {
                case 0:
                    if (lang == true) {
                        category = "Sports";
                    } else {
                        category = "Αθλητισμός";
                    }
                    break;
                case 1:
                    if (lang == true) {
                        category = "Geography";
                    } else {
                        category = "Γεωγραφία";
                    }
                    break;
                case 2:
                    if (lang == true) {
                        category = "Science";
                    } else {
                        category = "Επιστήμη";
                    }
                    break;
                case 3:
                    if (lang == true) {
                        category = "Movies";
                    } else {
                        category = "Ταινίες";
                    }
                    break;
                case 4:
                    if (lang == true) {
                        category = "Arts";
                    } else {
                        category = "Τέχνη";
                    }
                    break;
            }

            if (lang == true) {
                text2 = new JLabel("The Category is " + category + ". How much do you bet?");
            } else {
                text2 = new JLabel("Η κατηγορία είναι " + category + ". Πόσους πόντους θες να ποντάρεις;");
            }
            text2.setFont(new java.awt.Font("Tahoma", 0, 16));
            text2.setHorizontalAlignment(SwingConstants.CENTER);
            top.add(text2);

            button = new JButton[4];

            button[0] = new JButton("250");
            button[1] = new JButton("500");
            button[2] = new JButton("750");
            button[3] = new JButton("1000");
            for (int i = 0; i < 4; i++) {
                button[i].setActionCommand("" + (i + 1));
            }

            action = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    int action = Integer.parseInt(e.getActionCommand());
                    if (action == 1) {
                        bet1 = 250;
                    } else if (action == 2) {
                        bet1 = 500;
                    } else if (action == 3) {
                        bet1 = 750;
                    } else if (action == 4) {
                        bet1 = 1000;
                    }
                    if (numOfPlayers == 1) {
                        grid.removeAll();
                        grid.revalidate();
                        grid.repaint();

                        top.remove(text2);
                        top.remove(grid);
                        top.remove(next);
                        top.revalidate();
                        top.repaint();
                        makeQuestionsAndAnswersPanel();
                    } else {
                        next.setVisible(true);
                    }
                }
            };
            for (int i = 0; i < 4; i++) {
                if (numOfPlayers == 1) {
                    button[i].addActionListener(action);
                }
                grid.add(button[i]);
            }

            if (lang == true) {
                next = new JButton("Go to question");
            } else {
                next = new JButton("Πήγαινε στην Ερώτηση");
            }
            next.setVisible(false);
            next.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    grid.removeAll();
                    grid.revalidate();
                    grid.repaint();

                    top.remove(text2);
                    top.remove(grid);
                    top.remove(next);
                    top.revalidate();
                    top.repaint();
                    makeQuestionsAndAnswersPanel();
                }
            });

            //Add to panel
            top.add(grid);
            top.add(next);
            border.add(top, BorderLayout.CENTER);
            this.add(border);
        }

        /**
         *
         * Creates the timer that is going to be used in the timer round and
         * sets the properties for the panel.
         */
        public void makeTimerPanel() {
            timerField = new JTextField();
            timerField.setText("5000");
            count = 5000;
            timer = new Timer(100, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (count <= 0) {
                        ((Timer) e.getSource()).stop();
                        next.setVisible(true);
                        for (int i = 0; i < 4; i++) {
                            button[i].setEnabled(false);
                        }
                        if (numOfPlayers == 2) {
                            endOfQuestion();
                        } else {
                            if (questions == 4) {
                                rounds++;
                                questions = -1;
                            }
                            if (rounds == 5) {
                                io.writeScores(numOfPlayers);
                            }
                        }
                    } else {
                        timerField.setText(Integer.toString(count));
                        count -= 100;
                    }
                    timerField.setText(Integer.toString(count));
                }

            });
            timer.start();
            if (lang == true) {
                text.setText("Round " + (rounds + 1) + ": Timer");
            } else {
                text.setText("Γύρος " + (rounds + 1) + ": Χρονόμετρο");
            }
            top.add(text);

            grid3 = new JPanel();
            grid3.setLayout(new GridLayout(1, 2, 10, 10));

            timerField.setEnabled(false);
            timerField.setFont(new java.awt.Font("Tahoma", 0, 16));
            if (lang == true) {
                timerText = new JLabel("Time:");
            } else {
                timerText = new JLabel("Χρόνος:");
            }
            timerText.setFont(new java.awt.Font("Tahoma", 0, 16));
            grid3.add(timerText);
            grid3.add(timerField);

            borderGrid.add(grid3);
            border.add(borderGrid, BorderLayout.EAST);

            makeQuestionsAndAnswersPanel();
        }

        /**
         *
         * Creates the quick answer round.
         */
        public void makeQuickAnswer() {
            if (lang == true) {
                text.setText("Round " + (rounds + 1) + ": Quick answer");
            } else {
                text.setText("Γύρος " + (rounds + 1) + ": Γρήγορη απάντηση");
            }
            top.add(text);
            makeQuestionsAndAnswersPanel();
        }

        /**
         *
         * Creates the thermometer round.
         */
        public void makeΤhermometer() {
            thermometerFlag = true;

            if (lang == true) {
                text.setText("Round " + (rounds + 1) + ": Thermometer");
            } else {
                text.setText("Γύρος " + (rounds + 1) + ": Θερμόμετρο");
            }
            top.add(text);

            grid3 = new JPanel();
            grid3.setLayout(new GridLayout(2, 2, 10, 10));

            if (first == true) {
                thermometer1Field = new JTextField();
                thermometer1Field.setText("5");
                thermometer2Field = new JTextField();
                thermometer2Field.setText("5");
                first = false;
            }

            thermometer1Field.setEnabled(false);
            thermometer1Field.setFont(new java.awt.Font("Tahoma", 0, 16));
            if (lang == true) {
                thermometer1Text = new JLabel("Thermometer 1:");
            } else {
                thermometer1Text = new JLabel("Θερμόμετρο 1:");
            }
            thermometer1Text.setFont(new java.awt.Font("Tahoma", 0, 16));
            grid3.add(thermometer1Text);
            grid3.add(thermometer1Field);

            thermometer2Field.setEnabled(false);
            thermometer2Field.setFont(new java.awt.Font("Tahoma", 0, 16));
            if (lang == true) {
                thermometer2Text = new JLabel("Thermometer 2:");
            } else {
                thermometer2Text = new JLabel("Θερμόμετρο 2:");
            }
            thermometer2Text.setFont(new java.awt.Font("Tahoma", 0, 16));
            grid3.add(thermometer2Text);
            grid3.add(thermometer2Field);

            borderGrid.add(grid3);
            border.add(borderGrid, BorderLayout.EAST);
            makeQuestionsAndAnswersPanel();
        }

        /**
         *
         * This method is called during each round during single player game. It
         * sets the panel with the question and the answers, gets the choice of
         * the player and then it decides if the player wins or loses any
         * points. When it is done, it calls the constructor for a new question
         * or a new round be made.
         */
        public void makeQuestionsAndAnswersPanel() {
            //Button Panel
            grid = new JPanel();
            grid.setLayout(new GridLayout(2, 2, 30, 10));//Grid Layout

            questionsFlag = false;

            //Question TextArea
            question = new JTextArea(round.getQuestionsAndAnswers(0));
            question.setFont(new java.awt.Font("Tahoma", 0, 14));
            question.setEditable(false);
            question.setEnabled(false);
            question.setLineWrap(true);
            question.setWrapStyleWord(true);
            question.setBackground(Color.black);
            question.setAutoscrolls(true);
            top.add(question);

            button = new JButton[4];

            //New buttons
            for (int i = 0; i < 4; i++) {
                button[i] = new JButton(round.getQuestionsAndAnswers(i + 1));
                button[i].setActionCommand("" + (i + 1));
            }

            if ((rounds >= 4 && questions == 4 && thermometerFlag == false) || (rounds >= 5 && questions == -1 && thermometerFlag == false)) {
                if (lang == true) {
                    next = new JButton("End quiz");
                } else {
                    next = new JButton("Τέλος παιχνιδιού");
                }
            } else {
                if (lang == true) {
                    next = new JButton("Next Question");
                } else {
                    next = new JButton("Επόμενη Ερώτηση");
                }
            }
            next.setVisible(false);
            next.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (rounds != 5) {
                        frame.remove(playerPane);
                        playerPane = new playerPanel(lang, flag, rounds, ++questions, numOfPlayers);
                        frame.add(playerPane);
                        frame.revalidate();
                        frame.repaint();
                    } else {
                        System.exit(0);
                    }
                }
            });

            //Action Listener
            action = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    int action = Integer.parseInt(e.getActionCommand());
                    if (!round.checkChoice(action)) {
                        button[action - 1].setBackground(Color.red);
                    }
                    for (int i = 1; i <= 4; i++) {
                        if (round.checkChoice(i)) {
                            button[i - 1].setBackground(Color.green);
                        }
                    }

                    switch (round.getRoundNum()) {
                        case 0:
                            if (round.checkChoice(action) == true) {
                                player1.addPoints(1000);
                            }
                            break;
                        case 1:
                            if (round.checkChoice(action) == true) {
                                player1.addPoints(bet1);
                            } else {
                                player1.subPoints(bet1);
                            }
                            break;
                        case 2:
                            timer.stop();
                            if (round.checkChoice(action) == true) {
                                player1.addPoints((int) (count * 0.2));
                            }
                            break;
                    }
                    score.setText("" + player1.getPoints());

                    next.setVisible(true);
                    for (int i = 0; i < 4; i++) {
                        button[i].setEnabled(false);
                    }

                    if (questions == 4) {
                        rounds++;
                        questions = -1;
                    }
                    if (rounds == 5) {
                        io.writeScores(numOfPlayers);
                    }
                }
            };
            for (int i = 0; i < 4; i++) {
                if (numOfPlayers == 1) {
                    button[i].addActionListener(action);
                }
                grid.add(button[i]);
            }

            //Add to panel
            top.add(grid);
            top.add(next);
            border.add(top, BorderLayout.CENTER);
            borderGrid.add(grid2);
            border.add(borderGrid, BorderLayout.EAST);

            if (round.getQuestionsAndAnswers(5) != null) {
                image = new ImageIcon(round.getQuestionsAndAnswers(5));
                imageLabel = new JLabel("", image, JLabel.CENTER);
                border.add(imageLabel, BorderLayout.EAST);
            }

            this.add(border);
        }
    }
}
