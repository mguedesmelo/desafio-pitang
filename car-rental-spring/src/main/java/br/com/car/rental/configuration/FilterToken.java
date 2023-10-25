package br.com.car.rental.configuration;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter {
//    @Autowired
//    private TokenService tokenService;
//    @Autowired
//    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
    		FilterChain filterChain) throws ServletException, IOException {
//        var authorizationHeader = request.getHeader("Authorization");
//        if (authorizationHeader != null) {
//        	String token = authorizationHeader.replace("Bearer ", "");
//            String subject = this.tokenService.getSubject(token);
//
//            User user = this.userRepository.findByLogin(subject);
//
//            var authentication = new UsernamePasswordAuthenticationToken(user, null, 
//            		user.getAuthorities());
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }

        filterChain.doFilter(request, response);
    }
}