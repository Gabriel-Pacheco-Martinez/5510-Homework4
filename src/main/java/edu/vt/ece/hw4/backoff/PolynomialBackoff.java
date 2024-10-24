package edu.vt.ece.hw4.backoff;

public class PolynomialBackoff implements Backoff{
    @Override
    public void backoff(int a) throws InterruptedException {
        int delay = (int)Math.pow(a,2);
        Thread.sleep(delay);
    }
}
