package br.com.car.rental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.car.rental.api.data.UserDto;
import br.com.car.rental.api.data.UserRequestDto;
import br.com.car.rental.api.data.mapper.UserMapper;
import br.com.car.rental.exception.BusinessException;
import br.com.car.rental.exception.RecordNotFoundException;
import br.com.car.rental.model.User;
import br.com.car.rental.repository.UserRepository;
import br.com.car.rental.shared.StringUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class UserService extends BaseService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;

	public List<UserDto> findAll() {
		return this.userRepository.findAll().stream().map(userMapper::map).toList();
	}

	public List<User> findAllUsers() {
		return this.userRepository.findAll();
	}

	
//	firstName,
//	lastName, 
//	email,
//	LocalDate birthDay, 
//	login,
//	phone, 
//	password
	
	public UserDto save(@Valid UserRequestDto userRequestDto) {
		if (StringUtil.isNullOrEmpty(userRequestDto.firstName(), userRequestDto.lastName(),
				userRequestDto.email(), userRequestDto.login(), userRequestDto.phone(),
				userRequestDto.password()) || userRequestDto.birthDay() == null) {
			throw new BusinessException("Missing fields");
		}
		userRepository.findAllByEmail(userRequestDto.email()).stream().findAny().ifPresent(c -> {
			throw new BusinessException("Email already exists");
		});

		userRepository.findAllByLogin(userRequestDto.login()).stream().findAny().ifPresent(c -> {
			throw new BusinessException("Login already exists");
		});

		User user = this.userMapper.toModel(userRequestDto);
		return this.userMapper.map(this.userRepository.save(user));
	}

	public UserDto update(@Positive @NotNull Long id, @Valid UserRequestDto userRequestDto) {
		return this.userRepository.findById(id).map(actual -> {
			actual.setFirstName(userRequestDto.firstName());
			actual.setLastName(userRequestDto.lastName());
			actual.setEmail(userRequestDto.email());
			actual.setBirthDay(userRequestDto.birthDay());
			actual.setLogin(userRequestDto.login());
			actual.setPassword(userRequestDto.password());
			actual.setPhone(userRequestDto.phone());
			return this.userMapper.map(this.userRepository.save(actual));
		}).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public UserDto findById(@Positive @NotNull Long id) {
		return this.userMapper.map(this.findOptionalById(id).orElse(null));
	}

	public Optional<User> findByLogin(String login) {
		return this.userRepository.findByLogin(login);
	}

	public Optional<User> findOptionalById(@Positive @NotNull Long id) {
		return this.userRepository.findById(id);
	}

	public void delete(@Positive @NotNull Long id) {
		this.userRepository.delete(this.userRepository.findById(id).orElseThrow(
				() -> new RecordNotFoundException(id)));
	}

//	public User findImageById(@Positive @NotNull Long id) {
//		return this.userRepository.findImageById(id).orElse(null);
//	}
}
