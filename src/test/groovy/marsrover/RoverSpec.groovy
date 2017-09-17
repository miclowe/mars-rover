package test.groovy.marsrover

import main.groovy.marsrover.Direction
import main.groovy.marsrover.Rover
import spock.lang.*

@Unroll
class RoverSpec extends Specification {

  Rover testRover

  def setup() {
    testRover = new Rover(x: 0, y: 0, direction: Direction.N)
  }

  def "should turn left"(roverDirection, expectedDirection) {
//    given:
//    testRover.turnLeft()

    when: "rover turns left"
    testRover.turnLeft()

    then: "rover should have new direction"
    testRover.direction == expectedDirection

    where:
    roverDirection  ||  expectedDirection
    Direction.N     ||  Direction.W
    new Rover(x: 0, y: 0, direction: Direction.W)     ||  Direction.S
    new Rover(x: 0, y: 0, direction: Direction.S)     ||  Direction.E
    new Rover(x: 0, y: 0, direction: Direction.E)     ||  Direction.N

  }

}
