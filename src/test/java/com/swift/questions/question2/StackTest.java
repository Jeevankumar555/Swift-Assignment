package com.swift.questions.question2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.swift.exception.OutOfElementsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StackTest {

  @DisplayName("test method to verify stack implementation with given example")
  @Test
  void checkForStackImplementationWithIntegers() {
    var stack = new Stack();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    assertEquals(3, stack.pop());
    assertEquals(2, stack.peek());
    assertEquals(2, stack.pop());
    assertEquals(1, stack.pop());
  }

  @DisplayName("test method to verify stack implementation with other data type")
  @Test
  void checkForStackImplementationWithStrings() {
    var stack = new Stack();
    stack.push("a");
    stack.push("b");
    stack.push("c");
    assertEquals("c", stack.pop());
    assertEquals("b", stack.peek());
    assertEquals("b", stack.pop());
    assertEquals("a", stack.pop());
  }

  @DisplayName("test method to verify removal of element when stack is empty")
  @Test
  void checkForOutOfElementsException() {
    var stack = new Stack();
    assertThrows(OutOfElementsException.class, stack::pop);
  }

  @DisplayName("test method to verify peek of element when stack is empty")
  @Test
  void checkForPeekingEmptyStack() {
    var stack = new Stack<>();
    assertNull(stack.peek());
  }

}