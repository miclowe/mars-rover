package test.groovy.marsrover

import main.groovy.marsrover.Plateau
import org.junit.Before
import org.junit.Test

class PlateauTests extends GroovyTestCase {

  Plateau testPlateau

  @Before
  void setUp() {
    testPlateau = new Plateau(width: 5, height: 5)
  }

  @Test
  void testIsMoveValid_WithinMaxWidthAndHeight_True() {
    assert testPlateau.isMoveValid(0, 0)
  }

  @Test
  void testIsMoveValid_ExceedsMaxWidth_False() {
    assert !testPlateau.isMoveValid(6, 5)
  }

  @Test
  void testIsMoveValid_ExceedsMaxHeight_False() {
    assert !testPlateau.isMoveValid(5, 6)
  }

  @Test
  void testIsMoveValid_BelowMinWidth_False() {
    assert !testPlateau.isMoveValid(-6, 5)
  }

  @Test
  void testIsMoveValid_BelowMinHeight_False() {
    assert !testPlateau.isMoveValid(5, -5)
  }
}
