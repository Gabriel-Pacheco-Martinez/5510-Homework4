package edu.vt.ece.hw4.locks;

import java.util.concurrent.atomic.AtomicReference;

public class CLHLock implements Lock {
    // most recent lock holder
    AtomicReference<QNode> tail;
    // thread-local variables
    ThreadLocal<QNode> myNode, myPred;

    /**
     * Constructor
     */
    public CLHLock() {
        tail = new AtomicReference<QNode>(new QNode());
        // initialize thread-local variables
        myNode = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return new QNode();
            }
        };
        myPred = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return null;
            }
        };
    }

    public void lock(int id) {
        QNode qnode = myNode.get(); // use my node
        qnode.locked = true;        // announce start
        // Make me the new tail, and find my predecessor
        QNode pred = tail.getAndSet(qnode);
        myPred.set(pred);           // remember predecessor
        while (pred.locked) {}      // spin
    }
    public void unlock(int id) {
        QNode qnode = myNode.get(); // use my node
        qnode.locked = false;       // announce finish
        myNode.set(myPred.get());   // reuse predecessor
    }

    static class QNode {  // Queue node inner class
        public boolean locked = false;
    }
}
