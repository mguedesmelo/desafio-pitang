package br.com.car.rental.configuration;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import br.com.car.rental.service.AuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private FilterToken filter;

	@Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
        		AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(authenticationService).passwordEncoder(
        		this.passwordEncoder());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        return http
				.cors(withDefaults())
				.authorizeHttpRequests((auth) -> auth
						.requestMatchers(
								antMatcher("/h2-console/**/**"),
								antMatcher("/api/signin"), 
								antMatcher("/api/logout"), 
								antMatcher("/api/users/**"), 
								antMatcher("/css/**"), 
								antMatcher("/icons/**"), 
								antMatcher("/images/**"), 
								antMatcher("/js/**"), 
								antMatcher("/plugins/**"), 
								antMatcher("/*/imagem/**")
								)
						.permitAll()
						.anyRequest()
						.authenticated())
//				.formLogin(form -> form
//						.loginPage("/login")
//						.defaultSuccessUrl("/index", true)
//						.permitAll())
				.logout(logout -> logout
						.logoutUrl("/api/logout")
						.logoutSuccessUrl("/login")
						.invalidateHttpSession(true))
				.csrf((csrf) -> csrf
						.disable())
				.authenticationManager(authenticationManager)
				.headers((headers) -> headers
	            		.contentTypeOptions(withDefaults())
	            		.frameOptions(withDefaults())
	            		.disable())
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();

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

//	@Bean
//	UserDetailsManager users(DataSource dataSource) {
//		return new JdbcUserDetailsManager(dataSource);
//	}

	@Bean
	AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println("h3ll0 = " + bCryptPasswordEncoder.encode("h3ll0"));
		return bCryptPasswordEncoder;
	}
}
