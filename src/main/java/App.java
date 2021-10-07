import controller.Controller;
import controller.LoginController;
import model.Model;
import model.ModelEngine;
import view.LoginView;
import view.View;

import java.sql.SQLException;

public class App {

  public static void main(String[] args) throws SQLException {

    Model model = new ModelEngine(args);
    View view = new LoginView(model);
    Controller loginController = new LoginController(model, view);
    loginController.initController();
  }
}
