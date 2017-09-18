package main.groovy.marsrover

import groovy.transform.ToString

@ToString(includeNames = true)
class Rover {

  int x

  int y

  Direction direction

  void move(String command, Plateau grid) {
    switch (command) {
      case Move.FORWARD.id:
        moveForward(grid)
        break
      case Move.LEFT.id:
        turnLeft()
        break
      case Move.RIGHT.id:
        turnRight()
        break
      default:
        break
    }
  }

  void turnLeft() {
    // Same as the 2 lines beneath
    direction = direction.previous()
    // List<Direction> dirList = Direction.values()
    // direction = dirList[(direction.ordinal() + 1) % dirList.size()]

  }

  void turnRight() {
    // Same as the 2 lines beneath
    direction = direction.next()
    // List<Direction> dirList = Direction.values()
    // direction = dirList[(direction.ordinal() - 1) % dirList.size()]
  }

  // Move Rover according to the commands
  // If a move is not valid, Rover stays where it is
  void moveForward(Plateau grid) {
    int newXPos = x
    int newYPos = y
    switch (direction) {
      case Direction.N:
        newYPos = this.y + 1
        if (grid.isMoveValid(x, newYPos)) {
          y = newYPos
        }
        break
      case Direction.E:
        newXPos = this.x + 1
        if (grid.isMoveValid(newXPos, y)) {
          x = newXPos
        }
        break
      case Direction.S:
        newYPos = this.y - 1
        if (grid.isMoveValid(x, newYPos)) {
          y = newYPos
        }
        break
      case Direction.W:
        newXPos = this.x - 1
        if (grid.isMoveValid(newXPos, y)) {
          x = newXPos
        }
        break
      default:
        break
    }
  }
}
