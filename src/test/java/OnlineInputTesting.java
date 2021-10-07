import model.UserInfo;
import model.facade.input.InputAPI;
import model.facade.input.OnlineInput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

public class OnlineInputTesting {
  InputAPI inputAPI;
  UserInfo userInfo;

  @Before
  public void setUp() {
    inputAPI = new OnlineInput();
    userInfo = Mockito.mock(UserInfo.class);
  }

  @Test
  public void testInit() {
    boolean onOrOff;
    inputAPI = new OnlineInput();
    inputAPI.init();
    onOrOff = inputAPI.getIsOnline();
    Assert.assertEquals(onOrOff, true);
  }

  @Test
  public void testLoggingIn() throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("e0e134c3-d07c-4278-893a-66f057358c00");
    inputAPI.loggingIn(userInfo.getCoinToken());
    int status = inputAPI.getResponse().getStatusLine().getStatusCode();
    Assert.assertEquals(status, 200);
  }

  @Test
  public void testLoggingInFalse() throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("123456");
    inputAPI.loggingIn(userInfo.getCoinToken());
    int status = inputAPI.getResponse().getStatusLine().getStatusCode();
    Assert.assertNotEquals(status, 200);
  }

  @Test
  public void testViewCryptoList() throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("e0e134c3-d07c-4278-893a-66f057358c00");
    inputAPI.loggingIn(userInfo.getCoinToken());
    inputAPI.viewCryptoList();
    int status = inputAPI.getResponse().getStatusLine().getStatusCode();
    Assert.assertEquals(status, 200);
  }

  @Test
  public void testNotLoggedIn() throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("1234");
    inputAPI.loggingIn(userInfo.getCoinToken());
    inputAPI.viewCryptoList();
    int status = inputAPI.getResponse().getStatusLine().getStatusCode();
    Assert.assertNotEquals(status, 200);
  }

  @Test
  public void testNotLoggedIn1() throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("e0e134c3-d07c-4278-893a-66f057358c00");
    inputAPI.viewCryptoList();
    int status = inputAPI.getResponse().getStatusLine().getStatusCode();
    Assert.assertNotEquals(status, 200);
  }

  @Test
  public void testViewCryptoInfo() throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("e0e134c3-d07c-4278-893a-66f057358c00");
    inputAPI.loggingIn(userInfo.getCoinToken());
    inputAPI.viewCryptoInfo("id", "1");
    int status = inputAPI.getResponse().getStatusLine().getStatusCode();
    Assert.assertEquals(status, 200);
  }

  @Test
  public void testViewCryptoInfo1() throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("e0e134c3-d07c-4278-893a-66f057358c00");
    inputAPI.loggingIn(userInfo.getCoinToken());
    inputAPI.viewCryptoInfo("id", "a");
    int status = inputAPI.getResponse().getStatusLine().getStatusCode();
    Assert.assertNotEquals(status, 200);
  }

  @Test
  public void testViewCryptoCurrency()throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("e0e134c3-d07c-4278-893a-66f057358c00");
    inputAPI.loggingIn(userInfo.getCoinToken());
    inputAPI.viewCryptoCurrency("id", "1","2", "20");
    int status = inputAPI.getResponse().getStatusLine().getStatusCode();
    Assert.assertEquals(status, 200);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testViewCryptoCurrency1()throws IOException, URISyntaxException {
    Mockito.when(userInfo.getCoinToken()).thenReturn("e0e134c3-d07c-4278-893a-66f057358c00");
    inputAPI.loggingIn(userInfo.getCoinToken());
    inputAPI.viewCryptoCurrency("slug", "1", "2", "20");
    int status = inputAPI.getResponse().getStatusLine().getStatusCode();
    Assert.assertNotEquals(status, 200);
  }
}
