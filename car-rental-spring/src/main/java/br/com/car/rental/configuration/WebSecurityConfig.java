package br.com.car.rental.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import br.com.car.rental.service.AuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	private AuthenticationService autenticacaoService;
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private FilterToken filter;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(autenticacaoService).passwordEncoder(this.passwordEncoder());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

		return http
				.authorizeHttpRequests((auth) -> auth
						.requestMatchers("/api/signin", "/api/users/**", "/css/**", "/icons/**", 
								"/images/**", "/js/**", "/plugins/**", "/*/imagem/**")
						.permitAll()
						.anyRequest()
						.authenticated())
				.formLogin(form -> form
						.loginPage("/login")
						.defaultSuccessUrl("/index", true)
						.permitAll())
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/home"))
				.csrf((csrf) -> csrf
						.disable())
				.authenticationManager(authenticationManager)
				.build();
//            .headers((headers) -> headers
//            		.contentTypeOptions(withDefaults())
//            		.frameOptions(withDefaults()))
//            .disable();



//    	return http
//    			.csrf((csrf) -> csrf.disable())
//    			.sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .and()
//                .authorizeHttpRequests((authorizeHttpRequests) ->
//		                authorizeHttpRequests
//		                        .requestMatchers("/api/signin", "/api/users/**", "/css/**", 
//		                        		"/icons/**", "/images/**", "/js/**", "/plugins/**", 
//		                        		"/*/image/**")
//		                        .permitAll()
//		                        .anyRequest().authenticated()
//		        )
//                .antMatchers(HttpMethod.POST, "/login")
//                .permitAll()
//                .antMatchers(HttpMethod.GET, "/home")
//                .permitAll()
//                .anyRequest().authenticated()
//                .and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
//                .build();
	}

	@Bean
	UserDetailsManager users(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
