package com.swift.questions.question2;

public abstract class ElementsStore<T> {

  // let the storage size be holding a max of 10 items when not specified
  private static final int MAX_SIZE = 10;
  protected int sizeMax;
  protected int primaryIndex;
  protected final T[] elements;

  @SuppressWarnings("unchecked")
  protected ElementsStore() {
    this.sizeMax = MAX_SIZE;
    this.primaryIndex = -1;
    elements = (T[]) new Object[sizeMax];
  }

  @SuppressWarnings("unchecked")
  protected ElementsStore(int sizeMax) {
    if (sizeMax <= 0) {
      throw new IllegalArgumentException("Storage size cannot be zero or negative");
    }
    this.primaryIndex = -1;
    this.sizeMax = sizeMax;
    elements = (T[]) new Object[sizeMax];
  }

  public synchronized boolean isEmpty() {
    return primaryIndex == -1;
  }

  public synchronized T peek() {
    return !isEmpty() ? elements[primaryIndex] : null;
  }

  public abstract boolean isFull();
}
