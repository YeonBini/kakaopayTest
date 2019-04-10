package com.kakaopay.housing.auth.service;

import com.kakaopay.housing.auth.domain.UserToken;
import com.kakaopay.housing.auth.repository.UserTokenRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTokenServiceTest {

    @Autowired
    UserTokenService userTokenService;

    @Autowired
    UserTokenRepository userTokenRepository;

    @Before
    public void setUp() {
        userTokenService.registerUser("yeonbin", "1234");
    }

    @Test
    public void saveOrUpdate() {
        // given
        UserToken userToken = UserToken.getInstance("yeonbin2", "12345");

        // when
        userTokenRepository.save(userToken);

        // then
        Assert.assertTrue(userTokenRepository.findByUsername("yeonbin2").equals("12345"));
    }

    @Test
    public void registerUser() {
        // given
        UserToken userToken = userTokenRepository.findByUsername("yeonbin");

        // when
        UserToken userTokenResult =  userTokenService.saveOrUpdate(userToken);

        // then
        Assert.assertTrue(userTokenResult.getPassword().equals("1234"));

    }

    @Test
    public void updateTokenGenCount() {
        // given
        UserToken userToken = userTokenRepository.findByUsername("yeonbin");
        // when
        String newToken = userTokenService.updateTokenGenCount(userToken.getToken());

        // then
        Assert.assertFalse(userToken.getToken().equals(newToken));
    }

    @Test
    public void getUserTokenByLogin() {
        // when
        String userToken = userTokenService.getUserTokenByLogin("yeonbin", "1234");

        // then
        Assert.assertTrue(userToken.equals(userTokenRepository.findByUsername("yeonbin").getToken()));
    }

    @Test
    public void validateToken() {
        // when
        String userToken = userTokenService.getUserTokenByLogin("yeonbin", "1234");

        // then
        Assert.assertTrue(userTokenService.validateToken(userToken));

    }
}