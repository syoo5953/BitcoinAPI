package model.facade.input;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class OfflineInput implements InputAPI {

  boolean isOnline = false;
  private boolean logged_in = false;

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
    this.isOnline = false;
  }

  @Override
  public boolean getIsOnline() {
    return this.isOnline;
  }

  @Override
  public int loggingIn(String token) {
    if (token.equals("53240032-0bea-482c-9273-f346c3f17632")) {
      this.logged_in = true;
      return 200;
    } else {
      return 400;
    }
  }

  @Override
  public String viewCryptoList() {
    if (this.logged_in == true) {
      try (Reader fileReader =
          new InputStreamReader(getClass().getResourceAsStream("/config.json"))) {
        JSONObject configObject = (JSONObject) new JSONParser().parse(fileReader);
        return configObject.toJSONString();
      } catch (NullPointerException | IOException | ParseException e) {
        System.out.println("Error: Configuration file missing or malformed.");
        System.out.println("Exiting Program.");
        throw new IllegalArgumentException("Missing or malformed configuration file");
      }
    } else {
      return "Not logged in!";
    }
  }

  @Override
  public String viewCryptoInfo(String name, String value) {
    if (this.logged_in == true) {
      String str =
          "{\n"
              + "  \"data\": {\"1\": {\n"
              + "    \"symbol\": \""
              + value
              + "\",\n"
              + "    \"tag-groups\": [\n"
              + "      \"OTHER\",\n"
              + "      \"CONSENSUS_ALGORITHM\",\n"
              + "      \"CONSENSUS_ALGORITHM\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\",\n"
              + "      \"PROPERTY\"\n"
              + "    ],\n"
              + "    \"twitter_username\": \"\",\n"
              + "    \"is_hidden\": 0,\n"
              + "    \"description\": \""
              + value
              + " (DC) is a cryptocurrency . Users are able to generate "
              + value
              + " through the process of mining. "
              + value
              + " has a current supply of 18,729,837. The last known price of Bitcoin is 33,034.01006883 USD and is down -9.34 over the last 24 hours. It is currently trading on 9722 active market(s) with $43,701,868,530.46 traded over the last 24 hours. More information can be found at https://dummyCoin.org/.\",\n"
              + "    \"tag-names\": [\n"
              + "      \"Mineable\",\n"
              + "      \"PoW\",\n"
              + "      \"SHA-256\",\n"
              + "      \"Store of Value\",\n"
              + "      \"State channels\",\n"
              + "      \"Coinbase Ventures Portfolio\",\n"
              + "      \"Three Arrows Capital Portfolio\",\n"
              + "      \"Polychain Capital Portfolio\",\n"
              + "      \"Binance Labs Portfolio\",\n"
              + "      \"Arrington XRP capital\",\n"
              + "      \"Blockchain Capital Portfolio\",\n"
              + "      \"BoostVC Portfolio\",\n"
              + "      \"CMS Holdings Portfolio\",\n"
              + "      \"DCG Portfolio\",\n"
              + "      \"DragonFly Capital Portfolio\",\n"
              + "      \"Electric Capital Portfolio\",\n"
              + "      \"Fabric Ventures Portfolio\",\n"
              + "      \"Framework Ventures\",\n"
              + "      \"Galaxy Digital Portfolio\",\n"
              + "      \"Huobi Capital\",\n"
              + "      \"Alameda Research Portfolio\",\n"
              + "      \"A16Z Portfolio\",\n"
              + "      \"1Confirmation Portfolio\",\n"
              + "      \"Winklevoss Capital\",\n"
              + "      \"USV Portfolio\",\n"
              + "      \"Placeholder Ventures Portfolio\",\n"
              + "      \"Pantera Capital Portfolio\",\n"
              + "      \"Multicoin Capital Portfolio\",\n"
              + "      \"Paradigm XZY Screener\"\n"
              + "    ],\n"
              + "    \"subreddit\": \"bitcoin\",\n"
              + "    \"platform\": null,\n"
              + "    \"tags\": [\n"
              + "      \"mineable\",\n"
              + "      \"pow\",\n"
              + "      \"sha-256\",\n"
              + "      \"store-of-value\",\n"
              + "      \"state-channels\",\n"
              + "      \"coinbase-ventures-portfolio\",\n"
              + "      \"three-arrows-capital-portfolio\",\n"
              + "      \"polychain-capital-portfolio\",\n"
              + "      \"binance-labs-portfolio\",\n"
              + "      \"arrington-xrp-capital\",\n"
              + "      \"blockchain-capital-portfolio\",\n"
              + "      \"boostvc-portfolio\",\n"
              + "      \"cms-holdings-portfolio\",\n"
              + "      \"dcg-portfolio\",\n"
              + "      \"dragonfly-capital-portfolio\",\n"
              + "      \"electric-capital-portfolio\",\n"
              + "      \"fabric-ventures-portfolio\",\n"
              + "      \"framework-ventures\",\n"
              + "      \"galaxy-digital-portfolio\",\n"
              + "      \"huobi-capital\",\n"
              + "      \"alameda-research-portfolio\",\n"
              + "      \"a16z-portfolio\",\n"
              + "      \"1confirmation-portfolio\",\n"
              + "      \"winklevoss-capital\",\n"
              + "      \"usv-portfolio\",\n"
              + "      \"placeholder-ventures-portfolio\",\n"
              + "      \"pantera-capital-portfolio\",\n"
              + "      \"multicoin-capital-portfolio\",\n"
              + "      \"paradigm-xzy-screener\"\n"
              + "    ],\n"
              + "    \"date_added\": \"2013-04-28T00:00:00.000Z\",\n"
              + "    \"urls\": {\n"
              + "      \"website\": [\"https://bitcoin.org/\"],\n"
              + "      \"twitter\": [],\n"
              + "      \"message_board\": [\"https://bitcointalk.org\"],\n"
              + "      \"chat\": [],\n"
              + "      \"explorer\": [\n"
              + "        \"https://blockchain.coinmarketcap.com/chain/dummyCoin\",\n"
              + "        \"https://blockchain.info/\",\n"
              + "        \"https://live.blockcypher.com/btc/\",\n"
              + "        \"https://blockchair.com/bitcoin\",\n"
              + "        \"https://explorer.viabtc.com/btc\"\n"
              + "      ],\n"
              + "      \"reddit\": [\"https://reddit.com/r/dummyCoin\"],\n"
              + "      \"technical_doc\": [\"https://bitcoin.org/dummyCoin.pdf\"],\n"
              + "      \"source_code\": [\"https://github.com/dummyCoin/\"],\n"
              + "      \"announcement\": []\n"
              + "    },\n"
              + "    \"name\": \"DummyCoin\",\n"
              + "    \"logo\": \"https://s2.coinmarketcap.com/static/img/coins/64x64/1.png\",\n"
              + "    \"id\": 1,\n"
              + "    \"category\": \"dummy\",\n"
              + "    \"slug\": \"DummyCoin\",\n"
              + "    \"notice\": \"\"\n"
              + "  }},\n"
              + "  \"status\": {\n"
              + "    \"error_message\": null,\n"
              + "    \"elapsed\": 13,\n"
              + "    \"credit_count\": 1,\n"
              + "    \"error_code\": 0,\n"
              + "    \"timestamp\": \"2021-06-08T11:46:51.742Z\",\n"
              + "    \"notice\": \"You have used 165% of your plan's daily credit limit.\"\n"
              + "  }\n"
              + "}";
      return str;
    } else {
      return "Not logged in!";
    }
  }

  @Override
  public String viewCryptoCurrency(
      String name, String baseCurrency, String quoteCurrency, String amount) {
    if (this.logged_in == true) {
      if (isNumeric(amount)) {
        String str =
            "{\n"
                + "  \"data\": {\n"
                + "    \"symbol\": \""
                + baseCurrency
                + "\",\n"
                + "    \"amount\": \""
                + amount
                + "\",\n"
                + "    \"last_updated\": \"2021-06-08T12:20:02.000Z\",\n"
                + "    \"quote\": {\""
                + quoteCurrency
                + "\": {\n"
                + "      \"last_updated\": \"2021-06-08T12:20:02.000Z\",\n"
                + "      \"price\": "
                + 10 * Integer.parseInt(amount)
                + "\n"
                + "    }},\n"
                + "    \"name\": \""
                + baseCurrency
                + "\",\n"
                + "    \"id\": 1\n"
                + "  },\n"
                + "  \"Extension Output\": "
                + baseCurrency
                + " -> "
                + quoteCurrency
                + " is converted to "
                + (10 * Integer.parseInt(amount))
                + ",\n"
                + "  \"status\": {\n"
                + "    \"error_message\": null,\n"
                + "    \"elapsed\": 17,\n"
                + "    \"credit_count\": 1,\n"
                + "    \"error_code\": 0,\n"
                + "    \"timestamp\": \"2021-06-08T12:20:39.770Z\",\n"
                + "    \"notice\": \"You have used 166% of your plan's daily credit limit.\"\n"
                + "  }\n"
                + "}";
        return str;
      } else {
        return null;
      }
    } else {
      return "Not logged in!";
    }
  }

  @Override
  public CloseableHttpResponse getResponse() {
    return null;
  }
}
