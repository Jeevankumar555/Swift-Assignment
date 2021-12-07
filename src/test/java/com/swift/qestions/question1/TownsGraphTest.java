package com.swift.qestions.question1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TownsGraphTest {

  private TownsGraph routes;

  @BeforeEach
  public void setUp() {
    var inputArray = new String[]{"AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3",
        "AE7"};
    routes = new TownsGraph(inputArray);
  }

  @DisplayName("test for distance between route A-B-C")
  @Test
  void checkForDistanceBetweenABC() {
    assertEquals("9", routes.getDistanceForPath("A-B-C"));
  }

  @DisplayName("test for distance between route A-D")
  @Test
  void checkForDistanceBetweenAD() {
    assertEquals("5", routes.getDistanceForPath("A-D"));
  }

  @DisplayName("test for distance between route A-D-C")
  @Test
  void checkForDistanceBetweenADC() {
    assertEquals("13", routes.getDistanceForPath("A-D-C"));
  }

  @DisplayName("test for distance between route A-E-B-C-D")
  @Test
  void checkForDistanceBetweenAEBCD() {
    assertEquals("22", routes.getDistanceForPath("A-E-B-C-D"));
  }

  @DisplayName("test for distance between route A-E-D")
  @Test
  void checkForDistanceBetweenAED() {
    assertEquals("NO SUCH ROUTE", routes.getDistanceForPath("A-E-D"));
  }

  @DisplayName("test for C-C trips with max 3 stops")
  @Test
  void checkForTripsWithMax3StopsBetweenCToC() {
    assertEquals(2, routes.totalTripsCount("C", "C", stop -> stop <= 3, 3));
  }

  @DisplayName("test for A-C trips with exactly 4 stops")
  @Test
  void checkForTripsWith4StopsBetweenAToC() {
    assertEquals(3, routes.totalTripsCount("A", "C", stop -> stop == 4, 4));
  }

  @DisplayName("test for length of shortest route from A-C")
  @Test
  void checkForShortestRouteLengthBetweenAToC() {
    assertEquals(9, routes.lengthOfShortestRoute("A", "C"));
  }

  @DisplayName("test for length of shortest route from B-B")
  @Test
  void checkForShortestRouteLengthBetweenBToB() {
    assertEquals(9, routes.lengthOfShortestRoute("B", "B"));
  }

  @DisplayName("test for number of different routes from C-C with < 30 distance")
  @Test
  void checkForDifferentRoutesBetweenCToCWithinAllowedDistance() {
    assertEquals(7, routes.numberOfRoutesBetweenTwoNodes("C", "C", 30));
  }
}