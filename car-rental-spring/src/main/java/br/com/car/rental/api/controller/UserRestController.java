package br.com.car.rental.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.car.rental.api.data.UserDto;
import br.com.car.rental.api.data.UserRequestDto;
import br.com.car.rental.exception.BusinessException;
import br.com.car.rental.model.User;
import br.com.car.rental.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/users")
public class UserRestController extends BaseRestController<User> {
	@Autowired
	private UserService userService;

	@GetMapping
	public List<UserDto> findAll() {
		return this.userService.findAll();
	}

	@PostMapping
	public ResponseEntity<UserDto> save(@RequestBody UserRequestDto user) {
		UserDto savedUser = this.userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	@GetMapping(value = "/{id}")
	public UserDto findById(@PathVariable("id") @Positive @NotNull Long id) {
		return this.userService.findById(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable @Positive @NotNull Long id) {
		this.userService.delete(id);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDto> update(@PathVariable @Positive @NotNull Long id,
			@RequestBody @Valid UserRequestDto user) {
		UserDto savedUser = this.userService.update(id, user);
		return ResponseEntity.status(HttpStatus.OK).body(savedUser);
	}

	@PostMapping("/upload/{id}")
    public ResponseEntity<UserDto> upload(@PathVariable @Positive @NotNull Long id, 
    		@RequestParam("imagem") MultipartFile file) {
        try {
        	return ResponseEntity.status(HttpStatus.CREATED).body(
        			this.userService.saveImage(id, file.getName(), file.getSize(), 
        			file.getContentType(), file.getBytes()));
        } catch (IOException e) {
            throw new BusinessException("Error saving image");
        }
    }

	@GetMapping("/image/{id}")
    public void image(@PathVariable Long id, HttpServletResponse response) {
    	Optional<User> optional = this.userService.findImagemById(id);
		optional.ifPresentOrElse((u) -> {
			loadImage(response, u.getImageType(), u.getImage());
		}, () -> {
			loadNoPhoto(response);
		});
    }
}
