package org.xitikit.blue.kit.authentication.v2dot0;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class AuthenticationProperties{

    private final NotBefore notBefore = new NotBefore();

    public class NotBefore{

        /**
         * The "not before" token sometimes comes back from microsoft in the future. In milliseconds, this lets us pad the "now"
         * time, in effect saying "as long as it's not TOO far in the future, we're okay with it."
         *
         * This will return {@code 0L) if not-before.enabled is {@code true}.
         *
         * Default: {@code 0L}
         */
        private long paddingInMilliseconds = 0;

        /**
         * Indicates that the 'not before' claim should be validated.     *
         *
         * Default: {@code true)
         */
        private boolean enabled = true;

        private NotBefore(){

        }

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
}
