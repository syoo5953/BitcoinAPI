import model.UserInfo;
import model.facade.input.InputAPI;
import model.facade.input.OfflineInput;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

public class OfflineInputTesting {

  InputAPI inputAPI;
  UserInfo userInfo;

  @Before
  public void setUp() {
    inputAPI = new OfflineInput();
    userInfo = Mockito.mock(UserInfo.class);
  }

  @Test
  public void testInit() {
    boolean onOrOff;
    inputAPI = new OfflineInput();
    inputAPI.init();
    onOrOff = inputAPI.getIsOnline();
    Assert.assertEquals(onOrOff, false);
  }

  @Test
  public void testLoggingIn() throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("53240032-0bea-482c-9273-f346c3f17632");
    int loggedStatus = inputAPI.loggingIn(userInfo.getCoinToken());
    Assert.assertEquals(loggedStatus, 200);
  }

  @Test
  public void testLoggingInFalse() throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("123456");
    int loggedStatus = inputAPI.loggingIn(userInfo.getCoinToken());
    Assert.assertNotEquals(loggedStatus, 200);
  }

  @Test
  public void testViewCryptoList() throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("53240032-0bea-482c-9273-f346c3f17632");
    inputAPI.loggingIn(userInfo.getCoinToken());
    String str = inputAPI.viewCryptoList();
    JSONObject json = new JSONObject(str);
    JSONArray jsonArray = json.getJSONArray("data");
    Assert.assertEquals(jsonArray.getJSONObject(0).get("symbol").toString(), "BTC");
  }

  @Test
  public void testNotLoggedIn() throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("1234");
    int status = inputAPI.loggingIn(userInfo.getCoinToken());
    String str = inputAPI.viewCryptoList();
    System.out.println(status);
    Assert.assertFalse(str.contains("data"));
  }

  @Test
  public void testViewCryptoInfo() throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("53240032-0bea-482c-9273-f346c3f17632");
    inputAPI.loggingIn(userInfo.getCoinToken());
    String str = inputAPI.viewCryptoInfo("id", "1");
    JSONObject json = new JSONObject(str);
    Assert.assertTrue(json.toString().contains("\"symbol\":\"1\""));
  }

  @Test
  public void testViewCryptoCurrency() throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("53240032-0bea-482c-9273-f346c3f17632");
    inputAPI.loggingIn(userInfo.getCoinToken());
    String str = inputAPI.viewCryptoCurrency("id", "1", "2", "20");
    JSONObject json = new JSONObject(str);
    System.out.println(json);
    Assert.assertTrue(json.toString().contains("\"symbol\":\"1\",\"amount\":\"20\""));
  }
}
