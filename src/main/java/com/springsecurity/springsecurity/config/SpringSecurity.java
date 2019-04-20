package com.springsecurity.springsecurity.config;

//import com.springsecurity.springsecurity.service.CustomUserDetailService;
import com.springsecurity.springsecurity.service.CustomUserDetailService;
import com.springsecurity.springsecurity.successHandler.LoginSuccess;
import com.springsecurity.springsecurity.successHandler.LogoutSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginSuccess loginSuccess;
    @Autowired
    LogoutSuccess logoutSuccess;

    @Autowired
    CustomUserDetailService customUserDetailService;


    @Autowired
    DataSource dataSource;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception {

        managerBuilder.userDetailsService(customUserDetailService);
//                .inMemoryAuthentication()
//                .withUser("user").password("pass").roles("USER")
//                .and()
//                .withUser("user1").password("pass").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests()
//                .antMatchers("/home/**").hasAuthority("ROLE_USER")
                .antMatchers("/home/**").hasRole("ADMIN")
                .antMatchers("/login").anonymous()
                .antMatchers("/enableUser").permitAll()
                .antMatchers("/loginUrl").permitAll()

                .anyRequest().authenticated()

                .and()
                .rememberMe()
                //Set Expiration Time
                .tokenValiditySeconds(3600)
                //Change Cookie Name
                .rememberMeCookieName("my-cookie")
                // Change Parameter for cookie
                .rememberMeParameter("remember")
                .tokenRepository(persistentTokenRepository())
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


    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}
