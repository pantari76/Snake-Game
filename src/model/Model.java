package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class to represent the Model. This class handles the growth of the snake, moving the snake,
 * and generating a target for the snake.
 */
public class Model implements ModelInterface {
  private final List<Snake> snake;
  private final Board[][] board;
  private boolean gameOver;
  private int targetRow;
  private int targetCol;

  /**
   * A constructor for the Model. It sets up the snake and the board.
   * @param width the width of the board
   * @param height the height of the board
   * @throws IllegalArgumentException if the width and/or the height are invalid
   */
  public Model(int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException();
    }
    this.snake = new ArrayList<Snake>();
    this.snake.add(new Snake(width / 2, height / 2));
    this.board = new Board[width][height];
    this.clearBoard();
    this.board[width / 2][height / 2] = Board.SNAKE;
    this.gameOver = false;
    this.generateNewTarget();
  }

  @Override
  public void updateSnakePositions(int changeX, int changeY) {
    // Clears the board.
    this.clearBoard();

    // Sets the target on the board.
    this.board[targetRow][targetCol] = Board.TARGET;

    // Updates each position of the snake.
    for (int i = this.snake.size() - 1; i >= 0; i--) {
      if (i == 0) {
        try {
          if (this.board[this.snake.get(i).getXPosition() + changeX]
                  [this.snake.get(i).getYPosition() + changeY].equals(Board.TARGET)) {
            this.grow();
            this.generateNewTarget();
          }
          else if (this.board[this.snake.get(i).getXPosition() + changeX]
                  [this.snake.get(i).getYPosition() + changeY].equals(Board.SNAKE)) {
            this.gameOver = true;
          }
          this.board[this.snake.get(i).getXPosition() + changeX]
                  [this.snake.get(i).getYPosition() + changeY] = Board.SNAKE;
        } catch (IndexOutOfBoundsException e) {
          this.gameOver = true;
        }
        this.snake.get(i).updatePosition(this.snake.get(i).getXPosition() + changeX,
                this.snake.get(i).getYPosition() + changeY);
      }
      else {
        this.snake.get(i).updatePosition(this.snake.get(i - 1).getXPosition(),
                this.snake.get(i - 1).getYPosition());
        this.board[this.snake.get(i - 1).getXPosition()]
                [this.snake.get(i - 1).getYPosition()] = Board.SNAKE;
      }
    }
  }

  /**
   * If the snake hits a target, then a new portion of it grows at the last ligament of the snake.
   */
  private void grow() {
    this.snake.add(new Snake(this.snake.get(this.snake.size() - 1).getXPosition(),
            this.snake.get(this.snake.size() - 1).getYPosition()));
    this.board[this.snake.get(this.snake.size() - 1).getXPosition()]
            [this.snake.get(this.snake.size() - 1).getYPosition()]
            = Board.SNAKE;
  }

  @Override
  public boolean getGameOver() {
    if (this.snake.size() == (this.board.length * this.board[0].length) - 1) {
      return true;
    }
    else {
      return this.gameOver;
    }
  }

  /**
   * Generates a new position on the board for the target. If the position generated is occupied
   * by a piece of the snake, then a new position is generated.
   */
  private void generateNewTarget() {
    Random rand = new Random();
    this.board[targetRow][targetCol] = Board.EMPTY;
    boolean keepChecking = true;
    while (keepChecking) {
      this.targetRow = rand.nextInt(this.board.length);
      this.targetCol = rand.nextInt(this.board[0].length);

      if (!this.board[targetRow][targetCol].equals(Board.SNAKE)) {
        this.board[targetRow][targetCol] = Board.TARGET;
        keepChecking = false;
      }
    }
  }

  @Override
  public Board[][] getBoard() {
    return this.board.clone();
  }

  /**
   * Clears the board to allow for the snake positions to be updated.
   */
  private void clearBoard() {
    for (int i = 0; i < this.board.length; i++) {
      for (int j = 0; j < this.board[0].length; j++) {
        this.board[i][j] = Board.EMPTY;
      }
    }
  }

  @Override
  public int getScore() {
    return this.snake.size();
  }

  @Override
  public void restart() {
    this.snake.clear();
    this.clearBoard();
    this.snake.add(new Snake(this.board.length / 2, this.board[0].length / 2));
    this.board[this.board.length / 2][this.board[0].length / 2] = Board.SNAKE;
    this.gameOver = false;
    this.generateNewTarget();
  }
}
