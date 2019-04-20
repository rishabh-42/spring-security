package com.springsecurity.springsecurity.config;

import com.springsecurity.springsecurity.successHandler.LoginSuccess;
import com.springsecurity.springsecurity.successHandler.LogoutSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginSuccess loginSuccess;
    @Autowired
    LogoutSuccess logoutSuccess;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception {

        managerBuilder
                .inMemoryAuthentication()
                .withUser("user").password("pass").roles("USER")
                .and()
                .withUser("user1").password("pass").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests()
//                .antMatchers("/home/**").hasAuthority("ROLE_USER")
                .antMatchers("/home/**").hasRole("ADMIN")
                .antMatchers("/login").anonymous()
                .antMatchers("/loginUrl").permitAll()

                .anyRequest().authenticated()

                .and()
                .formLogin()
                .successHandler(loginSuccess)
                .loginPage("/login").loginProcessingUrl("/loginUrl")
                .and()
                .logout().
                permitAll()
                .logoutSuccessHandler(logoutSuccess)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));
    }
}
