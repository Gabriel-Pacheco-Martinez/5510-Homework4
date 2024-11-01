package edu.vt.ece.hw4.backoff;

public class ExponentialBackoff implements Backoff {

    @Override
    public void backoff(int a) throws InterruptedException {
        int delay = (int)Math.pow(2,a);
        Thread.sleep(delay);
    }

}
