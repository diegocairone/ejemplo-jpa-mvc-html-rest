package com.cairone.appexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.cairone.appexample.services.UsuarioService;
import com.cairone.appexample.utils.Md5Encoder;

@Configuration @EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired private UsuarioService usuarioService = null;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
	        .anyRequest().authenticated()
	        .and()
	        .csrf().disable()
	    .formLogin()
	        .loginPage("/login")
	        .permitAll()
	        .and()
	    .logout()
	        .permitAll();
	  }
		
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthenticationProvider());
    }
	
	@Bean
	public AuthenticationProvider getAuthenticationProvider() {
		return new AuthenticationProvider() {
			
			@Override
			public boolean supports(Class<?> authentication) {
				return true;
			}
			
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				
				String username = authentication.getName();
		        String password = authentication.getCredentials().toString();
		        
				UserDetails userDetails = usuarioService.loadUserByUsername(username);
				
				if(userDetails == null) {
					throw new BadCredentialsException("El USUARIO no existe!");
				}
				
				String passwordMd5 = Md5Encoder.encode(authentication.getCredentials().toString());
				
				if(!userDetails.getPassword().equals(passwordMd5)) {
					throw new BadCredentialsException("La contrasena es incorrecta!");
				}
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
				
				return usernamePasswordAuthenticationToken;
			}
		};
	}
}
