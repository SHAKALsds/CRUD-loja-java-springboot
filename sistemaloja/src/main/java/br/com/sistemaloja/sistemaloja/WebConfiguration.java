package br.com.sistemaloja.sistemaloja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder
		.inMemoryAuthentication()
		.withUser("hiago").password("{noop}hiago").roles("USER")
		.and().withUser("root").password("{noop}root").roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/", "/css/**").permitAll()
		.antMatchers("/**").permitAll()
		.antMatchers("/**").permitAll()
		.anyRequest().authenticated().and().formLogin().permitAll()
		.and().logout().permitAll()
		.and().csrf().disable();
	}
	
	
}
