package edu.vt.ece.hw4.backoff;

public interface Backoff {
    void backoff(int a) throws InterruptedException;
}
