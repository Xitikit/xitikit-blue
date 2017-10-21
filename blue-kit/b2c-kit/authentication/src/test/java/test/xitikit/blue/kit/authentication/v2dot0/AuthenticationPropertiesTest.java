package test.xitikit.blue.kit.authentication.v2dot0;

import org.junit.Test;
import org.xitikit.blue.kit.authentication.v2dot0.AuthenticationProperties;
import org.xitikit.blue.kit.authentication.v2dot0.AuthenticationProperties.NotBefore;

import static junit.framework.TestCase.*;

/**
 * Created by Keith on 10/21/2017.
 */
public class AuthenticationPropertiesTest{

    @Test
    public void notBeforeValues() throws Exception{

        AuthenticationProperties p = new AuthenticationProperties();
        assertNull(p.getNotBefore());

        NotBefore notBefore = new NotBefore();
        p.setNotBefore(notBefore);
        assertNotNull(p.getNotBefore());
        assertEquals(notBefore, p.getNotBefore());

        assertTrue(notBefore.isEnabled());//Enabled by default
        notBefore.setEnabled(false);
        assertFalse(notBefore.isEnabled());

        assertTrue(notBefore.getPaddingInMilliseconds() == 0);
        notBefore.setPaddingInMilliseconds(1000);
        assertTrue(notBefore.getPaddingInMilliseconds() == 0);

        notBefore.setEnabled(true);
        assertTrue(notBefore.getPaddingInMilliseconds() == 1000);
    }
}