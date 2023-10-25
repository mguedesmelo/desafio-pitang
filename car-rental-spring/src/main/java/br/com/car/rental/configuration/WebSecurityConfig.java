package br.com.car.rental.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSecurityConfig {
//    @Autowired
//    private AuthenticationService autenticacaoService;
//    @Autowired
//    private UserService userService;
//	@Autowired
//	private CustomOAuth2UserService oauthUserService;

//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(autenticacaoService).passwordEncoder(this.passwordEncoder());
//
//        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
//        http
//            .authorizeHttpRequests((auth) -> auth
//                .antMatchers("/index", "/home", "/home/**", "/api/**", "/css/**", "/icons/**", "/images/**", "/js/**", "/plugins/**", "/*/imagem/**", "/oauth/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//            )
//            .formLogin(form -> form
//                .loginPage("/login")
//                .defaultSuccessUrl("/index", true)
//                .permitAll()
//            )
//            .logout(logout -> logout
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/home")
//            )
//            .csrf().disable()
//            .authenticationManager(authenticationManager)
//            .headers().frameOptions().disable();
//
//        http
//        	.oauth2Login()
//	        .loginPage("/login")
//	        .userInfoEndpoint()
//	        .userService(oauthUserService)
//	        .and()
//	        .successHandler(new AuthenticationSuccessHandler() {
//	            @Override
//	            public void onAuthenticationSuccess(HttpServletRequest request, 
//	            		HttpServletResponse response, Authentication authentication) 
//	            				throws IOException, ServletException {
//	                CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
//	                request.getSession(true).setAttribute("oauthUser", oauthUser);
//	                userService.processOAuthPostLogin(oauthUser);
//	                response.sendRedirect("/index");
//	            }
//	        });
//        // TODO estudar melhor pra que serve isso
////        http.headers().frameOptions().disable();
//
//        return http.build();
//    }
//
//    @Bean
//    UserDetailsManager users(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    LocaleResolver localeResolver() {
//        SessionLocaleResolver toReturn = new SessionLocaleResolver();
//        toReturn.setDefaultLocale(Locale.US);
//        toReturn.setLocaleAttributeName("session.current.locale");
//        toReturn.setTimeZoneAttributeName("session.current.timezone");
//        return toReturn;
//    }

//    @Bean
//    LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor toReturn = new LocaleChangeInterceptor();
//        toReturn.setParamName("lang");
//        return toReturn;
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
}
