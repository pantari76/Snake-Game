import controller.Controller;
import model.Model;
import model.ModelInterface;
import view.View;

/**
 * A class that contains the entry point of the program.
 */
public class SnakeMain {

  /**
   * The main method that sets up the board based on the command line arguments.
   * @param args
   */
  public static void main(String[] args) {
    int height;
    int width;
    ModelInterface model;

    if (args.length == 0) {
      model = new Model(10, 10);
    }
    else if (args.length >= 2){
      try {
        width = Integer.parseInt(args[0]);
        height = Integer.parseInt(args[1]);
        model = new Model(width, height);
      }
      catch (NumberFormatException e) {
        throw new IllegalArgumentException("An invalid board size has been given");
      }
    }
    else {
      throw new IllegalArgumentException("An invalid board size has been given");
    }

    View view = new View(model);
    Controller controller = new Controller(model, view);
    controller.run();
  }

}
