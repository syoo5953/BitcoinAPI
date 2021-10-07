package model;

import model.facade.input.InputAPI;
import model.facade.output.OutputAPI;

public interface Model {
  InputAPI getInputAPIModel();

  OutputAPI getOutputAPIModel();

  UserInfo getUser();
}
