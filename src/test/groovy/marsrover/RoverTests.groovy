package test.groovy.marsrover

import main.groovy.marsrover.Direction
import main.groovy.marsrover.Plateau
import main.groovy.marsrover.Rover
import org.junit.Test

class RoverTests extends GroovyTestCase {

  Rover testRover
  Plateau testPlateau

  int beforeX
  int beforeY

  void setUp() {
    testRover = new Rover(x: 0, y: 0, direction: Direction.N)
    testPlateau = new Plateau(width: 3, height: 4)
    beforeX = testRover.x
    beforeY = testRover.y
  }

  @Test
  void testMove_Left() {
    testRover.move("L", testPlateau)
    assertEquals(Direction.W, testRover.direction)
  }

  @Test
  void testMove_Right() {
    testRover.move("R", testPlateau)
    assertEquals(Direction.E, testRover.direction)
  }

  @Test
  void testMove_Forward() {
    testRover.move("M", testPlateau)
    assertEquals(beforeX, testRover.x)
    assertEquals(beforeY + 1, testRover.y)
  }

  @Test
  void testTurnLeft_NtoW() {
    testRover.turnLeft()
    assertEquals(Direction.W, testRover.direction)
  }

  @Test
  void testTurnLeft_WtoS() {
    testRover.direction = Direction.W
    testRover.turnLeft()
    assertEquals(Direction.S, testRover.direction)
  }

  @Test
  void testTurnLeft_StoE() {
    testRover.direction = Direction.S
    testRover.turnLeft()
    assertEquals(Direction.E, testRover.direction)
  }

  @Test
  void testTurnLeft_EtoN() {
    testRover.direction = Direction.E
    testRover.turnLeft()
    assertEquals(Direction.N, testRover.direction)
  }

  @Test
  void testTurnRight_NtoE() {
    testRover.turnRight()
    assertEquals(Direction.E, testRover.direction)
  }

  @Test
  void testTurnRight_EtoS() {
    testRover.direction = Direction.E
    testRover.turnRight()
    assertEquals(Direction.S, testRover.direction)
  }

  @Test
  void testTurnRight_StoW() {
    testRover.direction = Direction.S
    testRover.turnRight()
    assertEquals(Direction.W, testRover.direction)
  }

  @Test
  void testTurnRight_WtoN() {
    testRover.direction = Direction.W
    testRover.turnRight()
    assertEquals(Direction.N, testRover.direction)
  }

  @Test
  void testMoveForward_StartN_MoveValid() {
    testRover.moveForward(testPlateau)
    assertEquals(beforeY + 1, testRover.y)
    assertEquals(beforeX, testRover.x)
  }

  @Test
  void testMoveForward_StartE_MoveValid() {
    testRover.direction = Direction.E

    testRover.moveForward(testPlateau)
    assertEquals(beforeX + 1, testRover.x)
    assertEquals(beforeY, testRover.y)
  }

  @Test
  void testMoveForward_StartS_MoveValid() {
    testRover.direction = Direction.S
    testRover.y = 1

    beforeY = testRover.y

    testRover.moveForward(testPlateau)
    assertEquals(beforeY - 1, testRover.y)
    assertEquals(beforeX, testRover.x)
  }

  @Test
  void testMoveForward_StartW_MoveValid() {
    testRover.direction = Direction.W
    testRover.x = 1

    beforeX = testRover.x

    testRover.moveForward(testPlateau)
    assertEquals(beforeX - 1, testRover.x)
    assertEquals(beforeY, testRover.y)
  }

  @Test
  void testMoveForward_StartN_MoveInvalid() {
    testRover.y = testPlateau.height
    beforeY = testRover.y

    testRover.moveForward(testPlateau)
    assertEquals(beforeY, testRover.y)
    assertEquals(beforeX, testRover.x)
  }

  @Test
  void testMoveForward_StartW_MoveInvalid() {
    testRover.direction = Direction.W

    testRover.moveForward(testPlateau)
    assertEquals(beforeY, testRover.y)
    assertEquals(beforeX, testRover.x)
  }

  @Test
  void testMoveForward_StartS_MoveInvalid() {
    testRover.direction = Direction.S

    testRover.moveForward(testPlateau)
    assertEquals(beforeY, testRover.y)
    assertEquals(beforeX, testRover.x)
  }

  @Test
  void testMoveForward_StartE_MoveInvalid() {
    testRover.direction = Direction.E
    testRover.x = testPlateau.width

    beforeX = testRover.x

    testRover.moveForward(testPlateau)
    assertEquals(beforeY, testRover.y)
    assertEquals(beforeX, testRover.x)
  }
}
