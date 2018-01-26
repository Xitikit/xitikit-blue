package org.xitikit.blue.admin.form;

import java.io.Serializable;

/**
 * Make sure that accessors do not modify data when setting or getting values.
 */
public class SearchForm implements Serializable{

  private String givenName;

  private String surname;

  public String getGivenName(){

    return givenName;
  }

  public void setGivenName(final String givenName){

    this.givenName = givenName;
  }

  public String getSurname(){

    return surname;
  }

  public void setSurname(final String surname){

    this.surname = surname;
  }
}
