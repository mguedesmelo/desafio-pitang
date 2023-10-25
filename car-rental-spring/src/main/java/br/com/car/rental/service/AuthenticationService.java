package br.com.car.rental.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.car.rental.model.User;
import br.com.car.rental.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = this.userRepository.findByLogin(username);
        if (optional.isPresent()) {
        	return optional.get();
        }

        throw new UsernameNotFoundException("Invalid login or password");
    }
}
