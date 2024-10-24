package edu.vt.ece.hw4.backoff;

public class FibonacciBackoff implements Backoff {

    @Override
    public void backoff(int a) throws InterruptedException {
        int delay = fibonacciResult(a);
        Thread.sleep(delay);
    }

    public static int fibonacciResult(int limit) {
        int previous = 0, current = 1;
        int res=0;

        for (int i = 0; i < limit; i++)
        {
            res = previous+current;
            previous = current;
            current = res;
        }

        return res;
    }
}
