package edu.vt.ece.hw4.locks;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/*
Improvement upon the TTAS lock
 */
public class SpinSleepLock implements Lock {

    private AtomicBoolean state = new AtomicBoolean(false);
    private AtomicInteger spinningThreads = new AtomicInteger(0);
    private int maxSpinningThreads;
    private final Object monitor = new Object();
    
    public SpinSleepLock(int maxSpin) {
        maxSpinningThreads = maxSpin;
    }

    @Override
    public void lock(int id) {
        while (true){
            //Check if there is space for another thread to spin
            if (spinningThreads.getAndIncrement() <= maxSpinningThreads){
                //Spin
                while (state.get()) {};
                //Reach for lock
                if (!state.getAndSet(true))
                {
                    //Reduce spinning count and return
                    spinningThreads.decrementAndGet();
                    return;
                }
                //Reduce spinning count in case the thread
                //didn't grab the lock
                spinningThreads.decrementAndGet();
            }
            //No space to spin
            else
            {
                //Reduce spinning count
                spinningThreads.decrementAndGet();

                //Set thread to sleep
                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    @Override
    public void unlock(int id) {
        //Change state
        state.set(false);

        //Notify a thread to wake up
        synchronized (monitor) {
            monitor.notify();
        }

    }
}