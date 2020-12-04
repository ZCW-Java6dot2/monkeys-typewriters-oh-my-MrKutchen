package io.zipcoder;

import java.util.Random;

/**
 * Make this extend the Copier like `UnsafeCopier`, except use locks to make sure that the actual intro gets printed
 * correctly every time.  Make the run method thread safe.
 */
public class SafeCopier extends Copier {

    public SafeCopier(String toCopy) {
        super(toCopy);
    }

    @Override
    public void run() {
        Random random = new Random();
        String next = "";

        while (stringIterator.hasNext()) {
            try {
                synchronized (stringIterator) {
                    if (stringIterator.hasNext()) {
                        next = stringIterator.next() + " ";
                        Thread.sleep(random.nextInt((250 - 50) + 1) + 50);
                        copied += next;
//                        System.out.println(Thread.currentThread().getName());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        copied = copied.trim();
    }
}
