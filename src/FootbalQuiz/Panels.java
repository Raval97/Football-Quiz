package FootbalQuiz;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Panels {

    BufferedImage image;
    JLabel imageLabel;

    JPanel panelStart;
    List<JTextArea> startTextFields = new ArrayList<JTextArea>();
    List<JLabel> welcomeLabel = new ArrayList<JLabel>();
    JTextArea nameOfPlayers= new JTextArea("Piotr");
    public static JTextArea nrOfQuestions = new JTextArea("4");
    JButton buttonStart = new JButton("START");

    JPanel panelGame;
    JLabel nrOfQuestion = new JLabel("0");
    List<JButton> buttonAnswer =new ArrayList<JButton>();
    JLabel scorePlayer = new JLabel();
    JLabel scoreOpponent = new JLabel();
    JButton select = new JButton("SELECT");
    JTextArea question = new JTextArea();
    JTextArea timer= new JTextArea("");
    JLabel timerLabel= new JLabel("Time to end:");
    JButton buttonNextQuestion = new JButton("NEXT");

    JPanel panelFinish;
    JLabel theEndTextField = new JLabel("Thank you for game:)");
    JTextArea scoreArea = new JTextArea("...");
    JLabel resultField = new JLabel();
    JButton buttonClose = new JButton("Press to CLOSE");

    public Panels(){
        panelStart = new JPanel();
        panelGame = new JPanel();
        panelFinish = new JPanel();
    }

    public JPanel startPanel() throws IOException {
        panelStart.setLayout(null);
        panelStart.setBackground(Color.red);

        welcomeLabel.add(new JLabel("HELLO IN MY FOOTBALL QUIZ", SwingConstants.CENTER));
        welcomeLabel.get(welcomeLabel.size()-1).setFont(new Font("Arial Black", Font.BOLD, 30));
        welcomeLabel.get(welcomeLabel.size()-1).setForeground(Color.yellow);
        welcomeLabel.get(welcomeLabel.size()-1).setBounds(100,120, 700,30);
        panelStart.add(welcomeLabel.get(welcomeLabel.size()-1));

        startTextFields.add(new JTextArea("Enter the name of the  player: "));
        startTextFields.get(startTextFields.size()-1).setFont(new Font("Arial Black", Font.BOLD, 20));
        startTextFields.get(startTextFields.size()-1).setForeground(Color.white);
        startTextFields.get(startTextFields.size()-1).setBackground(Color.blue);
        startTextFields.get(startTextFields.size()-1).setBounds(100,220, 400,50);
        panelStart.add(startTextFields.get(startTextFields.size()-1));

        startTextFields.add(new JTextArea("Enter the number of questions: "));
        startTextFields.get(startTextFields.size()-1).setFont(new Font("Arial Black", Font.BOLD, 20));
        startTextFields.get(startTextFields.size()-1).setForeground(Color.white);
        startTextFields.get(startTextFields.size()-1).setBackground(Color.blue);
        startTextFields.get(startTextFields.size()-1).setBounds(100,300, 400,50);
        panelStart.add(startTextFields.get(startTextFields.size()-1));

        welcomeLabel.add(new JLabel("Lets start the QUIZ", SwingConstants.CENTER));
        welcomeLabel.get(welcomeLabel.size()-1).setFont(new Font("Arial Black", Font.BOLD, 24));
        welcomeLabel.get(welcomeLabel.size()-1).setForeground(Color.yellow);
        welcomeLabel.get(welcomeLabel.size()-1).setBounds(150,400, 300,30);
        panelStart.add(welcomeLabel.get(welcomeLabel.size()-1));

        nameOfPlayers.setFont(new Font("Arial Black", Font.ITALIC, 20));
        nameOfPlayers.setForeground(Color.white);
        nameOfPlayers.setBackground(Color.magenta);
        nameOfPlayers.setBounds(550, 220, 200, 50);
        panelStart.add(nameOfPlayers);

        nrOfQuestions.setFont(new Font("Arial Black", Font.ITALIC, 20));
        nrOfQuestions.setForeground(Color.white);
        nrOfQuestions.setBackground(Color.magenta);
        nrOfQuestions.setBounds(550, 300, 200, 50);
        panelStart.add(nrOfQuestions);

        buttonStart.setFont(new Font("Arial Black", Font.ITALIC, 20));
        buttonStart.setBounds(550, 400, 200, 30);
        panelStart.add(buttonStart);

        image = ImageIO.read(new File("images/image1.jpg"));
        imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(0,0,960, 720);
        panelStart.add(imageLabel);
        return panelStart;
    }

    public JPanel gamePanel() throws IOException {
        panelGame.setBackground(Color.red);
        panelGame.setLayout(null);

        scorePlayer.setFont(new Font("Arial Black", Font.BOLD, 20));
        scorePlayer.setForeground(Color.yellow);
        scorePlayer.setBounds(50, 25, 200, 30);
        panelGame.add(scorePlayer);

        scoreOpponent.setFont(new Font("Arial Black", Font.BOLD, 20));
        scoreOpponent.setForeground(Color.yellow);
        scoreOpponent.setBounds(50, 60, 200, 30);
        panelGame.add(scoreOpponent);

        nrOfQuestion.setFont(new Font("Arial Black", Font.BOLD, 30));
        nrOfQuestion.setForeground(Color.yellow);
        nrOfQuestion.setBounds(410, 30, 100, 30);
        panelGame.add(nrOfQuestion);

        question.setBackground(Color.gray);
        question.setFont(new Font("Arial Black", Font.BOLD, 24));
        question.setForeground(Color.yellow);
        question.setBounds(150, 100, 600, 150);;
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        panelGame.add(question);

        timerLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
        timerLabel.setForeground(Color.yellow);
        timerLabel.setBounds(750, 20, 150, 50);
        panelGame.add(timerLabel);
        timer.setBackground(Color.blue);
        timer.setFont(new Font("Arial Black", Font.BOLD, 25));
        timer.setForeground(Color.yellow);
        timer.setBounds(800, 70, 50, 50);
        panelGame.add(timer);

        buttonAnswer.add(new JButton("A"));
        buttonAnswer.get(buttonAnswer.size()-1).setBounds(50, 300, 350, 60);
        buttonAnswer.get(buttonAnswer.size()-1).setFont(new Font("Arial Black", Font.BOLD, 16));
        panelGame.add(buttonAnswer.get(buttonAnswer.size()-1));

        buttonAnswer.add(new JButton("B"));
        buttonAnswer.get(buttonAnswer.size()-1).setBounds(500, 300, 350, 60);
        buttonAnswer.get(buttonAnswer.size()-1).setFont(new Font("Arial Black", Font.BOLD, 16));
        panelGame.add(buttonAnswer.get(buttonAnswer.size()-1));

        buttonAnswer.add(new JButton("C"));
        buttonAnswer.get(buttonAnswer.size()-1).setBounds(50, 400, 350, 60);
        buttonAnswer.get(buttonAnswer.size()-1).setFont(new Font("Arial Black", Font.BOLD, 16));
        panelGame.add(buttonAnswer.get(buttonAnswer.size()-1));

        buttonAnswer.add(new JButton("D"));
        buttonAnswer.get(buttonAnswer.size()-1).setBounds(500, 400, 350, 60);
        buttonAnswer.get(buttonAnswer.size()-1).setFont(new Font("Arial Black", Font.BOLD, 16));
        panelGame.add(buttonAnswer.get(buttonAnswer.size()-1));

        buttonNextQuestion.setBackground(Color.gray);
        buttonNextQuestion.setForeground(Color.yellow);
        buttonNextQuestion.setBounds(800, 580, 100, 50);
        buttonNextQuestion.setFont(new Font("Arial Black", Font.BOLD, 20));
        panelGame.add(buttonNextQuestion);

        select.setBackground(Color.gray);
        select.setForeground(Color.yellow);
        select.setBounds(320, 500, 250, 50);
        select.setFont(new Font("Arial Black", Font.BOLD, 20));
        panelGame.add(select);

        image = ImageIO.read(new File("images/image2.jpg"));
        imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(0,0,960, 720);
        panelGame.add(imageLabel);
        return panelGame;
    }

    public JPanel finishPanel() throws IOException {
        panelFinish.setBackground(Color.red);
        panelFinish.setLayout(null);

        theEndTextField.setFont(new Font("Arial Black", Font.BOLD, 40));
        theEndTextField.setForeground(Color.yellow);
        theEndTextField.setBounds(220,150,550, 80);
        panelFinish.add(theEndTextField);

        scoreArea.setFont(new Font("Arial Black", Font.BOLD, 20));
        scoreArea.setForeground(Color.white);
        scoreArea.setBackground(Color.magenta);
        scoreArea.setBounds(230,230,500, 70);
        panelFinish.add(scoreArea);

        resultField.setFont(new Font("Arial Black", Font.BOLD, 35));
        resultField.setForeground(Color.green);
        resultField.setBounds(230,310,550, 80);
        panelFinish.add(resultField);

        buttonClose.setFont(new Font("Arial Black", Font.BOLD, 32));
        buttonClose.setForeground(Color.red);
        buttonClose.setBounds(250,400,400, 60);
        panelFinish.add(buttonClose);

        image = ImageIO.read(new File("images/image3.jpg"));
        imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(0,0,960, 720);
        panelFinish.add(imageLabel);
        return panelFinish;
    }
}