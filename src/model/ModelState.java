package model;

/**
 * An interface that has the immutable methods of the model. An instance of the
 * ModelState interface can be given to view safely because the view would be unable
 * to mutate the model.
 */
public interface ModelState {

  /**
   * Returns whether the game is over.
   * @return a boolean that represents if the game is over
   */
  boolean getGameOver();

  /**
   * Returns a copy of the board of the model.
   * @return a Board[][] 2D array
   */
  Board[][] getBoard();

  /**
   * Returns the user's score.
   * @return an integer representing the user's score
   */
  int getScore();

}
