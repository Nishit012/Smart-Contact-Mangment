package com.example.smartcontactmanager.config;

import com.example.smartcontactmanager.Entities.User;
import com.example.smartcontactmanager.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



//जब कोई user login करता है, तो user का data (जैसे username और password) लाया जा सके। अगर user नहीं मिलता, तो यह method error देता है।
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.getUserByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        CustomUserDetails userDetails = new CustomUserDetails(user);
        return userDetails;
    }
}
