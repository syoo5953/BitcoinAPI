package model.facade.output;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OnlineOutput implements OutputAPI {

  private boolean isOnline = false;
  private CloseableHttpResponse response;
  private CloseableHttpClient client = HttpClients.createDefault();
  private String token = null;

  @Override
  public int loggingIn(String token) {

    if (token.length() != 32) {
      return 400;
    } else {
      this.token = token;
      return 200;
    }
  }

  @Override
  public void init() {
    isOnline = true;
  }

  @Override
  public String paste(String data) throws IOException {
    client = HttpClients.createDefault();
    String response_content = null;
    HttpPost post = new HttpPost("https://pastebin.com/api/api_post.php");
    List<NameValuePair> nameValuePairs = new ArrayList<>(3);
    nameValuePairs.add(new BasicNameValuePair("api_dev_key", token));
    nameValuePairs.add(new BasicNameValuePair("api_option", "paste"));
    nameValuePairs.add(new BasicNameValuePair("api_paste_code", data));
    post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
    response = client.execute(post);
    try {
      HttpEntity entity = response.getEntity();
      response_content = EntityUtils.toString(entity);
      EntityUtils.consumeQuietly(entity);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      response.close();
    }
    return response_content;
  }

  @Override
  public boolean getIsOnline() {
    return this.isOnline;
  }

  @Override
  public CloseableHttpResponse getResponse() {
    return this.response;
  }
}
