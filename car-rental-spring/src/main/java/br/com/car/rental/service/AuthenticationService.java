package br.com.car.rental.service;

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
    private UserRepository usuarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.usuarioRepository.findByLogin(username);
        if (user == null) {
        	throw new UsernameNotFoundException("User not found!");
        }
        return user;
    }
}
