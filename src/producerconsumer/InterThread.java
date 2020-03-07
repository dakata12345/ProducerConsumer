/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dakata
 */
class Q {

    int num;
    boolean valueSet = true;

    public synchronized void set(int num) {
        while (valueSet) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
        valueSet = true;
        System.out.println("Producer value is " + this.num);
        this.num = num;
        notify();
    }

    public synchronized int get() {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex.getStackTrace());
            }

        }
        valueSet = false;
        notify();
        return this.num;
    }
}

class Prod implements Runnable {

    Q q;

    public Prod(Q q) {
        this.q = q;
        Thread t = new Thread(this, "Producer");
        t.start();
    }

    public void run() {
        int i = 0;
        while (true) {
            q.set(i++);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
}

class Cons implements Runnable {

    Q q;

    public Cons(Q q) {
        this.q = q;
        Thread t = new Thread(this, "Consumer");
        t.start();
    }

    public void run() {
        while (true) {
            int value = q.get();
            System.out.println("Consumer value is " + value);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
}

public class InterThread {

    public static void main(String[] args) {
        Q q = new Q();
        Cons c = new Cons(q);
        Prod p = new Prod(q);
    }
}
