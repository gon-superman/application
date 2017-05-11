package com.qph.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 禁用Boot的默认Security配置
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AppUserDetailsService detailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		        .antMatchers("/", "/public/**").permitAll()
		        .antMatchers("/admin/**").hasAuthority("ADMIN")
		        .anyRequest().fullyAuthenticated()
		        .and()
		        .formLogin()
		        .loginPage("/login")
		        .failureUrl("/login?error")
		        .usernameParameter("username")
		        .permitAll()
		        .and()
		        .logout()
		        .logoutUrl("/logout")
		        .deleteCookies("remember-me")
		        .logoutSuccessUrl("/")
		        .permitAll()
		        .and()
		        .rememberMe();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 配置所有/resources/*开头请求都放过
		web.ignoring().antMatchers("/resources/**");
	}

}
