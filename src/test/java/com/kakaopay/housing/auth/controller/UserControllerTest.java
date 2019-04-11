package com.kakaopay.housing.auth.controller;

import com.kakaopay.housing.auth.domain.UserSignInInfo;
import com.kakaopay.housing.auth.service.UserTokenService;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserController userController;

    @Autowired
    UserTokenService userTokenService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        UserSignInInfo userSignInInfo = new UserSignInInfo();
        userSignInInfo.setUsername("yeonbin");
        userSignInInfo.setPassword("1234");

        userTokenService.registerUser(userSignInInfo.getUsername(), userSignInInfo.getPassword());
    }

    @Test
    public void singUp() throws Exception {
        MediaType application_json = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "yeonbin2");
        jsonObject.put("password", "12345");

        mockMvc.perform(post("/user/signUp").contentType(application_json)
                .content(jsonObject.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void signIn() throws Exception {
        MediaType application_json = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "yeonbin");
        jsonObject.put("password", "1234");

        mockMvc.perform(get("/user/signIn").contentType(application_json).content(jsonObject.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void refreshToken() throws Exception {
        String token = (String) userTokenService.getUserTokenByLogin("yeonbin", "1234").get("result");

        MockHttpServletResponse response = mockMvc.perform(put("/user/refreshToken")
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertFalse(token.equals(response.getContentAsString()));
        System.out.println(token);
        System.out.println(response.getContentAsString());

    }
}