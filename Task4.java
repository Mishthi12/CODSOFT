import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Task4 
{
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static ArrayList<Question> questions = new ArrayList<>();
    private static JFrame frame;
    private static JLabel timerLabel;
    private static int timeRemaining;
    private static Timer timer;
    private static JPanel questionPanel;  

    public static void main(String[] args) 
    {
        questions.add(new Question("What is 1 + 1?", new String[]{"1", "2", "3", "4"}, "2"));
        questions.add(new Question("What is 2 + 2?", new String[]{"2", "3", "4", "5"}, "4"));
        questions.add(new Question("What is 3 + 1?", new String[]{"2", "3", "4", "5"}, "4"));
        questions.add(new Question("What is 5 - 2?", new String[]{"2", "3", "4", "5"}, "3"));
        questions.add(new Question("What is 10 - 8?", new String[]{"1", "2", "3", "4"}, "2"));
        questions.add(new Question("What is 4 + 4?", new String[]{"6", "7", "8", "9"}, "8"));
        questions.add(new Question("What is 7 - 5?", new String[]{"1", "2", "3", "4"}, "2"));
        questions.add(new Question("What is 9 - 1?", new String[]{"6", "7", "8", "9"}, "8"));
        questions.add(new Question("What is 3 + 3?", new String[]{"4", "5", "6", "7"}, "6"));
        questions.add(new Question("What is 6 - 4?", new String[]{"1", "2", "3", "4"}, "2"));

        frame = new JFrame("Math Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);

        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(new BorderLayout());

        JPanel timerPanel = new JPanel();
        timerPanel.setBackground(Color.WHITE);
        timerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        timerLabel = new JLabel("Time: 15");
        timerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        timerLabel.setOpaque(true);
        timerLabel.setBackground(Color.GREEN);

        timerPanel.add(timerLabel);
        frame.add(timerPanel, BorderLayout.NORTH);  

        questionPanel = new JPanel();
        questionPanel.setBackground(Color.WHITE);
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        frame.add(questionPanel, BorderLayout.CENTER);  

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  
        frame.setVisible(true);  

        startQuiz();
    }

    public static void startQuiz() 
    {
        displayQuestion(questions.get(currentQuestionIndex));
    }

    public static void displayQuestion(Question q) 
    {
        
        questionPanel.removeAll();

        JLabel questionLabel = new JLabel(q.getQuestion());
        questionLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));  
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionPanel.add(questionLabel);

        ButtonGroup group = new ButtonGroup();
        for (String option : q.getOptions()) 
        {
            JRadioButton button = new JRadioButton(option);
            button.setActionCommand(option);
            button.setBackground(Color.WHITE);
            button.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));  
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            group.add(button);
            questionPanel.add(button);
        }

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 24));  
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(e -> 
        {
            String userAnswer = group.getSelection().getActionCommand();
            submitAnswer(userAnswer, q);
            moveToNextQuestion();
        });
        questionPanel.add(submitButton);

        frame.revalidate();
        frame.repaint();

        startTimer();
    }

    public static void startTimer() 
    {
        timeRemaining = 15; 
        updateTimerLabel(); 
        if (timer != null) 
        {
            timer.cancel(); 
        }
        timer = new Timer();
        TimerTask task = new TimerTask() 
        {
            public void run() 
            {
                timeRemaining--;
                updateTimerLabel();
                if (timeRemaining <= 0) 
                {
                    timer.cancel();
                    moveToNextQuestion();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000); 
    }

    private static void updateTimerLabel() 
    {
        timerLabel.setText("Time: " + timeRemaining);
        if (timeRemaining > 10) 
        {
            timerLabel.setBackground(Color.GREEN);
        } 
        else if (timeRemaining > 5) 
        {
            timerLabel.setBackground(Color.YELLOW);
        } 
        else 
        {
            timerLabel.setBackground(Color.RED);
        }
    }

    public static void moveToNextQuestion() 
    {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) 
        {
            displayQuestion(questions.get(currentQuestionIndex));
        } 
        else 
        {
            showResults();
        }
    }

    public static void submitAnswer(String userAnswer, Question q) 
    {
        if (q.isCorrect(userAnswer)) 
        {
            score++;
            playSound("correct.wav");
            JOptionPane.showMessageDialog(frame, "Great job!", "Correct", JOptionPane.INFORMATION_MESSAGE);
        } 
        else 
        {
            playSound("incorrect.wav");
            JOptionPane.showMessageDialog(frame, "Try again!", "Incorrect", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void showResults() 
    {
        questionPanel.removeAll();
        JLabel resultLabel = new JLabel("Your final score is: " + score + " out of " + questions.size(), SwingConstants.CENTER);
        resultLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));  
        questionPanel.add(resultLabel);
        frame.revalidate();
        frame.repaint();
    }

    public static void playSound(String soundFile) 
    {
        // Implement sound playing logic using javax.sound.sampled package
        // For example: AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
    }
}

class Question 
{
    private String questionText;
    private String[] options;
    private String correctAnswer;

    public Question(String questionText, String[] options, String correctAnswer) 
    {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() 
    {
        return questionText;
    }

    public String[] getOptions() 
    {
        return options;
    }

    public boolean isCorrect(String answer) 
    {
        return correctAnswer.equals(answer);
    }
}
