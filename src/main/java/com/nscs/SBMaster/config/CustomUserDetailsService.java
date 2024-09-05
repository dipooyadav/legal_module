package com.nscs.SBMaster.config;

import com.nscs.SBMaster.Entity.User;
import com.nscs.SBMaster.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username);

        if(user==null){
            throw new UsernameNotFoundException("user not found ");
        }else{
            return new CustomUser(user);
        }

    }


    public UserDetails loadUserByUsername(String username,String password) throws UsernameNotFoundException {
        System.out.println(password);
        User user = userRepo.findByEmail(username);

        if(user==null){
            throw new UsernameNotFoundException("user not found ");
        }else{
            return new CustomUser(user);
        }

    }
}
