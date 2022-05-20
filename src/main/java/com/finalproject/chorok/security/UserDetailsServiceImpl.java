package com.finalproject.chorok.security;


import com.finalproject.chorok.login.model.User;
import com.finalproject.chorok.login.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.NotActiveException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @SneakyThrows
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("여기 들어옴?");
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));
        System.out.println("여기 메세지 왜 안나옴?");
        if (user.isEnabled() != false){
            return new UserDetailsImpl(user);
                } else throw new NotActiveException("비활성화된 유저입니다.");

    }
}