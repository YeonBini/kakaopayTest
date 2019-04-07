package com.kakaopay.housing.repository;

import com.kakaopay.housing.bank.domain.Bank;
import com.kakaopay.housing.bank.domain.BankListDto;
import com.kakaopay.housing.bank.domain.BankTopRankedDto;
import com.kakaopay.housing.bank.domain.ForeignBankDto;
import com.kakaopay.housing.bank.repository.BankRepository;
import com.kakaopay.housing.bank.service.BankService;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.math.BigInteger;
import java.time.LocalDateTime;
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
        List<String> bankList = bankRepository.findDistinctByInstituteCode();

        //then
        Assert.assertTrue(bankList.get(0).equals("주택도시기금1)(억원)"));
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
        Map<String, Object> bank = bankRepository.findDistinctTopByYearAndInstituteAndInstituteName(year);

        BankTopRankedDto topRankedDto = new BankTopRankedDto((int)bank.get("year"),
                (String)bank.get("instituteName"), ((BigInteger)bank.get("funds")).intValue());
        //then
        Assert.assertTrue(topRankedDto.getInstituteName().equals("주택도시기금1)(억원)"));
        Assert.assertTrue(topRankedDto.getFunds()==20789);
    }

    @Test
    public void findByInstituteCodeForeignBankGroupByYear_test() {
        //given
        bankService.saveData();

        //when
        List<Object[]> list = bankRepository.findByInstituteCodeForeignBankGroupByYear();

        List<ForeignBankDto> foreignBankDtos = list.stream().map(dto -> new ForeignBankDto(
                (int)dto[0],
                ((Double)dto[1]).intValue()
        )).collect(Collectors.toList());

        //then
        Assert.assertTrue(foreignBankDtos.get(0).getAmount() ==144);
    }
}
