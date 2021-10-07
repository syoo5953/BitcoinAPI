package model.facade.output;

import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public interface OutputAPI {
  int loggingIn(String token) throws URISyntaxException, IOException;

  void init();

  String paste(String str) throws URISyntaxException, IOException;

  boolean getIsOnline();

  CloseableHttpResponse getResponse();
}
