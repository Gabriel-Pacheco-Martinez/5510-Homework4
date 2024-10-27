package edu.vt.ece.hw4.locks;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ArrayLock implements Lock{
    private AtomicInteger[] b;
    private int size;

    public ArrayLock(int n) {
        size = n;
        b = new AtomicInteger[n];
        for (int i = 0; i < n; i++) {
            b[i] = new AtomicInteger(0); // Initialize each AtomicBoolean to false
        }
    }

    public void lock(int id) {
        if (id == 0) {
            // Thread 0 sets b[0] to 1
            while (true) {
                if (b[0].get() == 0) {
                    b[0].set(1);
                    break;
                }
            }
        }
        else if (id == size-1) {
            while (b[id - 1].get() != 1) {}
            b[id].set(2);
        }
        else {
            // Other threads (1 to size-1)
            while (b[id - 1].get() != 1) {}
            b[id].set(1);
        }
    }

    public void unlock(int id) {
        if (b[size-1].get()==2){
            b[id].set(0);
        }

    }
}
