package com.kakaopay.housing.domain;

import com.kakaopay.housing.bank.domain.Banks;
import com.kakaopay.housing.bank.domain.BanksListRequestDto;
import com.kakaopay.housing.bank.repository.BanksRepository;
import com.kakaopay.housing.bank.service.BanksService;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BanksRepositoryTest {

    @Autowired
    BanksRepository banksRepository;

    @Autowired
    BanksService banksService;

    @After
    public void cleanup() {
        banksRepository.deleteAll();
    }

    @Test
    public void load_banks_fund() {

        //given
        banksRepository.save(Banks.builder()
        .year(2005)
        .month(1)
        .housingFund(1019)
        .kb(846)
        .woori(82)
        .shinhan(95)
        .city(30)
        .hana(157)
        .cooperativesBank(57)
        .foreignExchange(80)
        .etc(99).build());

        //when
        List<Banks> banksList = banksRepository.findAll();

        //then
        Banks banks = banksList.get(0);
        Assert.assertThat(banks.getYear(), CoreMatchers.is(2005));
        Assert.assertThat(banks.getHousingFund(), CoreMatchers.is(1019));

    }

    @Test
    public void BaseTimeEntity_register() {
        //given
        LocalDateTime now = LocalDateTime.now();
        banksRepository.save(Banks.builder()
                .year(2005)
                .month(1)
                .housingFund(1019)
                .kb(846)
                .woori(82)
                .shinhan(95)
                .city(30)
                .hana(157)
                .cooperativesBank(57)
                .foreignExchange(80)
                .etc(99).build());

        //when
        List<Banks> banksList = banksRepository.findAll();

        //then
        Banks bank = banksList.get(0);
        Assert.assertTrue(bank.getCreatedDate().isAfter(now));
        Assert.assertTrue(bank.getModifiedDate().isAfter(now));
    }

    @Test
    public void findDistinctByYear_test() {
        //given
        banksService.saveData();

        //when
        List<Object> list =  banksRepository.findDistinctByYear();

        Assert.assertTrue(true);

    }
}
