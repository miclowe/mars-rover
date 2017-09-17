package test.groovy.marsrover

import main.groovy.marsrover.MainRover
import org.junit.Test

import static org.junit.Assert.assertEquals

class MainRoverTests extends GroovyTestCase {

  ByteArrayOutputStream out

  void setUp() {
    out = new ByteArrayOutputStream()
    System.setOut(new PrintStream(out))
  }

  protected void setArgs(String args) {
    System.setIn(new ByteArrayInputStream(args.getBytes()))
  }

  protected String getResult() {
    return out.toString()
  }

  @Test
  void testMain_PrintlnsEachResult() {
    String args = "5 5\n" +
                  "1 2 N\n" +
                  "LMLMLMLMM\n" +
                  "y\n" +
                  "3 3 E\n" +
                  "MMRMMRMRRM\n" +
                  "n"
    setArgs(args)

    MainRover.main(new String())

    assertTrue(getResult().contains("1 3 N"))
    assertTrue(getResult().contains("5 1 E"))
  }

  @Test
  void testStartErUp_ReturnsListOfRoverFinalCoordinates() {
    List<Integer> plateauDimensions = [5, 5]
    List<Map> roverList = [
        [x: 1, y: 2, direction: "N", commands: "LMLMLMLMM"],
        [x: 3, y: 3, direction: "E", commands: "MMRMMRMRRM"]
    ]

    def result = MainRover.startErUp(plateauDimensions, roverList)
    assertEquals(['1 3 N', '5 1 E'], result)
  }

  @Test
  void testGetInput_SetsInputParameters() {
    String args = "5 5\n" +
                  "1 2 N\n" +
                  "LMLMLMLMM\n" +
                  "n"
    setArgs(args)

    def result = MainRover.getInput()

    assert result
    assertEquals(2, result.size())

    assert result.containsKey('plateauDimensions')
    assert result.containsKey('roverList')

    assertEquals(2, result['plateauDimensions'].size())
    assert result['plateauDimensions'] == [5, 5]

    assertEquals(1, result['roverList'].size())

    def rover1 = result['roverList'][0]
    assertEquals(1, rover1.x)
    assertEquals(2, rover1.y)
    assertEquals("N", rover1.direction)
    assertEquals("LMLMLMLMM", rover1.commands)
  }

  @Test
  void testGetInput_SetsInputParameters_MultipleRovers() {
    String args = "5 5\n" +
                  "1 2 N\n" +
                  "LMLMLMLMM\n" +
                  "y\n" +
                  "3 3 E\n" +
                  "MMRMMRMRRM\n" +
                  "n"
    setArgs(args)

    def result = MainRover.getInput()

    assert result
    assertEquals(2, result.size())

    assert result.containsKey('plateauDimensions')
    assert result.containsKey('roverList')

    assertEquals(2, result['roverList'].size())

    def rover1 = result['roverList'][0]
    assertEquals(1, rover1.x)
    assertEquals(2, rover1.y)
    assertEquals("N", rover1.direction)
    assertEquals("LMLMLMLMM", rover1.commands)

    def rover2 = result['roverList'][1]
    assertEquals(3, rover2.x)
    assertEquals(3, rover2.y)
    assertEquals("E", rover2.direction)
    assertEquals("MMRMMRMRRM", rover2.commands)
  }

  @Test
  void testGetInput_InvalidPlateauDimensionsRetriesUntilValid() {
    String args = "a 5\n" +
                  "5 9 0\n" +
                  "-1 0\n" +
                  "5 5"
    setArgs(args)

    try {
      MainRover.getInput()
    } catch (Exception e) {
      assertEquals(3, (getResult() =~ /Invalid dimensions/).count)
      assertEquals(1, (getResult() =~ /Enter Rover's starting coordinates/).count)
    }
  }

  @Test
  void testGetInput_InvalidRoverCoordinatesRetriesUntilValid() {
    String args = "5 5\n" +
                  "a 3 N\n" +
                  "3 b N\n" +
                  "0 0 x\n" +
                  "0 0 n"
    setArgs(args)

    try {
      MainRover.getInput()
    } catch (Exception e) {
      assertEquals(3, (getResult() =~ /Invalid Rover data/).count)
      assertEquals(1, (getResult() =~ /Enter Rover's commands/).count)
    }
  }

  @Test
  void testGetInput_InvalidRoverCommandsRetriesUntilValid() {
    String args = "5 5\n" +
                  "0 0 n\n" +
                  "gobbledigook\n" +
                  "lmLmlmlMm"
    setArgs(args)

    try {
      MainRover.getInput()
    } catch (Exception e) {
      assertEquals(1, (getResult() =~ /Invalid command\(s\)/).count)
      assertEquals(1, (getResult() =~ /Add another rover/).count)
    }

  }

}
