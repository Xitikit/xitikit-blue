package test.xitikit.blue.kit.authentication.v2dot0;

import org.junit.Test;
import org.xitikit.blue.kit.authentication.v2dot0.AuthenticationProperties;

import static junit.framework.TestCase.*;

/**
 * Created by Keith on 10/21/2017.
 */
public class AuthenticationPropertiesTest{

    @Test
    public void notBeforeValues() throws Exception{

        AuthenticationProperties p = new AuthenticationProperties();

        assertNotNull(p.getNotBefore());
        assertTrue(p.getNotBefore().isEnabled());//Enabled by default
        p.getNotBefore().setEnabled(false);
        assertFalse(p.getNotBefore().isEnabled());

        assertTrue(p.getNotBefore().getPaddingInMilliseconds() == 0);
        p.getNotBefore().setPaddingInMilliseconds(1000);
        assertTrue(p.getNotBefore().getPaddingInMilliseconds() == 0);

        p.getNotBefore().setEnabled(true);
        assertTrue(p.getNotBefore().getPaddingInMilliseconds() == 1000);
    }
}