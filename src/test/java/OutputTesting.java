import model.UserInfo;
import model.facade.output.OfflineOutput;
import model.facade.output.OnlineOutput;
import model.facade.output.OutputAPI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

public class OutputTesting {
  OutputAPI onlineOutputAPI;
  OutputAPI offlineOutputAPI;
  UserInfo userInfo;

  @Before
  public void setUp() {
    onlineOutputAPI = new OnlineOutput();
    offlineOutputAPI = new OfflineOutput();
    userInfo = Mockito.mock(UserInfo.class);
  }

  @Test
  public void testOnlineOutputLoggedInAndPaste() throws IOException, URISyntaxException {
    onlineOutputAPI.loggingIn("txf4kypulzFLeJ6uMb1ZrtGzLMCK8urZ");
    String str = onlineOutputAPI.paste("testString");
    int status = onlineOutputAPI.getResponse().getStatusLine().getStatusCode();

    if (str.equals("Bad API request, Post limit, maximum pastes per 24h reached ")) {
      status = 200;
    }
    Assert.assertEquals(status, 200);
  }

  @Test
  public void testOnlineOutputNotLoggedInAndPaste() throws IOException, URISyntaxException {
    onlineOutputAPI.loggingIn("1234");
    onlineOutputAPI.paste("testString");
    int status = onlineOutputAPI.getResponse().getStatusLine().getStatusCode();
    Assert.assertEquals(status, 422);
  }

  @Test
  public void testOfflineOutputPasteNotLoggedIn() throws IOException, URISyntaxException {
    offlineOutputAPI.loggingIn("1234");
    String str = offlineOutputAPI.paste("testString");
    Assert.assertNull(str);
  }

  @Test
  public void testOfflineOutputPaste() throws IOException, URISyntaxException {
    offlineOutputAPI.loggingIn("53240032-0bea-482c-9273-f346c3f17632");
    String str = offlineOutputAPI.paste("testString");
    Assert.assertEquals(str, "https://pastebin.com/dummyAPI");
  }
}
