package org.xitikit.examples.openidconnect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author J. Keith Hoopes
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    public static abstract class AbstractWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter{

        @Value("${security.https.enabled}")
        protected boolean httpsEnabled;

        @Value("${security.http.port}")
        protected int httpPort;

        @Value("${security.https.port}")
        protected int httpsPort;

        @Override
        protected void configure(HttpSecurity http) throws Exception{

            http
                .portMapper()
                .http(httpPort)
                .mapsTo(httpsPort);
        }
    }

    @Configuration
    public static class SpringSecurityConfig extends AbstractWebSecurityConfigurerAdapter{

        @Override
        protected void configure(HttpSecurity http) throws Exception{

            http
                .portMapper()
                .http(httpPort)
                .mapsTo(httpsPort)
                .and()
                .csrf()
                .disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/errors/**")
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers("/policy-templates/**")
                .anonymous();
            if(httpsEnabled){
                //This should be enabled in development, but for production environments you will likely be using ssl termination at the off-loader.
                http
                    .requiresChannel()
                    .antMatchers("/**")
                    .requiresSecure();
            }
        }
    }
}
