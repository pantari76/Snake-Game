package view;

import java.awt.*;
import javax.swing.*;

import model.Board;
import model.ModelState;

/**
 * A class to represent a type of JPanel that will paint the board on it.
 */
public class GamePanel extends JPanel {
  private final ModelState model;

  /**
   * A constructor for the GamePanel.
   * @param model the ModelState object that contains only the immutable objects of the board.
   */
  public GamePanel(ModelState model) {
    super();
    this.setPreferredSize(new Dimension(500, 500));
    this.model = model;
    this.setVisible(true);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Board[][] board = this.model.getBoard();

    // Centers the board.
    int originX = (250 - ((board.length * 25) / 2));
    int originY = (250 - ((board[0].length * 25) / 2));

    // Draws each square of the board.
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        Color color = null;
        if (board[i][j].equals(Board.EMPTY)) {
          color = Color.blue;
        } else if (board[i][j].equals(Board.SNAKE)) {
          color = Color.green;
        } else if (board[i][j].equals(Board.TARGET)) {
          color = Color.red;
        }

        if (color != null) {
          g.setColor(color);
          g.fillRect((originX) + i * 25, (originY) + j * 25, 25, 25);
          if (color.equals(Color.green)) {
            g.setColor(Color.black);
            g.drawRect((originX) + i * 25, (originY) + j * 25, 25, 25);
          }
        }
      }
    }
  }

}
