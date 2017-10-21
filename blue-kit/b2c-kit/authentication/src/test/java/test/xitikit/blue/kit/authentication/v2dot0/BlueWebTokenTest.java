package test.xitikit.blue.kit.authentication.v2dot0;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.xitikit.blue.kit.authentication.v2dot0.BlueWebToken;

import java.time.ZonedDateTime;

import static java.util.Collections.*;
import static junit.framework.TestCase.*;

/**
 * Created by Keith on 10/21/2017.
 */
public class BlueWebTokenTest{

    @Test
    public void conversionTests() throws Exception{

        ZonedDateTime now = ZonedDateTime.now();
        long nowEpoch = now.toEpochSecond();

        String json = "{\n" +
          "\"exp\": " + nowEpoch + ",\n" +
          "\"nbf\": " + nowEpoch + ",\n" +
          "\"ver\": \"1.0\",\n" +
          "\"iss\": \"https://login.microsoftonline.com/11111111-1111-1111-1111-111111111111/v2.0/\",\n" +
          "\"acr\": \"b2c_1_what-you-named-the-policy\",\n" +
          "\"sub\": \"Not supported currently. Use oid claim.\",\n" +
          "\"aud\": \"11111111-1111-1111-1111-111111111111\",\n" +
          "\"at_hash\": \"11111111-1111-1111-1111-111111111111\",\n" +
          "\"nonce\": \"11111111-1111-1111-1111-111111111111\",\n" +
          "\"c_hash\":\"11111111-1111-1111-1111-111111111111\"\n," +
          "\"iat\": " + nowEpoch + ",\n" +
          "\"idp\": \"blue-kit\",\n" +
          "\"auth_time\": " + nowEpoch + ",\n" +
          "\"oid\": \"11111111-1111-1111-1111-111111111111\",\n" +
          "\"given_name\": \"One Punch\",\n" +
          "\"family_name\": \"Saitama\",\n" +
          "\"some_random_claim_i_made\": \"some_random_value_i_made\",\n" +
          "\"emails\": [\n" +
          "\"caped.baldy@heroassociation.org\"\n" +
          "]\n" +
          "}";

        ObjectMapper mapper = new ObjectMapper();
        BlueWebToken bwt = mapper.readValue(json, BlueWebToken.class);

        makeCommonAssertions(bwt, nowEpoch);

        bwt = new BlueWebToken();
        bwt.setExpiration(now);
        bwt.setNotBefore(now);
        bwt.setVersion("1.0");
        bwt.setIssuer("https://login.microsoftonline.com/11111111-1111-1111-1111-111111111111/v2.0/");
        bwt.setAuthContextReference("b2c_1_what-you-named-the-policy");
        bwt.setSubject("Not supported currently. Use oid claim.");
        bwt.setAudience("11111111-1111-1111-1111-111111111111");
        bwt.setAccessTokenHash("11111111-1111-1111-1111-111111111111");
        bwt.setNonce("11111111-1111-1111-1111-111111111111");
        bwt.setCodeHash("11111111-1111-1111-1111-111111111111");
        bwt.setIssuedAt(now);
        bwt.setIdentityProvider("blue-kit");
        bwt.setAuthTime(now);
        bwt.setObjectId("11111111-1111-1111-1111-111111111111");
        bwt.setGivenName("One Punch");
        bwt.setFamilyName("Saitama");
        bwt.add("some_random_claim_i_made", "some_random_value_i_made");
        bwt.setEmails(singletonList("caped.baldy@heroassociation.org"));

        makeCommonAssertions(bwt, nowEpoch);

        String jsonOther = mapper.writeValueAsString(bwt);
        bwt = mapper.readValue(jsonOther, BlueWebToken.class);
        makeCommonAssertions(bwt, nowEpoch);
    }

    private static void makeCommonAssertions(final BlueWebToken bwt, final long nowEpoch){

        assertNotNull(bwt.getOther());
        assertTrue(bwt.getOther().size() > 0);
        assertNotNull(bwt.getOther().get("some_random_claim_i_made"));
        assertEquals("some_random_value_i_made", bwt.getOther().get("some_random_claim_i_made"));
        assertEquals("11111111-1111-1111-1111-111111111111", bwt.getAccessTokenHash());
        assertEquals("11111111-1111-1111-1111-111111111111", bwt.getAudience());
        assertEquals("b2c_1_what-you-named-the-policy", bwt.getAuthContextReference());
        assertEquals("11111111-1111-1111-1111-111111111111", bwt.getCodeHash());
        assertEquals("One Punch", bwt.getGivenName());
        assertEquals("Saitama", bwt.getFamilyName());
        assertEquals("blue-kit", bwt.getIdentityProvider());
        assertEquals("https://login.microsoftonline.com/11111111-1111-1111-1111-111111111111/v2.0/", bwt.getIssuer());
        assertEquals("11111111-1111-1111-1111-111111111111", bwt.getNonce());
        assertEquals("11111111-1111-1111-1111-111111111111", bwt.getObjectId());
        assertEquals("Not supported currently. Use oid claim.", bwt.getSubject());
        assertEquals("1.0", bwt.getVersion());
        assertNotNull(bwt.getEmails());
        assertTrue(bwt.getEmails().size() == 1);
        assertEquals("caped.baldy@heroassociation.org", bwt.getFirstEmail());
        assertEquals("caped.baldy@heroassociation.org", bwt.getEmails().get(0));
        assertEquals(nowEpoch, bwt.getAuthTime().toEpochSecond());
        assertEquals(nowEpoch, bwt.getExpiration().toEpochSecond());
        assertEquals(nowEpoch, bwt.getExpiration().toEpochSecond());
        assertEquals(nowEpoch, bwt.getIssuedAt().toEpochSecond());
        assertEquals(nowEpoch, bwt.getNotBefore().toEpochSecond());
    }
}