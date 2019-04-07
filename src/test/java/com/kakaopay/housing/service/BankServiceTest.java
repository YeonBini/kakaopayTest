package com.kakaopay.housing.service;

import com.google.gson.JsonArray;
import com.kakaopay.housing.bank.repository.BankRepository;
import com.kakaopay.housing.bank.service.BankService;
import com.kakaopay.housing.banks.domain.Banks;
import com.kakaopay.housing.banks.service.BanksService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BankServiceTest {

    @Autowired
    BankRepository bankRepository;

    @Autowired
    BankService bankService;

    @Test
    public void bankInfoTest() {
        //given
        bankService.saveData();

        //when
        JSONObject jsonObject = bankService.bankFundsInfo();

        //then
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void readCsvTest() {
        // when
        JSONObject jsonObject = bankService.saveData();

        System.out.println(jsonObject.toJSONString());
        // then
        Assert.assertTrue(jsonObject.get("result") != "Already Executed");
    }

}
