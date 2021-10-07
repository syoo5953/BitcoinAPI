package model;

import model.facade.Facade;
import model.facade.input.InputAPI;
import model.facade.output.OutputAPI;

public class ModelEngine implements Model {
  String first_arg;
  String second_arg;
  InputAPI inputModel;
  OutputAPI outputModel;
  Facade facade = new Facade();
  UserInfo userInfo = new UserInfo();

  public ModelEngine(String[] args) {
    this.first_arg = args[0];
    this.second_arg = args[1];

    if (this.first_arg.equalsIgnoreCase("online")) {
      inputModel = facade.onlineIn();
    } else if (this.first_arg.equalsIgnoreCase("offline")) {
      inputModel = facade.offlineIn();
    }

    if (this.second_arg.equalsIgnoreCase("online")) {
      outputModel = facade.onlineOut();
    } else if (this.second_arg.equalsIgnoreCase("offline")) {
      outputModel = facade.offlineOut();
    }
  }

  public InputAPI getInputAPIModel() {
    return inputModel;
  }

  public OutputAPI getOutputAPIModel() {
    return outputModel;
  }

  public UserInfo getUser() {
    return userInfo;
  }
}
