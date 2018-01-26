package org.xitikit.blue.admin.view;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Make sure that accessors do not modify data when setting or getting values.
 */
public class AccountViewTest{

  @Test
  public void givenName(){

    AccountView view = new AccountView();
    assertNull(view.getGivenName());
    view.setGivenName(" Name ");
    assertEquals(" Name ", view.getGivenName());
    view.setGivenName(" ");
    assertEquals(" ", view.getGivenName());
    view.setGivenName("");
    assertEquals("", view.getGivenName());
    view.setGivenName(null);
    assertEquals(null, view.getGivenName());
  }

  @Test
  public void surName(){

    AccountView view = new AccountView();
    assertNull(view.getSurname());
    view.setSurname(" Name ");
    assertEquals(" Name ", view.getSurname());
    view.setSurname(" ");
    assertEquals(" ", view.getSurname());
    view.setSurname("");
    assertEquals("", view.getSurname());
    view.setSurname(null);
    assertEquals(null, view.getSurname());
  }

  @Test
  public void webAccountId(){

    String uuid = UUID.randomUUID().toString();
    AccountView view = new AccountView();
    assertNull(view.getAccountId());
    view.setAccountId(uuid);
    assertEquals(uuid, view.getAccountId());
    view.setAccountId(" ");
    assertEquals(" ", view.getAccountId());
    view.setAccountId("");
    assertEquals("", view.getAccountId());
    view.setAccountId(null);
    assertEquals(null, view.getAccountId());
  }
}