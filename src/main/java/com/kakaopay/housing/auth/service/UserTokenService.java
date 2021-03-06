package com.kakaopay.housing.auth.service;

import com.kakaopay.housing.auth.conf.AuthenticationException;
import com.kakaopay.housing.auth.domain.UserToken;
import com.kakaopay.housing.auth.repository.UserTokenRepository;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserTokenService {

    UserTokenRepository userTokenRepository;

    public UserToken saveOrUpdate(UserToken userToken) {
        return userTokenRepository.save(userToken);
    }
    
    public JSONObject registerUser(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        if(getUserTokenByUserName(username) != null) {
            jsonObject.put("result", "Already Registered");
        }

        UserToken userToken = UserToken.getInstance(username, password);
        saveOrUpdate(userToken);
        jsonObject.put("result", userToken.getToken());
        return jsonObject;
    }

    public JSONObject updateTokenGenCount(String token) {
        JSONObject jsonObject = new JSONObject();
        String username = UserToken.getUserNameByToken(token);
        UserToken userToken = getUserTokenByUserName(username);

        String newToken = userToken.addCountAndGetToken();
        userToken.setToken(newToken);
        saveOrUpdate(userToken);

        jsonObject.put("result", newToken);

        return jsonObject;
    }

    private UserToken getUserTokenByUserName(String username) {
        return userTokenRepository.findByUsername(username);
    }

    public JSONObject getUserTokenByLogin(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        UserToken userToken = getUserTokenByUserName(username);

        if(userToken == null) {
            jsonObject.put("result", "No token for " + username);
        }

        if(!password.equals(userToken.getPassword())) {
            jsonObject.put("result", "invalid password");
        }
        jsonObject.put("result", userToken.getToken());
        return jsonObject;
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
