package itcase.xitikit.blue.b2c.api.v2dot0.policy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.xitikit.blue.b2c.api.v2dot0.policy.itcase.ITCaseApplication;
import org.xitikit.blue.b2c.kit.v2dot0.policy.SignInPolicy;
import org.xitikit.blue.boot.b2c.SimplePolicyConfiguration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Keith on 10/15/2017.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ITCaseApplication.class, webEnvironment = WebEnvironment.MOCK)
class PolicyITCase{

  @Autowired
  private SimplePolicyConfiguration simplePolicyConfiguration;

  @Test
  void signInConfiguration(){

    SignInPolicy signInPolicy;
    assertNotNull(simplePolicyConfiguration);

    signInPolicy = simplePolicyConfiguration.getSignIn();
    assertNotNull(signInPolicy);

    assertEquals("one", signInPolicy.getName());
    assertEquals("http://test/", signInPolicy.getRedirectUrl());
    assertEquals("", signInPolicy.getTemplateUrl());
    assertFalse(signInPolicy.isDisabled());

    signInPolicy = simplePolicyConfiguration.getSignIn();
    assertNotNull(signInPolicy, "signInPolicy should not be null, even when disabled.");
    assertEquals("", signInPolicy.getName());
    assertEquals("", signInPolicy.getRedirectUrl());
    assertEquals("", signInPolicy.getTemplateUrl());
    assertTrue(signInPolicy.isDisabled());
  }

}