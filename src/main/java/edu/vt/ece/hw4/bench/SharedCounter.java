package edu.vt.ece.hw4.bench;

import edu.vt.ece.hw4.locks.Lock;

/**
 *
 * @author Mohamed M. Saad
 */
public class SharedCounter extends Counter{
    private Lock lock;

    public SharedCounter(int c, Lock lock) {
        super(c);
        this.lock = lock;
    }

    @Override
    public int getAndIncrement(int id) {
        lock.lock(id);
        int temp = -1;
        try {
            temp = super.getAndIncrement(id);
        } finally {
            lock.unlock(id);
        }
        return temp;
    }

}