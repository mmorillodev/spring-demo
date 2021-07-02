package br.com.thoughtworks.config.security;

import br.com.thoughtworks.controller.respository.UserRepository;
import br.com.thoughtworks.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Usuario> user = userRepository.findByEmail(s);

        if(user.isPresent())
            return user.get();

        throw new UsernameNotFoundException("The user '" + s + "' doest not exist.");
    }
}
