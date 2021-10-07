package model.facade.output;

import org.apache.http.client.methods.CloseableHttpResponse;

public class OfflineOutput implements OutputAPI {

  private boolean logged_in = false;
  private boolean isOnline = false;

  @Override
  public int loggingIn(String token) {
    if (token.equals("53240032-0bea-482c-9273-f346c3f17632")) {
      this.logged_in = true;
      return 200;
    } else {
      return 401;
    }
  }

  @Override
  public void init() {
    isOnline = false;
  }

  @Override
  public String paste(String str) {
    if (this.logged_in) {
      return "https://pastebin.com/dummyAPI";
    } else {
      return null;
    }
  }

  @Override
  public boolean getIsOnline() {
    return this.isOnline;
  }

  @Override
  public CloseableHttpResponse getResponse() {
    return null;
  }
}
