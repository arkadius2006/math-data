package com.knuth.linear.sequential;

import com.knuth.linear.OverflowException;
import com.knuth.linear.Queue;
import com.knuth.linear.UnderflowException;

import java.util.Objects;

/**
 * D. Knuth. Art of Computer Programming, Vol 1: Section 2.2.2. Sequential Allocations.
 */
public class ArrayQueue implements Queue {
    private final int capacity;
    private final Object[] data;
    private int front;
    private int rear;
    private int n;

    public ArrayQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Queue capacity cannot be negative or zero");
        }

        this.capacity = capacity;
        this.data = new Object[capacity];
        this.front = 0;
        this.rear = 0;
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
    public void enqueue(Object o) throws OverflowException {
        Objects.requireNonNull(o);

        if (isFull()) {
            throw new OverflowException(this);
        }

        data[rear] = o;
        rear = IntUtils.inc(rear, capacity);
        n++;
    }

    @Override
    public Object dequeue() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException(this);
        }

        Object dequeued = data[front];
        front = IntUtils.inc(front, capacity);
        n--;
        return dequeued;
    }

    @Override
    public Object peek() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException(this);
        }

        return data[front];
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
