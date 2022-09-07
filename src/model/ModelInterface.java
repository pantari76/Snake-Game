package model;

/**
 * The ModelInterface contains the methods that mutate the model, which is the
 * updateSnakePositions method. This interface is to allow the controller to change the model
 * as needed.
 */
public interface ModelInterface extends ModelState {

  /**
   * The method changes the direction of the snake. It adds the x and y of the parameters to the
   * head of the snake.
   * @param x what to add to the x position of the head of snake
   * @param y what to add to the y position of the head of snake
   */
  void updateSnakePositions(int x, int y);
  
  /**
   * This method restarts the game allowing the user to play again.
   */
  void restart();

}
