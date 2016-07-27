package actor;

import sun.applet.Main;

/**
 * Created by fqc on 2016/7/27.
 */
public class ThreadPrint {
    public static void main(String[] args) {
        Thread t11 = new Thread(new T11());
        Thread t12 = new Thread(new T12());
        t11.start();
        t12.start();
    }
}

class T11 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <=20; i++) {
            System.out.println("T11-->i = " + i);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class T12 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <= 20; i++) {
            System.out.println("T12-->i = " + i);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



