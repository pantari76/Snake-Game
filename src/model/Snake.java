package model;

/**
 * A class to represent the individual piece of the snake.
 */
public class Snake {
  private int xPosition;
  private int yPosition;

  /**
   * A constructor to initialize the snake piece.
   * @param x the x position of the snake piece
   * @param y the y position of the snake piece
   */
  public Snake(int x, int y) {
    this.xPosition = x;
    this.yPosition = y;
  }

  /**
   * Updates the position of the individual snake piece.
   * @param x the new x position of the snake piece
   * @param y the new y position of the snake piece
   */
  public void updatePosition(int x, int y) {
    this.xPosition = x;
    this.yPosition = y;
  }

  /**
   * Returns the x position of the snake piece.
   * @return an integer representing the x position
   */
  public int getXPosition() {
    return this.xPosition;
  }

  /**
   * Returns the y position of the snake piece.
   * @return an integer representing the y position
   */
  public int getYPosition() {
    return this.yPosition;
  }

}
