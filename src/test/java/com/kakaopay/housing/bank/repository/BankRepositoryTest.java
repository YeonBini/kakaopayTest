package com.kakaopay.housing.bank.repository;

import com.kakaopay.housing.bank.domain.*;
import com.kakaopay.housing.bank.service.BankService;
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
public class BankRepositoryTest {

    @Autowired
    BankRepository bankRepository;

    @Autowired
    BankService bankService;

    @Autowired
    BankRepositorySupport bankRepositorySupport;

    @After
    public void cleanup() {
        bankRepository.deleteAll();
    }

    @Test
    public void load_banks_fund() {

        //given
        bankRepository.save(Bank.builder()
        .year(2005)
        .month(1)
        .instituteCode("B001")
        .instituteName("주택도시기금1)(억원)")
        .funds(1019)
        .build());

        //when
        List<Bank> banksList = bankRepository.findAll();

        //then
        Bank bank = banksList.get(0);
        Assert.assertThat(bank.getYear(), CoreMatchers.is(2005));

    }

    @Test
    public void BaseTimeEntity_register() {
        //given
        LocalDateTime now = LocalDateTime.now();
        bankRepository.save(Bank.builder()
                .year(2005)
                .month(1)
                .instituteCode("B001")
                .instituteName("주택도시기금1)(억원)")
                .funds(1019)
                .build());

        //when
        List<Bank> banksList = bankRepository.findAll();

        //then
        Bank bank = banksList.get(0);
        Assert.assertTrue(bank.getCreatedDate().isAfter(now));
        Assert.assertTrue(bank.getModifiedDate().isAfter(now));
    }

    @Test
    public void findDistinctByInstituteCode() {
        //given
        bankService.saveData();

        //when
        List<String> bankList = bankRepositorySupport.findBankList();

        //then
        System.out.println(Arrays.toString(bankList.toArray()));
        Assert.assertTrue(bankList.get(0).equals("주택도시기금1"));
    }

    @Test
    public void findDistinctByYearAndInstituteCode_test() {
        //given
        bankService.saveData();

        //when
        List<Object[]> list =  bankRepository.findDistinctByYearAndInstituteCode();

        List<BankListDto> banksListDtoList
                = list.stream().map(dto -> new BankListDto(
                (int)dto[0],
                (String)dto[1],
                (String)dto[2],
                ((Long)dto[3]).intValue()
                )).collect(Collectors.toList());

        // then
        BankListDto bankListDto = banksListDtoList.get(0);
        Assert.assertTrue(bankListDto.getYear() == 2005);

    }

    @Test
    public void findDistinctByYear_test() {
        //given
        bankService.saveData();

        //when
        List<Integer[]> list = bankRepository.findDistinctByYear();

        //then
        Integer[] integers = list.get(0);
        Assert.assertTrue(integers[0]==2005);
    }

    @Test
    public void findDistinctTopByYear_test() {
        //given
        bankService.saveData();

        //when
        int year = 2005;
        Map<Integer, String> bank = bankRepositorySupport.findLargestFundsBankByYear(year);

        for(Integer key : bank.keySet()) {
            System.out.println(key + " : " + bank.get(key));
        }
//        BankTopRankedDto topRankedDto = new BankTopRankedDto((int)bank.get("year"),
//                (String)bank.get("instituteName"), ((BigInteger)bank.get("funds")).intValue());
        //then
//        Assert.assertTrue(topRankedDto.getInstituteName().equals("주택도시기금1)(억원)"));
//        Assert.assertTrue(topRankedDto.getFunds()==20789);
    }



    @Test
    public void 외환은행_min_max_test() {
        //given
        bankService.saveData();

        Map<Integer, Integer> results = bankRepositorySupport.findForeignBankMinMax();
        for(int i : results.keySet()) {
            System.out.println(i+" : " + results.get(i));
        }
    }
}
