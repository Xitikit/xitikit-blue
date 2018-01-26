package test.xitikit.blue.b2c.kit.v2dot0.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.xitikit.blue.b2c.kit.v2dot0.authentication.BlueWebToken;
import org.xitikit.blue.b2c.kit.v2dot0.authentication.BlueWebTokenBuilder;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes on 5/4/2017
 */
class BlueWebTokenTest{

  @Test
  void parseTest() throws IOException{

    ObjectMapper mapper = new ObjectMapper();
    Instant now = Instant.ofEpochSecond(System.currentTimeMillis() / 1000);
    ZonedDateTime auth_time = ZonedDateTime.ofInstant(now, ZoneId.systemDefault());
    ZonedDateTime iat = ZonedDateTime.from(auth_time);
    ZonedDateTime exp = ZonedDateTime
                          .ofInstant(now, ZoneId.systemDefault())
                          .plusMinutes(30);
    ZonedDateTime nbf = exp.minusMinutes(1);
    String jsonToken = "{\n" + "    \"exp\": " + exp.toEpochSecond() + ",\n" + "    \"nbf\": " + nbf.toEpochSecond() + ",\n" + "    \"ver\": \"1.0\",\n" + "    \"iss\": \"https://login.microsoftonline.com/11111111-1111-1111-1111-111111111111/v2.0/\",\n" + "    \"acr\": \"b2c_1_what-you-named-the-policy\",\n" + "    \"sub\": \"Not supported currently. Use oid claim.\",\n" + "    \"aud\": \"11111111-1111-1111-1111-111111111111\",\n" + "    \"nonce\": \"11111111-1111-1111-1111-111111111111\",\n" + "    \"iat\": " + iat.toEpochSecond() + ",\n" + "    \"auth_time\": " + auth_time.toEpochSecond() + ",\n" + "    \"oid\": \"11111111-1111-1111-1111-111111111111\",\n" + "    \"given_name\": \"One Punch\",\n" + "    \"family_name\": \"Saitama\",\n" + "    \"emails\": [\n" + "       \"caped.baldy@heroassociation.org\"\n" + "    ]\n" + "}";

    BlueWebToken token = mapper.readValue(jsonToken, BlueWebToken.class);
    assertEquals(token.getExpiration(), exp);
    assertEquals(token.getNotBefore(), nbf);
    assertEquals(token.getVersion(), "1.0");
    assertEquals(token.getIssuer(), "https://login.microsoftonline.com/11111111-1111-1111-1111-111111111111/v2.0/");
    assertEquals(token.getAuthContextReference(), "b2c_1_what-you-named-the-policy");
    assertEquals(token.getSubject(), "Not supported currently. Use oid claim.");
    assertEquals(token.getAudience(), "11111111-1111-1111-1111-111111111111");
    assertEquals(token.getNonce(), "11111111-1111-1111-1111-111111111111");
    assertEquals(token.getIssuedAt(), iat);
    assertEquals(token.getAuthTime(), auth_time);
    assertEquals(token.getObjectId(), "11111111-1111-1111-1111-111111111111");
    assertEquals(token.getFirstName(), "One Punch");
    assertEquals(token.getLastName(), "Saitama");
    assertEquals(token.getFirstEmail(), "caped.baldy@heroassociation.org");
  }

  @Test
  void builder(){

    ZonedDateTime now = ZonedDateTime.now();
    BlueWebTokenBuilder builder = BlueWebTokenBuilder.instance();
    BlueWebToken a = builder
                       .withAccessTokenHash("test")
                       .withAdditionalProperties(new HashMap<>(0))
                       .withAudience("test")
                       .withAuthContextReference("B2C_1_test")
                       .withAuthTime(now)
                       .withCodeHash("test")
                       .withEmails(asList("one@test.test", "one@test.test"))
                       .withExpiration(now.plusMinutes(1))
                       .withIdentityProvider("test")
                       .withIssuedAt(now)
                       .withIssuer("test")
                       .withNewUser(true)
                       .withNonce("test")
                       .withNotBefore(now.minusMinutes(1))
                       .withObjectId("test")
                       .withSubject("test")
                       .withTrustedFrameworkPolicy("test")
                       .withVersion("2.0")
                       .build();

    BlueWebToken b = builder.build();

    assertFalse(a == b);//reference should NOT be the same
    assertEquals(b.getAccessTokenHash(), "test");
    assertEquals(b.getAudience(), "test");
    assertEquals(b.getAuthContextReference(), "B2C_1_test");
    assertEquals(b.getAuthTime(), now);
    assertEquals(b.getCodeHash(), "test");
    assertNotNull(b.getEmails());
    assertTrue(b.getEmails().containsAll(asList("one@test.test", "one@test.test")));
    assertEquals(b.getExpiration(), now.plusMinutes(1));
    assertEquals(b.getIdentityProvider(), "test");
    assertEquals(b.getIssuedAt(), now);
    assertEquals(b.getIssuer(), "test");
    assertEquals(b.isNewUser(), true);
    assertEquals(b.getNonce(), "test");
    assertEquals(b.getNotBefore(), now.minusMinutes(1));
    assertEquals(b.getObjectId(), "test");
    assertEquals(b.getSubject(), "test");
    assertEquals(b.getTrustedFrameworkPolicy(), "test");
    assertEquals(b.getVersion(), "2.0");

    BlueWebToken c = builder.clear()
                            .with(
                              "test",
                              "test",
                              now,
                              "B2C_1_test",
                              "test",
                              asList("one@test.test", "one@test.test"),
                              now.plusMinutes(1),
                              "test",
                              now,
                              "test",
                              true,
                              "test",
                              now.minusMinutes(1),
                              "test",
                              "test",
                              "test",
                              "2.0",
                              new HashMap<>())
                            .withProperty("test", "test")
                            .withProperty("ing", "ing")
                            .build();

    assertFalse(a == c);//reference should NOT be the same
    assertEquals(c.getAccessTokenHash(), "test");
    assertEquals(c.getAudience(), "test");
    assertEquals(c.getAuthContextReference(), "B2C_1_test");
    assertEquals(c.getAuthTime(), now);
    assertEquals(c.getCodeHash(), "test");
    assertNotNull(c.getEmails());
    assertTrue(c.getEmails().containsAll(asList("one@test.test", "one@test.test")));
    assertEquals(c.getExpiration(), now.plusMinutes(1));
    assertEquals(c.getIdentityProvider(), "test");
    assertEquals(c.getIssuedAt(), now);
    assertEquals(c.getIssuer(), "test");
    assertEquals(c.isNewUser(), true);
    assertEquals(c.getNonce(), "test");
    assertEquals(c.getNotBefore(), now.minusMinutes(1));
    assertEquals(c.getObjectId(), "test");
    assertEquals(c.getSubject(), "test");
    assertEquals(c.getTrustedFrameworkPolicy(), "test");
    assertEquals(c.getVersion(), "2.0");
    assertEquals(c.get("test"), "test");
    assertEquals(c.get("ing"), "ing");
    assertNull(c.get("given_name"));
  }
}