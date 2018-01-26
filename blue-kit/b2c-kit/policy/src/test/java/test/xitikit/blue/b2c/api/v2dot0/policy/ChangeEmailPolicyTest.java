package test.xitikit.blue.b2c.api.v2dot0.policy;

import org.junit.Test;
import org.xitikit.blue.b2c.kit.v2dot0.policy.ChangeEmailPolicy;
import org.xitikit.blue.b2c.kit.v2dot0.policy.PolicyForB2C;

import static junit.framework.TestCase.*;

/**
 * Created by Keith on 10/15/2017.
 */
public class ChangeEmailPolicyTest{

  private final ChangeEmailPolicy a = new ChangeEmailPolicy();

  private final PolicyForB2C b = a;

  @Test
  public void Name(){

    b.setName("test");
    assertEquals("", b.getName());
  }

  @Test
  public void RedirectUrl(){

    b.setRedirectUrl("test");
    assertEquals("", b.getRedirectUrl());
  }

  @Test
  public void TemplateUrl(){

    b.setTemplateUrl("test");
    assertEquals("", b.getTemplateUrl());
  }

  @Test
  public void Disabled(){

    b.setDisabled(false);
    assertTrue(b.isDisabled());
  }
}