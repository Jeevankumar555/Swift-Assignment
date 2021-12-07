package com.swift.qestions.question1;

public class Edge {

  private final int src;
  private final int dest;
  private final int weight;

  public Edge(int src, int dest, int weight) {
    this.src = src;
    this.dest = dest;
    this.weight = weight;
  }

  public int getSrc() {
    return src;
  }

  public int getDest() {
    return dest;
  }

  public int getWeight() {
    return weight;
  }
}