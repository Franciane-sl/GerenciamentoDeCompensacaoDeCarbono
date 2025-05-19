package com.futurodevV2.GerenciamentoDeCarbono.Service;

import com.futurodevV2.GerenciamentoDeCarbono.Model.Entity.User;
import com.futurodevV2.GerenciamentoDeCarbono.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("O usuário não foi encontrado."));
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public UserDetails loadUserByUsername(String username) {
        return userDetailsService.loadUserByUsername(username);
    }

    public void registerUser(User user){
        if (!userRepository.existsByUsername(user.getUsername())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }else{
            throw new RuntimeException("O usuário já exite.");
        }
    }
}
