package com.swift.questions.question2;

import com.swift.exception.OutOfElementsException;

public class Stack<T> extends ElementsStore<T> {

  public Stack() {
    super();
  }

  @SuppressWarnings("unchecked")
  public Stack(int sizeMax) {
    super(sizeMax);
  }

  @Override
  public synchronized boolean isFull() {
    return primaryIndex == sizeMax - 1;
  }

  public synchronized void push(T item) {
    if (isFull()) {
      System.err.println("Stack is Full , cannot add more than Max Size: " + sizeMax);
      return;
    }
    elements[++primaryIndex] = item;
  }

  public synchronized T pop() {
    if (isEmpty()) {
      throw new OutOfElementsException("Stack has no more elements to be removed");
    }
    return elements[primaryIndex--];
  }

  @Override
  public boolean equals(Object o) {
    // in case if object is compared with its own instance
    if (o == this) {
      return true;
    }
    if (!(o instanceof Stack)) {
      return false;
    }
    var stack2 = (Stack<T>) o;
    if (this.elements.length != stack2.elements.length) {
      return false;
    }
    // make copies for comparision so we do not change their original state
    var stackThis = makeCopy(this);
    var stackDest = makeCopy(stack2);

    while (!stackThis.isEmpty()) {
      if (stackThis.peek() == stackDest.peek()) {
        stackThis.pop();
        stackDest.pop();
      } else {
        return false;
      }
    }
    return true;
  }

  // utility method to deep copy stack state for checking equality
  private Stack<T> makeCopy(Stack<T> stack) {
    var stackThis = new Stack<T>(stack.sizeMax);
    stackThis.primaryIndex = stack.primaryIndex;
    System.arraycopy(stack.elements, 0, stackThis.elements, 0, stack.elements.length);
    return stackThis;
  }
}