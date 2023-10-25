package br.com.car.rental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.car.rental.api.data.UserDto;
import br.com.car.rental.api.data.UserMapper;
import br.com.car.rental.api.data.UserRequestDto;
import br.com.car.rental.exception.RecordNotFoundException;
import br.com.car.rental.model.User;
import br.com.car.rental.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class UserService extends BaseService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
    private UserMapper userMapper;

	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	public UserDto save(UserRequestDto userRequestDto) {
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

	public Optional<User> findOptionalById(@Positive @NotNull Long id) {
		return this.userRepository.findById(id);
	}

	public void delete(@Positive @NotNull Long id) {
		this.userRepository.delete(this.userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
	}

	public User findImageById(@Positive @NotNull Long id) {
		return this.userRepository.findImageById(id).orElse(null);
	}
}
