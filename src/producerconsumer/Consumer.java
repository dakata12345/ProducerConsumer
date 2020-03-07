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
public class Consumer extends Thread {

    private final Object LOCK;

    public Consumer(Object LOCK) {
        this.LOCK = LOCK;
    }

    public void run() {
        synchronized (LOCK) {
            while (true) {
                while (Main.list.size() == 0) {
                    try {
                        System.out.println("Consumer is waiting");
                        LOCK.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                int value = Main.list.removeFirst();
                System.out.println("Consumer consumed " + value);
                LOCK.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
