package edu.vt.ece.hw4.backoff;

public class LinearBackoff implements Backoff{

    @Override
    public void backoff(int a) throws InterruptedException {
        int delay = a;
        Thread.sleep(delay);
    }
}

