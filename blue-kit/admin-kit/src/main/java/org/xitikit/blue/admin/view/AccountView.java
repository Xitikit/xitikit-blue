package org.xitikit.blue.admin.view;

import java.io.Serializable;

public class AccountView implements Serializable{

  private String givenName;

  private String accountId;

  private String surname;

  public String getGivenName(){

    return givenName;
  }

  public void setGivenName(final String givenName){

    this.givenName = givenName;
  }

  public String getAccountId(){

    return accountId;
  }

  public void setAccountId(final String accountId){

    this.accountId = accountId;
  }

  public String getSurname(){

    return surname;
  }

  public void setSurname(final String surname){

    this.surname = surname;
  }
}
