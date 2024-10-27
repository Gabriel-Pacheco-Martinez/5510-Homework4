package edu.vt.ece.hw4.backoff;

public class FixedBackoff implements Backoff{

    @Override
    public void backoff(int a) throws InterruptedException {
        int delay = 10 * a;
    }
}
