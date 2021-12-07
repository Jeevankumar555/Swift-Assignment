package com.swift.questions.question2;

import com.swift.exception.OutOfElementsException;

public class Queue<T> extends ElementsStore<T> {

  private int rearIndex;

  public Queue() {
    this.rearIndex = -1;
  }

  public Queue(int sizeMax) {
    super(sizeMax);
    this.rearIndex = -1;
  }

  @Override
  public synchronized boolean isFull() {
    return (primaryIndex == 0 && rearIndex == this.sizeMax - 1);
  }

  public synchronized void enqueue(T item) {
    if (isFull()) {
      System.err.println("Queue is Full , cannot add more than Max Size: " + sizeMax);
    } else {
      if (primaryIndex == -1) {
        primaryIndex = 0;
      }
      rearIndex++;
      elements[rearIndex] = item;
    }

  }

  public synchronized T dequeue() {
    if (isEmpty()) {
      throw new OutOfElementsException("Queue has no more elements to be removed");
    } else {
      // remove frontElement
      var frontElement = elements[primaryIndex];

      // when Queue has only one element
      if (primaryIndex >= rearIndex) {
        primaryIndex = -1;
        rearIndex = -1;
      } else {
        // Make next element as front element
        primaryIndex++;
      }
      return frontElement;
    }
  }

  @Override
  public boolean equals(Object o) {
    // in case if object is compared with its own instance
    if (o == this) {
      return true;
    }
    if (!(o instanceof Queue)) {
      return false;
    }
    var queue2 = (Queue<T>) o;
    if (this.elements.length != queue2.elements.length) {
      return false;
    }
    // make copies for comparision so we do not change their original state
    var queueThis = makeCopy(this);
    var queueDest = makeCopy(queue2);
    while (!queueThis.isEmpty()) {
      if (queueThis.peek() == queueDest.peek()) {
        queueThis.dequeue();
        queueDest.dequeue();
      } else {
        return false;
      }
    }
    return true;
  }

  // utility method to deep copy queue state for checking equality
  private Queue<T> makeCopy(Queue<T> queue) {
    var queueThis = new Queue<T>(queue.sizeMax);
    queueThis.primaryIndex = queue.primaryIndex;
    queueThis.rearIndex = queue.rearIndex;
    System.arraycopy(queue.elements, 0, queueThis.elements, 0, queue.elements.length);
    return queueThis;
  }
}
