package FootbalQuiz;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {
    public static ArrayList<Integer> question = new ArrayList<Integer>();
    //private static Data data = new Data("Questions.txt"); //wersja txt
    private static Data data = new Data(); //wersja MySQL
    private static  ArrayList<Player> players=new ArrayList<Player>();

    public static void main(String[] args) throws Exception {
        try (ServerSocket listener = new ServerSocket(1432)) { //port: 1432

            System.out.println("Football Quiz Server is Running...");
            Executor pool = Executors.newFixedThreadPool(100);
            while ( true ) {
                question = randomNumberFunction(10); //Integer.valueOf(Panels.nrOfQuestions.getText()));
                for (int i = 0; i<2 ; i++){
                    players.add(new Player(listener.accept()));
                    if (players.size() % 2 == 0) {
                        players.get(players.size() - 1).opponent = players.get(players.size() - 2);
                        players.get(players.size() - 2).opponent = players.get(players.size() - 1);
                    }
                    pool.execute(players.get(players.size() - 1));
                }
            }
        }
    }

    public static ArrayList<Integer> randomNumberFunction(Integer nr) {
        ArrayList<Integer> arrayOfCount = new ArrayList<Integer>();
        for (Integer i = 0; i < data.getNumberOfTasks(); i++) {
            arrayOfCount.add(i);
        }
        Collections.shuffle(arrayOfCount);
        for (Integer i = data.getNumberOfTasks() - 1; i > (nr - 1); i--) {
            arrayOfCount.remove(arrayOfCount.get(i));
        }
        for (Integer e:arrayOfCount) {
            System.out.print(e+" ");
        }
        System.out.println();
        return arrayOfCount;
    }
}


/*shurtcat:
 ctrl + F9 -debug;   shift + F10 -run   shift + F9 -debug
 sout ; psvm
 alt + enter -podpowiedz
 alt + Fn + insert -getter, setter, constructor
 ctrl +d -duplikuj linie kodu
 shift + tab -usuwa tabulacje
 shift + F6 -zmiana nazw
 ctr + / -zakomentowanie
 alt + strzałka  lubb  ctrl + tab -poruszanie sie pomiedzy plikami(klasami)
 ctrl + alt + m -zaznacz kod i zamien go na metode
 ctrl + alt + b -przenosi do implementacji zaznaczonej klasy/metody
*/