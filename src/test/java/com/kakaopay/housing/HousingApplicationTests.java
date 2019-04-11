package com.kakaopay.housing;

import com.kakaopay.housing.auth.controller.UserController;
import com.kakaopay.housing.auth.domain.UserSignInInfo;
import com.kakaopay.housing.auth.domain.UserToken;
import com.kakaopay.housing.auth.service.UserTokenService;
import com.kakaopay.housing.bank.controller.BankController;
import com.kakaopay.housing.bank.repository.BankRepository;
import com.kakaopay.housing.bank.service.BankService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HousingApplicationTests {

    @Autowired
    UserController userController;

    @Autowired
    UserTokenService userTokenService;

    @Autowired
    BankController bankController;

    @Autowired
    BankService bankService;

    @Autowired
    BankRepository bankRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bankController).build();

        UserSignInInfo userSignInInfo = new UserSignInInfo();
        userSignInInfo.setUsername("yeonbin");
        userSignInInfo.setPassword("1234");

        userTokenService.registerUser(userSignInInfo.getUsername(), userSignInInfo.getPassword());

        bankService.saveData();
    }

    @Test
    public void 초기_데이터세팅_test() throws Exception {
        UserToken userToken = UserToken.getInstance("yeonbin", "1234");
        String token = userToken.getToken();

        mockMvc.perform(post("/bank/init").header("Authorization", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    @Test
    public void 은행리스트_test() throws Exception {
        UserToken userToken = UserToken.getInstance("yeonbin", "1234");
        String token = userToken.getToken();

        mockMvc.perform(get("/bank/list").header("Authorization", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    @Test
    public void 은행정보_test() throws Exception {
        UserToken userToken = UserToken.getInstance("yeonbin", "1234");
        String token = userToken.getToken();

        mockMvc.perform(get("/bank/info").header("Authorization", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    @Test
    public void 가장_큰_지원_은행_test() throws Exception {
        UserToken userToken = UserToken.getInstance("yeonbin", "1234");
        String token = userToken.getToken();

        MediaType application_json = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
        mockMvc.perform(get("/bank/largest").contentType(application_json).header("Authorization", token).param("year", "2005"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    @Test
    public void 외환은행_test() throws Exception {
        UserToken userToken = UserToken.getInstance("yeonbin", "1234");
        String token = userToken.getToken();

        mockMvc.perform(get("/bank/foreignBank").header("Authorization", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }
}
