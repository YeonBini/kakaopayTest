package com.kakaopay.housing.auth.controller;

import com.kakaopay.housing.auth.domain.UserSignInInfo;
import com.kakaopay.housing.auth.service.UserTokenService;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    UserTokenService userTokenService;

    @PostMapping("/signUp")
    public JSONObject registerUserInfo(@RequestBody UserSignInInfo userSignInInfo) {
        return userTokenService.registerUser(userSignInInfo.getUsername(),
                userSignInInfo.getPassword());
    }

    @GetMapping("/signIn")
    public JSONObject loginUser(@RequestBody UserSignInInfo userSignInInfo) {
        return userTokenService.getUserTokenByLogin(userSignInInfo.getUsername(),
                userSignInInfo.getPassword());
    }

    @PutMapping("/refreshToken")
    public JSONObject refreshToken(@RequestHeader String authorization) {
        return userTokenService.updateTokenGenCount(authorization);
    }

}
