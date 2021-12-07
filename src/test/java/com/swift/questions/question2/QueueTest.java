package com.swift.questions.question2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.swift.exception.OutOfElementsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueueTest {

  @DisplayName("test method to verify queue implementation with given example")
  @Test
  void checkForQueueImplementationWithIntegers() {
    var queue = new Queue<>();
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    assertEquals(1, queue.dequeue());
    assertEquals(2, queue.dequeue());
    assertEquals(3, queue.peek());
    assertEquals(3, queue.dequeue());
  }

  @DisplayName("test method to verify queue implementation with other data type")
  @Test
  void checkForQueueImplementationWithStrings() {
    var queue = new Queue<>();
    queue.enqueue("a");
    queue.enqueue("b");
    queue.enqueue("c");
    assertEquals("a", queue.dequeue());
    assertEquals("b", queue.dequeue());
    assertEquals("c", queue.peek());
    assertEquals("c", queue.dequeue());
  }

  @DisplayName("test method to verify removal of element when queue is empty")
  @Test
  void checkForOutOfElementsException() {
    var queue = new Queue<>();
    assertThrows(OutOfElementsException.class, queue::dequeue);
  }

  @DisplayName("test method to verify peek of element when queue is empty")
  @Test
  void checkForPeekingEmptyQueue() {
    var queue = new Queue<>();
    assertNull(queue.peek());
  }
}