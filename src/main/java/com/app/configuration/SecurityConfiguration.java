package com.app.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;

import com.app.authentication.SinarmasMsigEntryPoint;
import com.app.authentication.SinarmasMsigLogoutHandler;

/**
 * 
 * @author Patar.Tambunan
 * Security configuration need to make sure everything clickable on
 * security web.
 *
 *
 */

@Configuration
@EnableWebSecurity
@Order(101)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	 
	@Autowired
	private ConsumerTokenServices tokenService;
	 @Autowired 
	 private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username, password,active as enabled from oauth_users where username=?")
		.authoritiesByUsernameQuery("select u.username, p.user_role from oauth_users u inner join oauth_permissions p on u.id = p.id_user where u.username=?");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// .TODO Auto-generated method stub
		http.csrf().disable()
		.formLogin().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
            .anonymous()
        .and()
            .exceptionHandling().authenticationEntryPoint(new SinarmasMsigEntryPoint())
           .and()
        .logout()
   	     .logoutUrl("/oauth/logout")
   	     .logoutSuccessHandler(new SinarmasMsigLogoutHandler(tokenService))
   	     .deleteCookies("JSESSIONID")
   	     .permitAll()
   	     .and()
         .authorizeRequests()
         .antMatchers("/manager/**").permitAll() 
         .anyRequest().authenticated();
	}
	
	
	
	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	 return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

}
