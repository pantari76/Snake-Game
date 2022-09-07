package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.ModelInterface;
import view.View;

/**
 * A class to represent the Controller of the program, which delegates the user's requests to
 * Model and View.
 */
public class Controller implements KeyListener {
  private int moveX;
  private int moveY;
  private final ModelInterface model;
  private final View view;
  private boolean restart;

  /**
   * Constructor to set up the Controller.
   * @param model the ModelInterface object with all methods of the model
   * @param view the View object
   */
  public Controller(ModelInterface model, View view) {
    this.model = model;
    this.view = view;
    this.moveX = 1;
    this.moveY = 0;
    this.view.setKeyListener(this);
    this.restart = false;
  }

  /**
   * Runs the program. It takes in user input and changes the direction of the snake accordingly
   * until the game is over.
   */
  public void run() {
    this.performOperations();
  }

  /**
   * This method is seperate from run() to allow for restarts to occur.
   */
  private void performOperations() {
    while (!this.model.getGameOver()) {
      // The thread is put to sleep for 200 milliseconds which corresponds to the speed of the
      // snake and the speed at which inputs are registered.
      try {
        Thread.sleep(180);
      }
      catch (InterruptedException ignored) {}
      this.model.updateSnakePositions(this.moveX, this.moveY);
      this.view.refresh();
      this.view.renderMessage("Score: " + this.model.getScore());
    }
    this.view.renderMessage("Game over! Score: " + this.model.getScore());
    while(!this.restart) {
      try {
        Thread.sleep(20);
      }
      catch (InterruptedException ignored) {}
    }
    this.restart = false;
    this.run();
  }

  /**
   * Changes the direction of the head of the snake based on the arrow key that is pressed.
   * @param e the event to be processed
   */
  @Override
  public void keyPressed(KeyEvent e) {
    int command = e.getKeyCode();
    switch (command) {
      case KeyEvent.VK_LEFT:
        if (this.moveX != 1) {
          this.moveX = -1;
          this.moveY = 0;
        }
        break;
      case KeyEvent.VK_RIGHT:
        if (this.moveX != -1) {
          this.moveX = 1;
          this.moveY = 0;
        }
        break;
      case KeyEvent.VK_UP:
        if (this.moveY != 1) {
          this.moveX = 0;
          this.moveY = -1;
        }
        break;
      case KeyEvent.VK_DOWN:
        if (this.moveY != -1) {
          this.moveX = 0;
          this.moveY = 1;
        }
        break;
      case KeyEvent.VK_R:
        this.restart = true;
        this.moveX = 1;
        this.moveY = 0;
        this.model.restart();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {}

  @Override
  public void keyTyped(KeyEvent e) {}

}

