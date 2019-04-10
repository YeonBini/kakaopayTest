package com.kakaopay.housing.bank.service;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BankServiceTest {

    @Autowired
    BankService bankService;

    @Before
    public void setUp() {
        bankService.saveData();
    }

    @Test
    public void saveData() {
        //when
        JSONObject result =  bankService.saveData();

        //then
        assertTrue(result.get("result").equals("Already Executed"));
    }

    @Test
    public void bankList() {
        // when
        JSONObject jsonObject = bankService.bankList();

        // then
        assertTrue(((List<String>)jsonObject.get("result")).size()==9);
    }

    @Test
    public void bankFundsInfo() {
        // when
        JSONObject jsonObject = bankService.bankFundsInfo();

        //then
        System.out.println(jsonObject.toJSONString());
        assertTrue(jsonObject.get("name").equals("주택금융 공급현황"));
    }

    @Test
    public void largestFundsBank() {
        // when
        JSONObject jsonObject = bankService.largestFundsBank("2005");

        //then
        assertTrue(jsonObject.get("bank").equals("주택도시기금1"));
    }

    @Test
    public void foreignBankInfo() {
        // when
        JSONObject jsonObject = bankService.foreignBankInfo();


    }
}