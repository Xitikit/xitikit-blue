package org.xitikit.blue.api.b2c.v2dot0.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@Component
@ConfigurationProperties
public class AuthenticationProperties{

    private NotBefore notBefore;

    public AuthenticationProperties(){

    }

    public AuthenticationProperties(final NotBefore notBefore){

        this.notBefore = notBefore;
    }

    public NotBefore getNotBefore(){

        return notBefore;
    }

    public void setNotBefore(final NotBefore notBefore){

        this.notBefore = notBefore;
    }

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
