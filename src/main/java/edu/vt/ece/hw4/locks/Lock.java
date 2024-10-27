package edu.vt.ece.hw4.locks;

public interface Lock {
    void lock(int id);
    void unlock(int id);
}
