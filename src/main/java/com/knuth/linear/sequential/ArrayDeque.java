package com.knuth.linear.sequential;


import com.knuth.linear.Deque;
import com.knuth.linear.OverflowException;
import com.knuth.linear.UnderflowException;

import java.util.Objects;

/**
 * D. Knuth. Art of Computer Programming, Vol 1: Section 2.2.2. Sequential Allocations.
 */
public class ArrayDeque implements Deque {
    private final int capacity;
    private final Object[] data;
    private int left;
    private int right;
    private int n;

    public ArrayDeque(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Deque capacity cannot be negative or zero");
        }
        this.capacity = capacity;
        this.data = new Object[capacity];
        this.left = 0;
        this.right = 0;
        this.n = 0;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public boolean isFull() {
        return n == capacity;
    }

    @Override
    public void enqueueLeft(Object o) throws OverflowException {
        Objects.requireNonNull(o);

        if (isFull()) {
            throw new OverflowException(this);
        }

        left = IntUtils.dec(left, capacity);
        data[left] = o;
        n++;
    }

    @Override
    public Object dequeueLeft() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException(this);
        }

        Object o = data[left];
        left = IntUtils.inc(left, capacity);
        n--;
        return o;
    }

    @Override
    public Object peekLeft() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException(this);
        }

        return data[left];
    }

    @Override
    public void enqueueRight(Object o) throws OverflowException {
        if (isFull()) {
            throw new OverflowException(this);
        }

        data[right] = o;
        right = IntUtils.inc(right, capacity);
        n++;
    }

    @Override
    public Object dequeueRight() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException(this);
        }

        right = IntUtils.dec(right, capacity);
        n--;
        return data[right];
    }

    @Override
    public Object peekRight() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException(this);
        }

        int index = IntUtils.dec(right, capacity);
        return data[index];
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
