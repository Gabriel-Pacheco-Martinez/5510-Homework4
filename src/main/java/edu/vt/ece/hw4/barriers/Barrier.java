package edu.vt.ece.hw4.barriers;

import edu.vt.ece.hw4.bench.Counter;

public interface Barrier {
    void enter(Counter counter, int threadCount, int iters) throws Exception;
}
