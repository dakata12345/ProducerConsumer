/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

import java.util.LinkedList;

/**
 *
 * @author dakata
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static LinkedList<Integer> list;
    public static int MAX_SIZE = 2;

    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        list = new LinkedList<>();
        final Object LOCK = new Object();
        Producer prod = new Producer(LOCK);
        Consumer con = new Consumer(LOCK);
        prod.start();
        con.start();
        prod.join();
        con.join();
    }

}
