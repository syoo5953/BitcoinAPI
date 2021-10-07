import model.UserInfo;
import model.facade.input.InputAPI;
import model.facade.input.OfflineInput;
import model.facade.input.OnlineInput;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

public class NewExtensionTest {
  InputAPI onlineInputAPI;
  InputAPI offlineInputAPI;
  UserInfo userInfo;

  @Before
  public void setUp() {
    onlineInputAPI = new OnlineInput();
    offlineInputAPI = new OfflineInput();
    userInfo = Mockito.mock(UserInfo.class);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOnlineInvalidInput() {
    Mockito.when(userInfo.getMax_amount()).thenReturn("0");
    String amount = userInfo.getMax_amount();
    onlineInputAPI.viewCryptoCurrency("id", "1", "2", amount);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOnlineInvalidInput1() {
    Mockito.when(userInfo.getMax_amount()).thenReturn("1001");
    String amount = userInfo.getMax_amount();
    onlineInputAPI.viewCryptoCurrency("id", "1", "2", amount);
  }

  @Test
  public void testOnlineValidInput() throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("e0e134c3-d07c-4278-893a-66f057358c00");
    onlineInputAPI.loggingIn(userInfo.getCoinToken());
    Mockito.when(userInfo.getMax_amount()).thenReturn("300");
    String amount = userInfo.getMax_amount();
    onlineInputAPI.viewCryptoCurrency("id", "1", "2", amount);
    int status = onlineInputAPI.getResponse().getStatusLine().getStatusCode();
    Assert.assertEquals(status, 200);
  }

  @Test
  public void testOfflineValidInput() throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("53240032-0bea-482c-9273-f346c3f17632");
    offlineInputAPI.loggingIn(userInfo.getCoinToken());
    Mockito.when(userInfo.getMax_amount()).thenReturn("300");
    String amount = userInfo.getMax_amount();
    String str = offlineInputAPI.viewCryptoCurrency("id", "1", "2", amount);
    JSONObject json = new JSONObject(str);
    Assert.assertEquals(json.get("Extension Output"), "1 -> 2 is converted to 3000");
  }

  @Test
  public void testOfflineInvalidValidInput() throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("53240032-0bea-482c-9273-f346c3f17632");
    offlineInputAPI.loggingIn(userInfo.getCoinToken());
    Mockito.when(userInfo.getMax_amount()).thenReturn("300");
    String amount = userInfo.getMax_amount();
    String str = offlineInputAPI.viewCryptoCurrency("id", "2", "2", amount);
    JSONObject json = new JSONObject(str);
    Assert.assertNotEquals(json.get("Extension Output"), "1 -> 2 is converted to 3000");
  }
}
