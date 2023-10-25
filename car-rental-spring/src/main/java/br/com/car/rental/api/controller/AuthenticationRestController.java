package br.com.car.rental.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.car.rental.api.data.LoginDto;
import br.com.car.rental.exception.BusinessException;
import br.com.car.rental.model.User;
import br.com.car.rental.service.TokenService;

@RestController
@RequestMapping("/api")
public class AuthenticationRestController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenService;
	
	@PostMapping(value = "/signin")
	public String login(@RequestBody LoginDto loginDto) {
		try {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					loginDto.login(), loginDto.password());
			Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			if (authenticate.getPrincipal() == null) {
				throw new UsernameNotFoundException("Invalid login or password");
			}
			User user = (User) authenticate.getPrincipal();
			
			return this.tokenService.generateToken(user);
		} catch (BadCredentialsException e) {
			throw new BusinessException("Invalid login or password");
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}
}
