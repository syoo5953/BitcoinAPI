package model;

public class UserInfo {

  String coinToken = null;
  String pasteToken = null;
  String max_amount = null;

  public String getCoinToken() {
    return this.coinToken;
  }

  public void setCoinToken(String token) {
    this.coinToken = token;
  }

  public String getPasteToken() {
    return this.pasteToken;
  }

  public void setPasteToken(String token) {
    this.pasteToken = token;
  }

  public String getMax_amount() {
    return this.max_amount;
  }

  public void setMax_amount(String max_amount) {
    int max_amount_int = Integer.parseInt(max_amount);

    if (max_amount_int >= 1 && max_amount_int <= 1000) {
      this.max_amount = max_amount;
    } else {
      throw new IllegalArgumentException("Amount must be in between 1 and 1000");
    }
  }
}
