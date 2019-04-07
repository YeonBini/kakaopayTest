package com.kakaopay.housing.banks.service;

import com.kakaopay.housing.banks.domain.Banks;
import com.kakaopay.housing.banks.domain.BanksListRequestDto;
import com.kakaopay.housing.banks.repository.BanksRepository;
import com.kakaopay.housing.banks.domain.BanksSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class BanksService {

    private BanksRepository banksRepository;


    @Transactional
    public Banks save(BanksSaveRequestDto dto) {
        Banks banks = banksRepository.findByYearAndMonth(dto.getYear(), dto.getMonth());

        if(banks != null) {
            banks.setHousingFund(dto.getHousingFund());
            banks.setKb(dto.getKb());
            banks.setWoori(dto.getWoori());
            banks.setShinhan(dto.getShinhan());
            banks.setCity(dto.getCity());
            banks.setHana(dto.getHana());
            banks.setCooperativesBank(dto.getCooperativesBank());
            banks.setForeignExchange(dto.getForeignExchange());
            banks.setEtc(dto.getEtc());
        } else {
            banks = banksRepository.save(dto.toEntity());
        }

        return banks;
    }

//    @Transactional
//    public List<Banks> saveData() {
//        int initialBankDataSize = banksRepository.findAll().size();
//
//
//        if(initialBankDataSize > 0) {
//            return null;
//        } else {
//            List<Banks> banksList = readCsvData();
//            banksRepository.saveAll(banksList);
//            return banksList;
//
//        }
//    }

    @Transactional(readOnly = true)
    public List<Object> banksDataByYear() {
        List<Object[]> banksList = banksRepository.findDistinctByYear();

        List<BanksListRequestDto> banksListRequestDtoList
                = banksList.stream().map(dto -> new BanksListRequestDto(
                (int)dto[0],
                ((Long)dto[1]).intValue(),
                ((Long)dto[2]).intValue(),
                ((Long)dto[3]).intValue(),
                ((Long)dto[4]).intValue(),
                ((Long)dto[5]).intValue(),
                ((Long)dto[6]).intValue(),
                ((Long)dto[7]).intValue(),
                ((Long)dto[8]).intValue(),
                ((Long)dto[9]).intValue()
        )).collect(Collectors.toList());



        return null;
    }



//    private List<Banks> readCsvData() {
//        List<Banks> banksList = new ArrayList<>();
//        BufferedReader br;
//
//        try {
//            ClassPathResource resource = new ClassPathResource("housing_data.csv");
//            br = Files.newBufferedReader(Paths.get(resource.getURI()));
//            Charset.forName("UTF-8");
//            String tmpLine;
//            String [] tmpArr;
//
//            // 첫 번째 행
//            br.readLine();
//
//            // 두 번째 행부터
//            while((tmpLine = br.readLine()) != null) {
//                tmpArr = tmpLine.split(",");
//                banksList.add(Banks.builder()
//                    .year(Integer.parseInt(tmpArr[0]))
//                    .month(Integer.parseInt(tmpArr[1]))
//                    .housingFund(Integer.parseInt(tmpArr[2]))
//                    .kb(Integer.parseInt(tmpArr[3]))
//                    .woori(Integer.parseInt(tmpArr[4]))
//                    .shinhan(Integer.parseInt(tmpArr[5]))
//                    .city(Integer.parseInt(tmpArr[6]))
//                    .hana(Integer.parseInt(tmpArr[7]))
//                    .cooperativesBank(Integer.parseInt(tmpArr[8]))
//                    .foreignExchange(Integer.parseInt(tmpArr[9]))
//                    .etc(Integer.parseInt(tmpArr[10]))
//                    .build());
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        return banksList;
//    }
}