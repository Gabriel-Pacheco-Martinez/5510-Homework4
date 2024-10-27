package edu.vt.ece.hw4.barriers;

import edu.vt.ece.hw4.bench.Counter;
import edu.vt.ece.hw4.bench.TestThread;

public class ArrayBarrier implements Barrier{
    public void enter(Counter counter, int threadCount, int iters) throws Exception {
        final TestThread[] threads = new TestThread[threadCount];
        TestThread.reset();

        for (int t = 0; t < threadCount; t++) {
            threads[t] = new TestThread(counter, iters);
        }

        for (int t = 0; t < threadCount; t++) {
            threads[t].start();
        }

        double totalTime = 0;
        for (int t = 0; t < threadCount; t++) {
            threads[t].join();
            totalTime += threads[t].getElapsedTime();
        }
        double avgBarrierTime = ((totalTime/iters)/threadCount);

        System.out.println("Array barrier time: " + avgBarrierTime + "ms");
    }
}
