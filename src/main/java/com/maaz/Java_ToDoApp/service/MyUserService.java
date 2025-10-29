package com.maaz.Java_ToDoApp.service;

import com.maaz.Java_ToDoApp.model.User;
import com.maaz.Java_ToDoApp.model.UserPrincipal;
import com.maaz.Java_ToDoApp.repo.Authrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MyUserService implements UserDetailsService {

    @Autowired
    Authrepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("========" + username + "======");
        User user =  repo.findByEmail(username);
        System.out.println("========" + user + "======");

        if (user == null){
            System.out.println("User 404");
            throw new UsernameNotFoundException("User 404");
        }
        return new UserPrincipal(user);
    }

}
