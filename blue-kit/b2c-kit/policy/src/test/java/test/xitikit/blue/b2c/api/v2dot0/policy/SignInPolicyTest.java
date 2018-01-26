package test.xitikit.blue.b2c.api.v2dot0.policy;

import org.junit.Test;
import org.xitikit.blue.b2c.kit.v2dot0.policy.PolicyForB2C;
import org.xitikit.blue.b2c.kit.v2dot0.policy.SignInPolicy;

import static junit.framework.TestCase.*;

/**
 * Created by Keith on 10/15/2017.
 */
public class SignInPolicyTest{

  private final SignInPolicy b = new SignInPolicy();

  @Test
  public void Inheritance(){
    //Make sure it aways implements
    //noinspection ConstantConditions
    assertTrue(b instanceof PolicyForB2C);
  }

  @Test
  public void Name(){

    b.setName("test");
    assertEquals("test", b.getName());
  }

  @Test
  public void RedirectUrl(){

    b.setRedirectUrl("test");
    assertEquals("test", b.getRedirectUrl());
  }

  @Test
  public void TemplateUrl(){
    //Sign In policies are not configurable with templates.
    b.setTemplateUrl("test");
    assertEquals("", b.getTemplateUrl());
  }

  @Test
  public void Disabled(){

    b.setDisabled(false);
    assertFalse(b.isDisabled());
  }
}