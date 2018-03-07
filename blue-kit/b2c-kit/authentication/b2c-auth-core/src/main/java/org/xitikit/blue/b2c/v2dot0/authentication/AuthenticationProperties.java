package org.xitikit.blue.b2c.v2dot0.authentication;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class AuthenticationProperties{

    private String authSuccessRedirect;

    private String authFailureRedirect;

    private String signupSuccessRedirect;

    private String resetPasswordSuccessRedirect;

    private NotBefore notBefore;

    public String getAuthSuccessRedirect(){

        return authSuccessRedirect;
    }

    public void setAuthSuccessRedirect(final String authSuccessRedirect){

        this.authSuccessRedirect = authSuccessRedirect;
    }

    public String getAuthFailureRedirect(){

        return authFailureRedirect;
    }

    public void setAuthFailureRedirect(final String authFailureRedirect){

        this.authFailureRedirect = authFailureRedirect;
    }

    public String getSignupSuccessRedirect(){

        return signupSuccessRedirect;
    }

    public void setSignupSuccessRedirect(final String signupSuccessRedirect){

        this.signupSuccessRedirect = signupSuccessRedirect;
    }

    public String getResetPasswordSuccessRedirect(){

        return resetPasswordSuccessRedirect;
    }

    public void setResetPasswordSuccessRedirect(final String resetPasswordSuccessRedirect){

        this.resetPasswordSuccessRedirect = resetPasswordSuccessRedirect;
    }

    public NotBefore getNotBefore(){

        return notBefore;
    }

    public void setNotBefore(final NotBefore notBefore){

        this.notBefore = notBefore;
    }

    //Maybe have a similar property class for each claim to be verified?
    public class NotBefore{

        /**
         * The "not before" token sometimes comes back from microsoft in the future. In milliseconds, this lets us pad the "now"
         * time, in effect saying "as long as it's not TOO far in the future, we're okay with it."
         */
        private long paddingInMilliseconds = 0;

        /**
         * Indicates that the 'not before' claim should be validated.     *
         * Default: true.
         */
        private boolean enabled = true;

        public NotBefore(){

        }

        public NotBefore(final long paddingInMilliseconds, final boolean enabled){

            this.paddingInMilliseconds = paddingInMilliseconds;
            this.enabled = enabled;
        }

        public long getPaddingInMilliseconds(){

            return paddingInMilliseconds;
        }

        public void setPaddingInMilliseconds(final long paddingInMilliseconds){

            this.paddingInMilliseconds = paddingInMilliseconds;
        }

        public boolean isEnabled(){

            return enabled;
        }

        public void setEnabled(final boolean enabled){

            this.enabled = enabled;
        }
    }
}
