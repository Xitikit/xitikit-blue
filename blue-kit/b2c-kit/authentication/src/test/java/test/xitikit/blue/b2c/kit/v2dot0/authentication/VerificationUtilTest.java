package test.xitikit.blue.b2c.kit.v2dot0.authentication;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.xitikit.blue.b2c.kit.v2dot0.authentication.VerificationUtil.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
class VerificationUtilTest{

  @Test
  void typeSecuredObjectMapperShouldNotAllowMapDeserialization(){

    assertThrows(Exception.class,
      () -> typeSecuredObjectMapper()
              .readValue(
                "{'one':'one'}",
                Map.class));
  }

  @Test
  void validateIssuerTest(){

    // validateIssuer();
  }

  @Test
  void validateExpirationTest(){

  }

  @Test
  void getKeyIdTest(){

  }

  @Test
  void getKeyId1Test(){

  }

  @Test
  void modulusTest(){

  }

  @Test
  void exponentTest(){

  }

  @Test
  void encodedModulusTest(){

  }

  @Test
  void encodedExponentTest(){

  }

  @Test
  void safeDecodeBase64Test(){

  }

  @Test
  void kidForKeyIdTest(){

  }

  @Test
  void safeNodeTest(){

  }

  @Test
  void jwksUriTest(){

  }

  @Test
  void parseKeyTest(){

  }

  @Test
  void rsaPublicKeySpecTest(){

  }
}