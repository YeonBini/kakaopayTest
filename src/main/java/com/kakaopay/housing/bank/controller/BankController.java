package com.kakaopay.housing.bank.controller;

import com.kakaopay.housing.bank.service.BankService;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/bank")
public class BankController {

    BankService bankService;

    @GetMapping("/hello")
    public String helloworld() {
        return "hello world";
    }


    @PostMapping("/init")
    public JSONObject init() {
        return bankService.saveData();
    }

    @GetMapping("/list")
    public JSONObject bankList() {
        return bankService.bankList();
    }

    @GetMapping("/info")
    public JSONObject bankInfo() {
        return bankService.bankFundsInfo();
    }

    @GetMapping("/largest")
    public JSONObject largestFundsBank(@RequestParam String year) {
        return bankService.largestFundsBank(year);
    }

    @GetMapping("/foreignBank")
    public JSONObject foreignBankInfo() {
        return bankService.foreignBankInfo();
    }

}
