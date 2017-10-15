package test.xitikit.blue.noitacitnehtua.api.v2dot0;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.xitikit.blue.noitacitnehtua.api.v2dot0.VerificationUtil.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
class VerificationUtilTest{

  @Test
  void typeSecuredObjectMapperShouldNotAllowMapDeserializtion() throws IOException{

    assertThrows(Exception.class, () ->
      typeSecuredObjectMapper()
        .readValue(
          "{'one':'one'}",
          Map.class));
  }

  @Test
  void validateIssuerTest(){

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