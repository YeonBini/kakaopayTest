package com.kakaopay.housing.bank.controller;

import com.kakaopay.housing.bank.domain.Banks;
import com.kakaopay.housing.bank.domain.BanksSaveRequestDto;
import com.kakaopay.housing.bank.service.BanksService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankController {

    private BanksService banksService;

    @GetMapping("/banks/hello")
    public String hello() {
        return "hello world";
    }

    @PostMapping("/banks/init")
    public List<Banks> init() {
        return banksService.saveData();
    }

    @PostMapping("/banks/save")
    public Banks save(@RequestBody BanksSaveRequestDto dto) {
        return banksService.save(dto);
    }
}
