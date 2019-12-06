package FootbalQuiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client2 implements ActionListener {

    private JFrame quizFrame = new JFrame();
    private Panels jPanel = new Panels();
    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    private Integer nrOfQuestions;
    private String nick = new String();
    private String opponentNick = new String();
    private int score = 0;
    private int opponentScore = 0;
    private String question = new String();
    private String answerA = new String();
    private String answerB = new String();
    private String answerC = new String();
    private String answerD = new String();
    private Character answer;
    private int countOfQuestions = 0;
    private int timeCounter;
    private boolean toLate = false;
    private boolean timeToNext = false;

    public static void main(String[] args) throws Exception {
        Client2 client = new Client2("127.0.0.20");
        client.play();
    }

    public Client2(String  serverAddress) throws  Exception{
        socket = new Socket(serverAddress, 1432);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
        quizFrame.setTitle("FOOTBALL QUIZ");
        quizFrame.setSize(960, 720);
        quizFrame.setLocation(0, 350);
        quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quizFrame.add(jPanel.startPanel());
        jPanel.nameOfPlayers.setText("Karol");
        quizFrame.setVisible(true);
        jPanel.buttonStart.addActionListener(this);
    }
    public void play() throws Exception {
        try {
            String response = in.nextLine();
            while (in.hasNextLine()) {
                response = in.nextLine();
                System.out.println("Server: "+response);
                if (response.startsWith("START")) {
                    quizFrame.remove(jPanel.panelStart);
                    try { quizFrame.add(jPanel.gamePanel()); }
                    catch (IOException ex) { ex.printStackTrace(); }
                    for (int i = 0; i < jPanel.buttonAnswer.size(); i++) {
                        jPanel.buttonAnswer.get(i).addActionListener(this);
                    }
                    showQuestion();
                    new Timer(1000, this::updateGUI).start();

                } else if (response.startsWith("NEXT")) {
                    showQuestion();
                } else if (response.startsWith("FINISH")) {
                    timeToNext=false;
                    quizFrame.remove(jPanel.panelGame);
                    try {  quizFrame.add(jPanel.finishPanel()); }
                    catch (IOException ex) { ex.printStackTrace(); }
                    classificationFunction();
                    resultFunction();
                } else if (response.startsWith("CORRECT")) {
                    jPanel.select.setText("Correct");
                    jPanel.select.setForeground(Color.green);
                    score++;
                }else if (response.startsWith("UNCORRECT")) {
                    jPanel.select.setText("Uncorrect");
                    jPanel.select.setForeground(Color.red);
                }else if (response.startsWith("#")) {
                    question=response;
                }else if (response.startsWith("A")) {
                    answerA=response;
                }else if (response.startsWith("B")) {
                    answerB=response;
                }else if (response.startsWith("C")) {
                    answerC=response;
                }else if (response.startsWith("D")) {
                    answerD=response;
                } else if (response.startsWith("NICK")) {
                    opponentNick=response.substring(5);
                }else if (response.startsWith("SCORE")) {
                    opponentScore=Integer.valueOf(response.substring(6));
                }else if (response.startsWith("time_to_next")) {
                    timeToNext=true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            socket.close();
            quizFrame.dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==jPanel.buttonStart){
            nick = jPanel.nameOfPlayers.getText();
            nrOfQuestions = Integer.valueOf(jPanel.nrOfQuestions.getText());
            out.println("nick "+nick);
            out.println("nr_quests "+nrOfQuestions);
            out.println("start1"); System.out.println("start1");
            jPanel.buttonNextQuestion.addActionListener(this);
            jPanel.select.addActionListener(this);
        }
        if(countOfQuestions>0) {
            if (e.getSource() == jPanel.buttonNextQuestion && countOfQuestions!=nrOfQuestions) {
                out.println("next1"); System.out.println("next1");
            }
            if (e.getSource() == jPanel.buttonNextQuestion && countOfQuestions == nrOfQuestions ) {
                out.println("finish1"); System.out.println("finish1");
                jPanel.buttonClose.addActionListener(this);
            }
            if (e.getSource() == jPanel.buttonAnswer.get(0)) answer = 'A';
            if (e.getSource() == jPanel.buttonAnswer.get(1)) answer = 'B';
            if (e.getSource() == jPanel.buttonAnswer.get(2)) answer = 'C';
            if (e.getSource() == jPanel.buttonAnswer.get(3)) answer = 'D';
            if (e.getSource() == jPanel.select && jPanel.select.getText() == "SELECT") {
                if (!toLate)
                    out.println("check " + answer); System.out.println("check " + answer);
            }
            if (e.getSource() == jPanel.buttonClose) System.exit(0);
        }
    }

    void updateGUI(ActionEvent e) {
        timeCounter--;
        if(timeCounter>=0) {
            jPanel.timer.setText((timeCounter)+"s");
            if(timeCounter==0 && jPanel.select.getText().equals("SELECT")) {
                toLate = true;
                jPanel.select.setText("Time is ended");
                jPanel.select.setForeground(Color.red);
            }
        }
        if(timeToNext && countOfQuestions!=nrOfQuestions)
            out.println("next1");
        if(timeToNext && countOfQuestions==nrOfQuestions){
            out.println("finish1");
            jPanel.buttonClose.addActionListener(this);
        }
    }

    public void showQuestion(){
        timeToNext=false;
        jPanel.question.setText(question);
        jPanel.scorePlayer.setText(nick+": "+score);
        jPanel.scoreOpponent.setText(opponentNick+": "+opponentScore);
        jPanel.nrOfQuestion.setText(""+(countOfQuestions+1)+"/"+nrOfQuestions);
        jPanel.buttonAnswer.get(0).setText(answerA);
        jPanel.buttonAnswer.get(1).setText(answerB);
        jPanel.buttonAnswer.get(2).setText(answerC);
        jPanel.buttonAnswer.get(3).setText(answerD);
        countOfQuestions++;
        timeCounter=Player.time;
        toLate=false;
        jPanel.select.setText("SELECT");
        jPanel.select.setForeground(Color.black);
    }

    public void classificationFunction(){
        String result = new String("");
        result+=(nick+": "+score+"\n");
        result+=(opponentNick+": "+opponentScore+"\n");
        jPanel.scoreArea.setText(result);
    }

    public void resultFunction(){
        if (score>opponentScore)
            jPanel.resultField.setText("The Winner is: "+nick.toUpperCase());
        else if (score<opponentScore)
            jPanel.resultField.setText("The Winner is: "+opponentNick.toUpperCase());
        if (score==opponentScore)
            jPanel.resultField.setText("The game ended in DRAW");
    }
}
