package FootbalQuiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {
    private File fileTasks;
    private ArrayList<String> questions = new ArrayList<String>();
    private ArrayList<ArrayList<String>> answers = new ArrayList<ArrayList<String>>();
    private ArrayList<String> correctAnswers = new ArrayList<String>();
    private Integer numberOfTasks=0;

    private Connection conn = null;
    private String url = "jdbc:mysql://localhost:3306/";
    private String dbName = "quiz";
    private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private  String password = "";

    public Data(){
        readData();
        //writeData();
    }

    public Data(String srcQuestion){
        try{
            fileTasks = new File(srcQuestion);
            readData2();
            //writeData();
        }
        catch(NullPointerException e){
            System.out.println("Error witch file, probably empty name of file");
        }
    }

    private void readData(){
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName,userName,password);
            System.out.println("Połączony z bazą");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet srs = stmt.executeQuery("SELECT * FROM pytania");
            int i=0;
            while (srs.next()) {
                questions.add(srs.getString("Tresc")+"\n");
                answers.add(new ArrayList<String>());
                answers.get(i).add(srs.getString("OdpA")+"\n");
                answers.get(i).add(srs.getString("OdpB")+"\n");
                answers.get(i).add(srs.getString("OdpC")+"\n");
                answers.get(i).add(srs.getString("OdpD")+"\n");
                correctAnswers.add(srs.getString("OdpPoprawna")+"\n");
                i++;
            }
            numberOfTasks=Integer.valueOf(questions.size()) ;
            conn.close();;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void readData2(){
        try {
            Scanner in = new Scanner(fileTasks);
            int i=0;
            while(in.hasNext()) {
                questions.add(in.nextLine()+"\n");
                answers.add(new ArrayList<String>());
                for(int j=0; j<4; j++)
                    answers.get(i).add(in.nextLine()+"\n");
                correctAnswers.add(in.nextLine()+"\n");
                i++;
            }
            numberOfTasks=Integer.valueOf(questions.size()) ;
        }
        catch(FileNotFoundException e){
            System.out.println("Error witch file, probably empty name of file");
            e.printStackTrace();
        }
    }

    public void writeData() {
        try{
            for(int i=0; i<questions.size(); i++){
                System.out.print(questions.get(i));
                for (String s: answers.get(i)) {
                    System.out.print(s);
                }
                System.out.print(correctAnswers.get(i));
            }
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Error witch index, probably index out of bounds");
        }
    }

    public void writeQuestion(int nr){
        System.out.print(questions.get(nr));
        for (String s: answers.get(nr)) {
            System.out.print(s);
        }
    }

    public String getQuestion(int nr) {
        return questions.get(nr);
    }

    public String getAnswer(int nr, int id) {
        return answers.get(nr).get(id);
    }

    public char getCorrectAnswer(int nr){
        String correct=correctAnswers.get(nr);
        return correct.charAt(0);
    }

    public Integer getNumberOfTasks() {
        return numberOfTasks;
    }
}

