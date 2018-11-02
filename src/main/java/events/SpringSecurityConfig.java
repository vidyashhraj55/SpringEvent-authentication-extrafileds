package events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private AuthenticationEntryPoint authEntryPoint;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest().authenticated()
				.and().httpBasic();
		http.csrf().disable();
//				.authenticationEntryPoint(authEntryPoint);
	}
	

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("john123").password("password").roles("USER"); 
		auth.inMemoryAuthentication().withUser("john123").password("$2a$04$mpRhpXb3gaY2TMo0P2mAKuj8ZbquNxmyJ7ZRP/qVzP4iUi6ZxPrD2").roles("USER");
		auth.inMemoryAuthentication().withUser("john").password("$2a$04$mpRhpXb3gaY2TMo0P2mAKuj8ZbquNxmyJ7ZRP/qVzP4iUi6ZxPrD2").roles("USER");
	}
	@Bean
	public BCryptPasswordEncoder encoder() {
	return new BCryptPasswordEncoder();
	}

	

}