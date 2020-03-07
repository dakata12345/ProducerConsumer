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
public class Producer extends Thread {

    private Object LOCK;
    private int value = 0;

    public Producer(Object LOCK) {
        this.LOCK = LOCK;
    }

    public void run() {
        synchronized (LOCK) {
            while (true) {
                // To produce one and immediately consume
                // could be replace by MAX_SIZE to produce MAX_SIZE and consume
                // MAX_SIZE
                while (Main.list.size() == 1) {
                    try {
                        System.out.println("Producer is waiting");
                        LOCK.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("Producer produced " + value);
                Main.list.add(value++);
                LOCK.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
