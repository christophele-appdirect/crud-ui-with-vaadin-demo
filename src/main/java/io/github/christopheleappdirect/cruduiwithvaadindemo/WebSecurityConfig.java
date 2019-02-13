package io.github.christopheleappdirect.cruduiwithvaadindemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.openid.OpenIDConsumer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final OpenIDConsumer openIDConsumer;
	private final AuthenticationUserDetailsService<OpenIDAuthenticationToken> userDetailsService;

	public WebSecurityConfig(OpenIDConsumer openIDConsumer,
	                         AuthenticationUserDetailsService<OpenIDAuthenticationToken> userDetailsService) {
		this.openIDConsumer = openIDConsumer;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors().disable()
				.csrf().disable()
				.formLogin().disable()
				.rememberMe().disable()
					.openidLogin()
						.loginPage("/login/openid")
						.consumer(openIDConsumer)
						.authenticationUserDetailsService(userDetailsService)
				.and()
					.logout()
						.logoutUrl("/logout")
						.clearAuthentication(true)
						.deleteCookies("JSESSIONID")
						.invalidateHttpSession(true)
						.logoutSuccessUrl("/logout?you-are-successfully-logged-out-trust-me-i-was-just-lazy-making-a-homepage")
				.and()
					.authorizeRequests()
						.antMatchers("/")
						.hasRole("USER")
				.and()
					.authorizeRequests()
						.anyRequest()
						.permitAll();
	}
}
