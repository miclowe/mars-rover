package main.groovy.marsrover

class MainRover {

  final static List<String> VALID_DIRECTIONS = Direction.values().collect { it.toString() }
  final static List<String> VALID_MOVES = ['L', 'M', 'R']

  static void main(String... args) {
    Map input = getInput()
    List<String> result = startErUp(input.plateauDimensions, input.roverList)
    result.each { println it }
  }

  static List<String> startErUp(List<Integer> plateauDimensions, List<Map> roverList) {
    List<String> result = []
    Plateau grid = new Plateau(width: plateauDimensions[0], height: plateauDimensions[1])

    roverList.each { r ->
      Rover rover = new Rover(x: r.x, y: r.y, direction: r.direction)
      String commands = r.commands
      List<String> commandList = commands.split('')

      commandList.each { command ->
        rover.move(command, grid)
      }

      result << [rover.x, rover.y, rover.direction].join(" ")
    }
    result
  }

  // Get the Plateau dimensions, Rover coordinates and commands
  static Map getInput() {
    List<Map> roverList = []
    Map result =[:]
    boolean addMore = true

    System.in.withReader { br ->

      // Get plateau dimensions
      while (!result['plateauDimensions']) {
        println("Enter the Plateau width and height. (i.e. 5 5)")
        try {
          List<Integer> dimensions = br.readLine().trim().split()*.toInteger()
          // Make sure grid starts at [0, 0]
          if (dimensions.size() == 2 && dimensions[0] >= 0 && dimensions[1] >= 0) {
            result['plateauDimensions'] = dimensions
          } else {
            throw new IllegalArgumentException()
          }
        } catch (IllegalArgumentException e) {
          println("Invalid dimensions. Please try again.")
        }
      }

      while (addMore) {
        Map rover = [:]

        // Get Rover starting point and direction it's facing
        while (!rover['direction']) {
          println("Enter Rover's starting coordinates and direction it's facing. (i.e. 1 2 N)")
          try {
            List roverData = br.readLine().split()
            int inputX = roverData[0].toInteger()
            int inputY = roverData[1].toInteger()
            // Make sure direction is valid and x and y are within the grid dimensions
            if (roverData[2]?.toUpperCase() in VALID_DIRECTIONS
                && inputX >= 0 && inputX < result['plateauDimensions'][0]
                && inputY >= 0 && inputY < result['plateauDimensions'][1]) {
              rover['x'] = inputX
              rover['y'] = inputY
              rover['direction'] = roverData[2].toUpperCase()
            } else {
              throw new IllegalArgumentException()
            }
          } catch (IllegalArgumentException e) {
            println("Invalid Rover data. Please try again.")
          }
        }

        // Get Rover commands
        while (!rover['commands']) {
          println("Enter Rover's commands. (i.e. LMLMLMLMM)")
          String commands = br.readLine().toUpperCase()
          // Make sure commands are valid
          if (commands.matches("${VALID_MOVES}+")) {
            rover['commands'] = commands
            roverList << rover
          } else {
            println("Invalid command(s). Please try again.")
          }
        }

        println("Add another rover? y/n")
        addMore = br.readLine().toLowerCase() == "y"
      }
      result['roverList'] = roverList
    }
    result
  }
}