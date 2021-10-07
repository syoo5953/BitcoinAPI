package model.facade.input;

import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public interface InputAPI {

  int loggingIn(String token) throws URISyntaxException, IOException;

  String viewCryptoList() throws URISyntaxException, IOException;

  String viewCryptoInfo(String name, String value) throws URISyntaxException, IOException;

  String viewCryptoCurrency(String name, String baseCurrency, String quoteCurrency, String amount);

  boolean getIsOnline();

  void init();

  CloseableHttpResponse getResponse();
}
