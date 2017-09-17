package main.groovy.marsrover

import groovy.transform.ToString

@ToString
class Plateau {

  int height

  int width

  // Make sure the move is within the grid dimensions
  boolean isMoveValid(int x, int y) {
    boolean valid = true
    if (x < 0 || x > width) {
      valid = false
    }

    if (y < 0 || y > height) {
      valid = false
    }
    valid
  }
}
