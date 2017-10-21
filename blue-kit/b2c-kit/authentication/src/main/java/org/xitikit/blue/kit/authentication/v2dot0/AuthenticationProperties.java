package org.xitikit.blue.kit.authentication.v2dot0;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class AuthenticationProperties{

    private NotBefore notBefore;

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

        public long getPaddingInMilliseconds(){

            return enabled ? paddingInMilliseconds : 0;
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

    public NotBefore getNotBefore(){

        return notBefore;
    }

    public void setNotBefore(final NotBefore notBefore){

        this.notBefore = notBefore;
    }
}
