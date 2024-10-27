package edu.vt.ece.hw4.locks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

public class TTASLock implements Lock {

    private AtomicBoolean state = new AtomicBoolean(false);
    private HashSet<Integer> map = new HashSet<Integer>();

    public void lock(int id) {
        while (true) {
            while (state.get()) {};  // ece.vt.edu.spin
            if ((!state.getAndSet(true))&&(!map.contains(id))){
                map.add(id);
                return;
            }
        }
    }

    public void unlock(int id) {
        map.remove(id);
        state.set(false);
    }

}