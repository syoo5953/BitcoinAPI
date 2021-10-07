package model.facade.input;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class OnlineInput implements InputAPI {

  private static final CloseableHttpClient client = HttpClients.createDefault();
  private CloseableHttpResponse response;
  private boolean isOnline = false;
  private String token = null;

  private static boolean isNumeric(String strNum) {
    if (strNum == null) {
      return false;
    }
    try {
      int d = Integer.parseInt(strNum);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }

  public void init() {
    isOnline = true;
  }

  @Override
  public boolean getIsOnline() {
    return this.isOnline;
  }

  public int loggingIn(String token) throws URISyntaxException, IOException {
    URIBuilder userBuild = new URIBuilder("https://pro-api.coinmarketcap.com/v1/key/info");
    HttpGet get = new HttpGet(userBuild.build());
    get.setHeader("X-CMC_PRO_API_KEY", token);
    try {
      response = client.execute(get);
      if (response.getStatusLine().getStatusCode() == 200) {
        this.token = token;
      }
    } finally {
      response.close();
    }

    return response.getStatusLine().getStatusCode();
  }

  @Override
  public String viewCryptoList() {
    String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
    List<NameValuePair> parameters = new ArrayList<>();
    parameters.add(new BasicNameValuePair("start", "1"));
    parameters.add(new BasicNameValuePair("limit", "300"));
    String result = null;
    try {
      result = makeAPICall(uri, parameters);
    } catch (IOException e) {
      System.out.println("Error: cannot access content - " + e.toString());
    } catch (URISyntaxException e) {
      System.out.println("Error: Invalid URL " + e.toString());
    }
    return result;
  }

  @Override
  public String viewCryptoInfo(String name, String value) {

    String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/info";
    List<NameValuePair> parameters = new ArrayList<>();
    parameters.add(new BasicNameValuePair(name, value));
    String result = null;
    try {
      result = makeAPICall(uri, parameters);
    } catch (IOException e) {
      System.out.println("Error: cannot access content - " + e.toString());
    } catch (URISyntaxException e) {
      System.out.println("Error: Invalid URL " + e.toString());
    }
    return result;
  }

  @Override
  public String viewCryptoCurrency(
      String name, String baseCurrency, String quoteCurrency, String amount) {
    int defensive_integer = Integer.parseInt(amount);
    String convert;

    if (defensive_integer >= 1 && defensive_integer <= 1000) {
      if (name.equals("id")) {
        if (!isNumeric(baseCurrency) || !isNumeric(quoteCurrency)) {
          JOptionPane.showMessageDialog(null, "The type of value is not correct");
          throw new IllegalArgumentException("The type of value is not correct");
        }
      }
      if (name.equals("symbol")) {
        if (isNumeric(baseCurrency) || isNumeric(quoteCurrency)) {
          JOptionPane.showMessageDialog(null, "The type of value is not correct");
          throw new IllegalArgumentException("The type of value is not correct");
        }
      }

      if (name.equals("id")) {
        convert = "convert_id";
      } else if (name.equals("symbol")) {
        convert = "convert";
      } else {
        throw new IllegalArgumentException(
            "The parameter name/convert/convert_id should be either id or symbol");
      }
      String uri = "https://pro-api.coinmarketcap.com/v1/tools/price-conversion";
      List<NameValuePair> parameters = new ArrayList<>();
      parameters.add(new BasicNameValuePair(name, baseCurrency));
      parameters.add(new BasicNameValuePair(convert, quoteCurrency));
      parameters.add(new BasicNameValuePair("amount", amount));
      String result = null;
      try {
        result = makeAPICall(uri, parameters);
      } catch (IOException e) {
        System.out.println("Error: cannot access content - " + e.toString());
      } catch (URISyntaxException e) {
        System.out.println("Error: Invalid URL " + e.toString());
      }
      return result;
    } else {
      throw new IllegalArgumentException("The type of value is not correct");
    }
  }

  public String makeAPICall(String uri, List<NameValuePair> parameters)
      throws URISyntaxException, IOException {
    String response_content;

    URIBuilder query = new URIBuilder(uri);
    query.addParameters(parameters);

    HttpGet request = new HttpGet(query.build());

    request.setHeader(HttpHeaders.ACCEPT, "application/json");
    request.addHeader("X-CMC_PRO_API_KEY", token);

    response = client.execute(request);

    try {
      HttpEntity entity = response.getEntity();
      response_content = EntityUtils.toString(entity);
      EntityUtils.consumeQuietly(entity);
    } finally {
      response.close();
    }

    return response_content;
  }

  @Override
  public CloseableHttpResponse getResponse() {
    return this.response;
  }
}
