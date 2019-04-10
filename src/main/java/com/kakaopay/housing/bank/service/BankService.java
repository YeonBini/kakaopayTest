package com.kakaopay.housing.bank.service;

import com.google.gson.JsonArray;
import com.kakaopay.housing.bank.domain.*;
import com.kakaopay.housing.bank.repository.BankRepository;
import com.kakaopay.housing.bank.repository.BankRepositorySupport;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BankService {

    private BankRepository bankRepository;

    private BankRepositorySupport bankRepositorySupport;

    final String [] INSTITUTE_NAME_ARR = {"주택도시기금1","국민은행","우리은행","신한은행",
            "한국시티은행","하나은행","농협은행/수협은행","외환은행","기타은행"};

    public JSONObject saveData() {
        JSONObject jsonObject = new JSONObject();
        if(bankRepository.findAll().size() > 0 ) {
            jsonObject.put("result", "Already Executed");
        } else {
            List<Bank> bankList = readCsvData();
            bankRepository.saveAll(bankList);
            jsonObject.put("result", bankList);
        }

        return jsonObject;
    }

    public JSONObject bankList() {
        JSONObject jsonObject = new JSONObject();

        List<String> bankList = bankRepositorySupport.findBankList();
        jsonObject.put("result", bankList);

        return jsonObject;
    }

    public JSONObject bankFundsInfo() {
        JSONObject jsonObject = new JSONObject();

        List<Map<String, Object>> data = makeBankInfo();

        jsonObject.put("name", "주택금융 공급현황");
        jsonObject.put("data", data.toArray());
        return jsonObject;
    }

    public JSONObject largestFundsBank(String sYear) {
        JSONObject jsonObject = new JSONObject();
        int year = Integer.parseInt(sYear);

        Map<Integer, String> bank = bankRepositorySupport.findLargestFundsBankByYear(year);

        jsonObject.put("year", year);
        jsonObject.put("bank", bank.get(year));
        return jsonObject;
    }

    public JSONObject foreignBankInfo() {
        JSONObject jsonObject = new JSONObject();
        Map<Integer, Integer> foreignBankMap = bankRepositorySupport.findForeignBankMinMax();

        JSONArray jsonArray = findMinMax(foreignBankMap);
        jsonObject.put("bank", "외환은행");
        jsonObject.put("support_amount", jsonArray);
        return jsonObject;
    }

    private JSONArray findMinMax(Map<Integer, Integer> foreignBankMap) {
        JSONArray jsonArray = new JSONArray();

        // min, max 초기화
        int maxYear =0, minYear=0;
        int maxFunds = 0, minFunds = Integer.MAX_VALUE;

        for(int year : foreignBankMap.keySet()) {
            int funds = foreignBankMap.get(year);

            //최소 금액
            if(minFunds > funds) {
                minFunds = funds;
                minYear = year;
            }
            //최대 금액
            if(maxFunds < funds) {
                maxFunds = funds;
                maxYear = year;
            }
        }

        // 결과값
        Map<String, Integer> minResult = new HashMap<>();
        Map<String, Integer> maxResult = new HashMap<>();
        minResult.put("year", minYear);
        minResult.put("amount", minFunds);
        maxResult.put("year", maxYear);
        maxResult.put("amount", maxFunds);

        jsonArray.add(minResult);
        jsonArray.add(maxResult);
        return jsonArray;

    }

    private List<Map<String, Object>> makeBankInfo() {
        List<Map<String, Object>> data = new ArrayList<>();
        List<Integer[]> bankInfoByYear = bankRepository.findDistinctByYear();
        for(Integer[] it : bankInfoByYear) {
            Map<String, Object> map = new HashMap<>();
            map.put("year", it[0]);
            map.put("total_amount", it[1]);
            data.add(map);
        }

        List<Object[]> list =  bankRepository.findDistinctByYearAndInstituteCode();
        List<BankListDto> banksListDtoList
                = list.stream().map(dto -> new BankListDto(
                (int)dto[0],
                (String)dto[1],
                (String)dto[2],
                ((Long)dto[3]).intValue()
        )).collect(Collectors.toList());


        for(BankListDto bld : banksListDtoList) {
            int year = bld.getYear();
            for(int i=0; i<data.size(); i++) {
                if(data.get(i).get("year").equals(year)) {
                    if(data.get(i).get("detail_amount") == null) {
                        Map<String, Integer> tMap = new HashMap<>();
                        tMap.put(bld.getInstituteName(), bld.getFunds());
                        data.get(i).put("detail_amount", tMap);
                    } else{
                        Map<String, Integer> tMap = (HashMap)data.get(i).get("detail_amount");
                        tMap.put(bld.getInstituteName(), bld.getFunds());
                    }
                    break;
                }
            }
        }
        return data;
    }

    private List<Bank> readCsvData() {
        List<Bank> bankList = new ArrayList<>();
        BufferedReader br;

        try {
            ClassPathResource resource = new ClassPathResource("housing_data.csv");
            br = Files.newBufferedReader(Paths.get(resource.getURI()));
            Charset.forName("UTF-8");
            String tmpLine;
            String [] tmpArr;

            // pass first line
            br.readLine();

            // save from second line
            while((tmpLine = br.readLine()) != null) {
                tmpArr = tmpLine.split(",");
                int year = Integer.parseInt(tmpArr[0]);
                int month = Integer.parseInt(tmpArr[1]);
                int length = INSTITUTE_NAME_ARR.length;

                for(int i=0; i<length; i++) {
                    bankList.add(Bank.builder()
                            .year(year)
                            .month(month)
                            .instituteName(INSTITUTE_NAME_ARR[i])
                            .instituteCode("B"+String.format("%03d", i))
                            .funds(Integer.parseInt(tmpArr[i+2]))
                            .build());

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return bankList;
    }
}
