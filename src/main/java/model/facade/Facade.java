package model.facade;

import model.facade.input.InputAPI;
import model.facade.input.OfflineInput;
import model.facade.input.OnlineInput;
import model.facade.output.OfflineOutput;
import model.facade.output.OnlineOutput;
import model.facade.output.OutputAPI;

public class Facade {
  private final InputAPI onlineInput;
  private final InputAPI offlineInput;
  private final OutputAPI onlineOutput;
  private final OutputAPI offlineOutput;

  public Facade() {
    onlineInput = new OnlineInput();
    offlineInput = new OfflineInput();
    onlineOutput = new OnlineOutput();
    offlineOutput = new OfflineOutput();
  }

  public OnlineInput onlineIn() {
    onlineInput.init();
    return (OnlineInput) onlineInput;
  }

  public OfflineInput offlineIn() {
    offlineInput.init();
    return (OfflineInput) offlineInput;
  }

  public OnlineOutput onlineOut() {
    onlineOutput.init();
    return (OnlineOutput) onlineOutput;
  }

  public OfflineOutput offlineOut() {
    offlineOutput.init();
    return (OfflineOutput) offlineOutput;
  }
}
