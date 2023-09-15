package neo.ehsanodyssey.java.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	// Enabling CSRF protection
	// Each request will contain CSRF token that in runtime if page contain spring mvc <form:form> tag
	// we can see CSRF token in end of the form in the browser without config it in page by developer like below
	// <div>
	//    <input type="hidden" name="_csrf" value="3b6bdfb1-3c4a-423b-8264-6cbef4ca97dd">
	// </div>
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll();
		http.csrf().disable();
	}
}