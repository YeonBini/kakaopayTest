package com.kakaopay.housing.bank.service;

import com.kakaopay.housing.bank.repository.BankRepository;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BankServiceTest2 {

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
