package main.groovy.marsrover

enum Move {

  LEFT("L"),
  RIGHT("R"),
  FORWARD("M")

  final String id

  private Move(String id) {
    this.id = id
  }
}
