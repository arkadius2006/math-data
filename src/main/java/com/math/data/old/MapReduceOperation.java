package com.math.data.old;

public interface MapReduceOperation {

    Object init();

    Object add(Object augend, Duple addend);

    Object complete(Object sum);
}