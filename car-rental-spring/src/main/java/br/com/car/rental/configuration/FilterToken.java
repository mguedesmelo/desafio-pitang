package br.com.car.rental.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.car.rental.exception.ResponseMessage;
import br.com.car.rental.model.User;
import br.com.car.rental.service.TokenService;
import br.com.car.rental.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
    		HttpServletResponse response, 
    		FilterChain filterChain) throws ServletException, IOException {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null) {
        	this.sendResponseMessage(response, HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        } else {
        	String token = authorizationHeader.replace("Bearer ", "");

        	try {
        		String subject = this.tokenService.getSubject(token);

	            User user = this.userService.findByLogin(subject).orElseThrow();
	
	            var authentication = new UsernamePasswordAuthenticationToken(user, null, 
	            		user.getAuthorities());
	            
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	
	            filterChain.doFilter(request, response);
        	} catch (TokenExpiredException e) {
        		this.sendResponseMessage(response, HttpStatus.UNAUTHORIZED.value(), "Unauthorized - invalid session");
        	}
        }
    }

	private void sendResponseMessage(HttpServletResponse response, int responseCode, 
			String responseBody) throws IOException {
	    response.addHeader("Content-Type", "application/json;charset=UTF-8");
	    response.setStatus(responseCode);
	    ResponseMessage unauthorized = new ResponseMessage(responseCode, responseBody);
	    objectMapper.writeValue(response.getOutputStream(), unauthorized);
	    response.flushBuffer();			
	}
}
