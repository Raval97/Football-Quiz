package FootbalQuiz;

import java.io.PrintWriter;

class TimerThread implements Runnable {

    private int time;
    public boolean done=false;
    PrintWriter out;

    TimerThread(int time, PrintWriter out){
        this.time = time;
        this.out = out;
    }
    public void run() {
        while (time>0) {
            System.out.println("Pozostalo:  "+time+"s");
            try {
                Thread.currentThread().sleep(1000);
            } catch(InterruptedException exc) {
                System.out.println("Wątek zliczania czasu zoostał przerwany.");
                return;
            }
            time--;
        }
        if(!done)
            out.println("time_to_next");
    }
    public void setTime(int time) {
        this.time = time;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
}
