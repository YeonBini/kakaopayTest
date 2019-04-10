package com.kakaopay.housing.auth.service;

import com.kakaopay.housing.auth.conf.AuthenticationException;
import com.kakaopay.housing.auth.domain.UserToken;
import com.kakaopay.housing.auth.repository.UserTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserTokenService {

    UserTokenRepository userTokenRepository;

    public UserToken saveOrUpdate(UserToken userToken) {
        return userTokenRepository.save(userToken);
    }
    
    public String registerUser(String username, String password) {
        if(getUserTokenByUserName(username) != null) {
            return "Already Registered";
        }

        UserToken userToken = UserToken.getInstance(username, password);
        saveOrUpdate(userToken);

        return userToken.getToken();
    }

    public String updateTokenGenCount(String token) {
        String username = UserToken.getUserNameByToken(token);
        UserToken userToken = getUserTokenByUserName(username);

        String newToken = userToken.addCountAndGetToken();
        userToken.setToken(newToken);
        saveOrUpdate(userToken);

        return newToken;
    }

    private UserToken getUserTokenByUserName(String username) {
        return userTokenRepository.findByUsername(username);
    }

    public String getUserTokenByLogin(String username, String password) {
        UserToken userToken = getUserTokenByUserName(username);

        if(userToken == null) {
            return "No token for " + username;
        }

        if(password.equals(userToken.getPassword())) {
            return userToken.getToken();
        }

        return "invalid password";
    }

    public boolean validateToken(String token) {
        String username;
        UserToken userToken;
        try {
            username = UserToken.getUserNameByToken(token);
            userToken = getUserTokenByUserName(username);
        } catch (Exception e) {
            throw new AuthenticationException("Authentication Failed");
        }

        return userToken.verifyToken(token);
    }

}
