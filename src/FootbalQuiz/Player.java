package FootbalQuiz;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Player extends Server implements Runnable {

    private String nick;
    private Integer scoreOfPlayer = 0;
    private ArrayList<Integer> questions = new ArrayList<Integer>();
    //private Data data = new Data("Questions.txt"); //wersja txt
    private Data data = new Data(); //wersja MySQL
    private Integer nrOfQuestions;
    private int countOfQuestions = 0;
    private char answer;
    private static final Queue<String> checkConfirm = new LinkedList<>();
    public static int time=10;
    public TimerThread timer;
    Player opponent;
    Socket socket;
    Scanner input;
    PrintWriter output;

    public Player(Socket socket) throws IOException {
        questions = Server.question;
        this.socket = socket;
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Connected: " + socket);
    }

    @Override
    public void run() {
        try {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
            processCommands();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (opponent != null && opponent.output != null) {
                opponent.output.println("OTHER_PLAYER_LEFT");
                System.out.println("OTHER_PLAYER_LEFT");
            }
            try {
                socket.close();
            } catch (IOException e) {
            }
        }

    }

    private void processCommands() throws InterruptedException {
        while ( input.hasNextLine() ) {
            String command = input.nextLine();
            System.out.println(command);
            if (command.startsWith("start")) {
                synchronizedThreadsFunction("START");
            } else if (command.startsWith("next")) {
                output.println("SCORE " + opponent.scoreOfPlayer);
                timer.setDone(true);
                synchronizedThreadsFunction("NEXT");
            } else if (command.startsWith("finish")) {
                output.println("SCORE " + opponent.scoreOfPlayer);
                timer.setDone(true);
                synchronizedThreadsFunction("FINISH");
            } else if (command.startsWith("nick")) {
                nick = command.substring(5);
            } else if (command.startsWith("nr_quests")) {
                nrOfQuestions = Integer.valueOf(command.substring(10));
            } else if (command.startsWith("check")) {
                answer = command.charAt(6);
                if (answer == data.getCorrectAnswer(questions.get(countOfQuestions - 1))) {
                    output.println("CORRECT");
                    System.out.println("CORRECT");
                    scoreOfPlayer++;
                } else {
                    output.println("UNCORRECT");
                    System.out.println("UNCORRECT");
                }
            }
        }
    }

    public void synchronizedThreadsFunction(String command) {
        synchronized (checkConfirm) {
            checkConfirm.add("Player Confirm");
            if (checkConfirm.size() == 2) {
                checkConfirm.notify();
            } else if (checkConfirm.size() == 1) {
                output.println("Waiting for opponend");
                System.out.println("Waiting for opponend");
                while ( checkConfirm.size() != 2 ) {
                    try {
                        checkConfirm.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                checkConfirm.remove(); checkConfirm.remove();
            }
            if (command == "START") {
                output.println("NICK " + opponent.nick);
                output.println("NICK " + opponent.nick);
            }
            if (command != "FINISH")
                sentQuestion();
            output.println(command);
            System.out.println(command);
            countOfQuestions++;
        }
    }

    public void sentQuestion() {
        timer = new TimerThread(time +1, output);
        Thread threadTimer = new Thread(timer);
        threadTimer.start();
        System.out.println("ask: " + countOfQuestions + "  nr:" + questions.get(countOfQuestions));
        output.print(data.getQuestion(questions.get(countOfQuestions)));
        System.out.print(data.getQuestion(questions.get(countOfQuestions)));
        for (int i = 0; i < 4; i++) {
            output.print(data.getAnswer(questions.get(countOfQuestions), i));
            System.out.print(data.getAnswer(questions.get(countOfQuestions), i));
        }
    }
}
