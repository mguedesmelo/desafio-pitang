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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "AuthenticationRestController")
public class AuthenticationRestController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserService userService;

	@GetMapping(value = "/signin")
	@Operation(summary = "Esta rota espera um objeto com os campos login e password e deve "
			+ "retornar o token de acesso da API (JWT) com as informações do usuário logado", 
			method = "GET")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Login inexistente ou senha inválida"),
    })
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
	@Operation(summary = "Retorna as informações do usuário logado (firstName, lastName, email, "
			+ "birthday, login, phone, cars, createdAt e lastLogin", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "JSON contendo os dados do usuário"),
			@ApiResponse(responseCode = "401", description = "Token não enviado, retorna a "
					+ "mensagem \"Unauthorized\""),
			@ApiResponse(responseCode = "401", description = "Token expirado, retorna a "
					+ "mensagem \"Unauthorized - invalid session\""),
    })
	public User me() {
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
        if (login != null) {
            return this.userService.findByLogin(login).orElse(null);
        }
        return null;
	}
}
