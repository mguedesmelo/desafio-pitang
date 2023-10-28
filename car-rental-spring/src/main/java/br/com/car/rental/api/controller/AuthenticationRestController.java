package br.com.car.rental.api.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.car.rental.api.data.LoginDto;
import br.com.car.rental.exception.BusinessException;
import br.com.car.rental.model.User;
import br.com.car.rental.service.TokenService;
import br.com.car.rental.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthenticationRestController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserService userService;

	@GetMapping(value = "/signin")
	public String signin(@RequestBody LoginDto loginDto) {
		try {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					loginDto.login(), loginDto.password());
			Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			if (authenticate.getPrincipal() == null) {
				throw new UsernameNotFoundException("Invalid login or password");
			}
			User user = (User) authenticate.getPrincipal();
			
			user.setLastLogin(LocalDateTime.now());
			this.userService.updateLastLogin(user);
			
			return this.tokenService.generateToken(user);
		} catch (BadCredentialsException e) {
			throw new BusinessException("Invalid login or password");
		} catch (Throwable t) {
			throw new BusinessException(t.getMessage());
		}
	}

	@GetMapping(value = "/me")
	public User me() {
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
        if (login != null) {
            return this.userService.findByLogin(login).orElse(null);
        }
        return null;
	}
}
