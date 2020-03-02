package com.sideproject.Services;

import com.sideproject.Daos.LoginDao;
import com.sideproject.Entity.CredVault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    LoginDao loginDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        CredVault credentials = loginDao.findByUserName(userName);
        return new User(credentials.getUserName(),credentials.getPassword(), new ArrayList<>());
    }

    public List<CredVault> retrieveAllUsers() {
        return loginDao.findAll();
    }
}
