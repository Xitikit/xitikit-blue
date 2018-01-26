package test.xitikit.blue.b2c.kit.v2dot0.authentication;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.xitikit.blue.b2c.kit.v2dot0.authentication.VerificationUtil.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public class VerificationUtilTest{

  @Test(expected = Exception.class)
  public void typeSecuredObjectMapperShouldNotAllowMapDeserialization() throws IOException{

    typeSecuredObjectMapper()
      .readValue(
        "{'one':'one'}",
        Map.class);
  }

  @Test
  public void validateIssuerTest(){

    // validateIssuer();
  }

  @Test
  public void validateExpirationTest(){

  }

  @Test
  public void getKeyIdTest(){

  }

  @Test
  public void getKeyId1Test(){

  }

  @Test
  public void modulusTest(){

  }

  @Test
  public void exponentTest(){

  }

  @Test
  public void encodedModulusTest(){

  }

  @Test
  public void encodedExponentTest(){

  }

  @Test
  public void safeDecodeBase64Test(){

  }

  @Test
  public void kidForKeyIdTest(){

  }

  @Test
  public void safeNodeTest(){

  }

  @Test
  public void jwksUriTest(){

  }

  @Test
  public void parseKeyTest(){

  }

  @Test
  public void rsaPublicKeySpecTest(){

  }
}