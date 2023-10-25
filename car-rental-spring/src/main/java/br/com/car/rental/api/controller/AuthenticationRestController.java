package br.com.car.rental.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.car.rental.api.data.LoginDto;
import br.com.car.rental.model.User;
import br.com.car.rental.service.TokenService;

@RestController
public class AuthenticationRestController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenService;
	
	
	public String login(@RequestBody LoginDto loginDto) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.login(), loginDto.password());
		Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		User user = (User) authenticate.getPrincipal();
		
		return this.tokenService.generateToken(user);
	}
}
