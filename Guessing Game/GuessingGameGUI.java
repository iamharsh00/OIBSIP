import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGameGUI extends JFrame {

    private static final int MAX_GUESSES = 6;
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 100;

    private int computersNumber;
    private int guessCount;

    private JLabel messageLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JButton playAgainButton;

    public GuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel messagePanel = new JPanel();
        messageLabel = new JLabel("I have picked a number between " + LOWER_BOUND + " and " + UPPER_BOUND + ". Try to guess it.");
        messagePanel.add(messageLabel);
        add(messagePanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        guessField = new JTextField();
        guessField.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(guessField);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessButtonListener());
        inputPanel.add(guessButton);

        JPanel playAgainPanel = new JPanel();
        playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(new PlayAgainButtonListener());
        playAgainButton.setEnabled(false);
        playAgainPanel.add(playAgainButton);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(inputPanel);
        centerPanel.add(Box.createVerticalStrut(20)); // Add space between inputPanel and playAgainButton
        centerPanel.add(playAgainPanel);

        add(centerPanel, BorderLayout.CENTER);

        startNewGame();
    }

    private void startNewGame() {
        Random random = new Random();
        computersNumber = random.nextInt(UPPER_BOUND - LOWER_BOUND + 1) + LOWER_BOUND;
        guessCount = 0;
        messageLabel.setText("I have picked a number between " + LOWER_BOUND + " and " + UPPER_BOUND + ". Try to guess it.");
        guessField.setText("");
        guessButton.setEnabled(true);
        playAgainButton.setEnabled(false);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int userGuess = Integer.parseInt(guessField.getText());
                guessCount++;

                if (userGuess == computersNumber) {
                    messageLabel.setText("You got it in " + guessCount + " guesses! My number was " + computersNumber);
                    guessButton.setEnabled(false);
                    playAgainButton.setEnabled(true);
                } else if (guessCount == MAX_GUESSES) {
                    messageLabel.setText("You didn't get the number in " + MAX_GUESSES + " guesses. You lose. My number was " + computersNumber);
                    guessButton.setEnabled(false);
                    playAgainButton.setEnabled(true);
                } else if (userGuess < computersNumber) {
                    messageLabel.setText("Your number is too low. Try again:");
                } else {
                    messageLabel.setText("Your number is too high. Try again:");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid integer.");
            }
        }
    }

    private class PlayAgainButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startNewGame();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuessingGameGUI gameGUI = new GuessingGameGUI();
            gameGUI.setVisible(true);
        });
    }
}
