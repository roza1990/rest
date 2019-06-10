package com.example.restapi.config;

import com.example.restapi.security.CurrentUserDetailServiceImpl;
import com.example.restapi.security.JwtAuthenticationEntryPoint;
import com.example.restapi.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration

public class RestSecurityConfig extends WebSecurityConfigurerAdapter {


  @Autowired

  private CurrentUserDetailServiceImpl userDetailsService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtAuthenticationEntryPoint unauthorizedHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
      // don't create session
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
        .and()
        .authorizeRequests()
      //.antMatchers("/users").hasAnyAuthority("USER", "ADMIN")
      //.antMatchers("/user/update").permitAll()
        .antMatchers("/api/token/signIn").permitAll()
        .antMatchers("/api/token/signUp").permitAll()
        .antMatchers("/getUserDetails/*").hasAnyAuthority("ADMIN","USER")
        .anyRequest().permitAll()
        .antMatchers("/**").fullyAuthenticated().and().formLogin().loginPage("/login")
        .failureUrl("/login?error").usernameParameter("email").permitAll().and().logout().logoutUrl("/logout")
        .logoutSuccessUrl("/").permitAll();
    // Custom JWT based security filter
    http
      .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

    // disable page caching
    http.headers().cacheControl();
  }



  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(passwordEncoder);
  }

  @Bean
  public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
    return new JwtAuthenticationTokenFilter();
  }


}