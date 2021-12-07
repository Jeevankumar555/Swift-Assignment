package com.swift.qestions.question1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class TownsGraph {

  private int destination;
  private int maxDistance;
  private int noStops;
  private List<String> allRoutePaths;
  private int noRoutesCount;
  private int noTripsCount;
  private final List<Edge>[] adjacentNodes;
  private static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";
  private static final String[] TOWNS = {"A", "B", "C", "D", "E"};


  public TownsGraph(String[] inputGraphSeq) {
    // build an adjacency list with size as no of vertices/towns
    adjacentNodes = (LinkedList<Edge>[]) new LinkedList[TOWNS.length];
    for (var i = 0; i < TOWNS.length; i++) {
      adjacentNodes[i] = new LinkedList<>();
    }
    constructGraph(inputGraphSeq);
  }

  private void constructGraph(String[] inputGraphSeq) {
    for (String input : inputGraphSeq) {
      int src = fetchTownIndex(Character.toString(input.charAt(0)));
      int dest = fetchTownIndex(Character.toString(input.charAt(1)));
      int weight = Character.getNumericValue(input.charAt(2));
      var edge = new Edge(src, dest, weight);
      adjacentNodes[edge.getSrc()].add(edge);
    }
  }

  public String getDistanceForPath(String path) {
    int distance = distanceOfDirectPath(path);
    if (distance == -1) {
      return NO_SUCH_ROUTE;
    }
    return Integer.toString(distance);
  }

  private int distanceOfDirectPath(String path) {
    var distance = 0;
    String[] nodes = path.split("-");
    int src;
    int dest;
    for (var i = 0; i < nodes.length - 1; ) {
      var isValidPath = false;
      src = fetchTownIndex(nodes[i++]);
      dest = fetchTownIndex(nodes[i]);
      List<Edge> edges = adjacentNodes[src];
      for (Edge edge : edges) {
        if (edge.getDest() == dest) {
          distance += edge.getWeight();
          isValidPath = true;
          break;
        }
      }
      if (!isValidPath) {
        return -1;
      }
    }
    return distance;
  }


  public int totalTripsCount(String src, String dest, Predicate<Integer> isStopsCondition,
      int stops) {
    this.destination = fetchTownIndex(dest);
    this.noStops = stops;
    this.noTripsCount = 0;
    int startIndex = fetchTownIndex(src);
    totalTripsCount(startIndex, String.valueOf(startIndex), isStopsCondition);
    return noTripsCount;
  }

  private void totalTripsCount(int src, String path, Predicate<Integer> isStopsCondition) {
    List<Edge> edges = adjacentNodes[src];
    for (Edge edge : edges) {
      String next = path + edge.getDest();
      int stopCount = next.length() - 1;
      if (this.destination == edge.getDest() && isStopsCondition.test(stopCount)) {
        noTripsCount++;
      }
      if (stopCount <= noStops) {
        totalTripsCount(edge.getDest(), next, isStopsCondition);
      }
    }
  }


  public int lengthOfShortestRoute(String src, String dest) {
    allRoutePaths = new ArrayList<>();
    this.destination = fetchTownIndex(dest);
    int startIndex = fetchTownIndex(src);
    findShortestPath(startIndex, String.valueOf(startIndex));

    int shortestDistance = Integer.MAX_VALUE;
    int currentDistance;
    for (String s : allRoutePaths) {
      currentDistance = distanceOfDirectPath(s);
      if (shortestDistance > currentDistance) {
        shortestDistance = currentDistance;
      }
    }
    if (shortestDistance == Integer.MAX_VALUE) {
      return 0;
    }
    return shortestDistance;
  }

  private void findShortestPath(int src, String path) {
    List<Edge> edges = adjacentNodes[src];
    for (Edge edge : edges) {
      // check if already visited
      if (path.length() > 1 && path.substring(1)
          .contains(String.valueOf(edge.getDest())))
      {
        continue;
      }
      String next = path + edge.getDest();
      if (this.destination == edge.getDest()) {
        allRoutePaths.add(getPathName(next));
      }
      findShortestPath(edge.getDest(), next);
    }
  }

  public int numberOfRoutesBetweenTwoNodes(String src, String dest, int maxDistance) {
    this.destination = fetchTownIndex(dest);
    this.maxDistance = maxDistance;
    this.noRoutesCount = 0;
    int startIndex = fetchTownIndex(src);
    countRoutes(startIndex, String.valueOf(startIndex));
    return noRoutesCount;
  }

  private void countRoutes(int src, String path) {
    List<Edge> edges = adjacentNodes[src];
    for (Edge e : edges) {
      String next = path + e.getDest();
      int distance = distanceOfDirectPath(getPathName(next));
      if (this.destination == e.getDest() && (distance < maxDistance)) {
        noRoutesCount++;
      }
      if (distance < maxDistance) {
        countRoutes(e.getDest(), next);
      }
    }
  }


  private static int fetchTownIndex(String town) {
    return IntStream.range(0, TOWNS.length)
        .filter(i -> town.equals(TOWNS[i]))
        .findFirst()
        .orElse(-1);
  }

  private String getTownName(int index) {
    return TOWNS[index];
  }

  private String getPathName(String path) {
    String[] arr = path.split("");
    List<String> townNames = new ArrayList();
    for (String town : arr) {
      townNames.add(getTownName(Integer.parseInt(town)));
    }
    return String.join("-", townNames);
  }
}
