package test.xitikit.blue.b2c.api.v2dot0.policy;

import org.junit.jupiter.api.Test;
import org.xitikit.blue.b2c.kit.v2dot0.policy.PolicyForB2C;
import org.xitikit.blue.b2c.kit.v2dot0.policy.SignInPolicy;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Keith on 10/15/2017.
 */
class SignInPolicyTest{

  private final SignInPolicy b = new SignInPolicy();

  @Test
  void Inheritance(){
    //Make sure it aways implements
    //noinspection ConstantConditions
    assertTrue(b instanceof PolicyForB2C);
  }

  @Test
  void Name(){

    b.setName("test");
    assertEquals("test", b.getName());
  }

  @Test
  void RedirectUrl(){

    b.setRedirectUrl("test");
    assertEquals("test", b.getRedirectUrl());
  }

  @Test
  void TemplateUrl(){
    //Sign In policies are not configurable with templates.
    b.setTemplateUrl("test");
    assertEquals("", b.getTemplateUrl());
  }

  @Test
  void Disabled(){

    b.setDisabled(false);
    assertFalse(b.isDisabled());
  }
}