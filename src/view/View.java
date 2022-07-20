package view;

import java.awt.*;
import java.awt.event.KeyListener;

import javax.swing.*;

import model.ModelState;

/**
 * A class to represent the View. This class creates the GUI that the user interacts with.
 */
public class View extends JFrame {
  private final JLabel messageLabel;

  /**
   * A constructor for the view. Sets up the JFrame and the JPanels within.
   * @param model
   */
  public View(ModelState model) {
    this.setSize(new Dimension(500, 500));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    GamePanel panel = new GamePanel(model);
    panel.setSize(500, 500);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JPanel messagePanel = new JPanel();
    this.messageLabel = new JLabel();

    this.add(panel, BorderLayout.CENTER);
    this.add(messagePanel, BorderLayout.SOUTH);
    messagePanel.add(this.messageLabel);
    this.setVisible(true);
  }

  /**
   * Sets whatever KeyListener given to the KeyListener of this JFrame.
   * @param listener the KeyListener to be added
   */
  public void setKeyListener(KeyListener listener) {
    this.addKeyListener(listener);
  }

  /**
   * Changes the text of the message JLabel.
   * @param message the message to be displayed
   */
  public void renderMessage(String message) {
    this.messageLabel.setText(message);
  }

  /**
   * Refresh the drawing on the board.
   */
  public void refresh() {
    this.repaint();
  }

}
