package test.xitikit.blue.noitacitnehtua.api.v2dot0;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.xitikit.blue.noitacitnehtua.api.v2dot0.BlueWebToken;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.*;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes on 5/4/2017
 */
public class BlueWebTokenTest{

    @Test
    public void parseTest() throws IOException{

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
}