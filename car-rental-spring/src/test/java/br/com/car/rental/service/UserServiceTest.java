package br.com.car.rental.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.car.rental.api.data.mapper.UserMapper;
import br.com.car.rental.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@InjectMocks
	private UserService userService;
	@Mock
	private UserRepository userRepository;
	@Mock
	private UserMapper userMapper;

	/*
	public UserDto findById(@Positive @NotNull Long id) {

	public Optional<User> findByLogin(String login) {

	public Optional<User> findOptionalById(@Positive @NotNull Long id) {

	public List<UserDto> findAll() {

	public List<User> findAllUsers() {

	public UserDto save(@Valid UserRequestDto userRequestDto) {

	public UserDto update(@Positive @NotNull Long id, @Valid UserRequestDto userRequestDto) {

	public void delete(@Positive @NotNull Long id) {
	*/
	
}
