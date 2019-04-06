package com.kakaopay.housing.service;

import com.kakaopay.housing.bank.domain.Banks;
import com.kakaopay.housing.bank.service.BanksService;
import org.junit.After;
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
    BanksService banksService;

    @Test
    public void readCsvTest() {
        // when
        List<Banks> banksList = banksService.saveData();

        // then
        Assert.assertTrue(banksList.size() > 0);
    }

}
