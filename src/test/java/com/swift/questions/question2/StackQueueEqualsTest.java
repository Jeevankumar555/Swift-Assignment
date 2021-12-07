package com.swift.questions.question2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StackQueueEqualsTest {

  @DisplayName("test method to verify for equals rules for stack and queue instances")
  @Test
  void checkForEqualsMethod() {
    var q1 = new Queue<>();
    var q2 = new Queue<>();
    var s1 = new Stack<>();
    var s2 = new Stack<>();

    s1.push(1);
    s1.push(2);
    s2.push(2);
    s2.push(3);

    q1.enqueue(1);
    q1.enqueue(2);
    q1.enqueue(3);
    q2.enqueue(2);
    q2.enqueue(3);

    assertNotEquals(q1, s1);
    assertNotEquals(q1, q2);
    assertNotEquals(s2, s1);
    assertEquals(q2, q2);

    s1.pop();
    s1.pop();
    s2.pop();
    s1.push(2);
    q1.dequeue();

    assertNotEquals(q1, s1);
    assertEquals(q1, q2);
    assertEquals(s2, s1);
    assertEquals(q2, q2);

  }
}
