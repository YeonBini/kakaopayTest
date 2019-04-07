package com.kakaopay.housing.banks.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BanksSaveRequestDto {
    private int year;

    private int month;

    private int housingFund;

    private int kb;

    private int woori;

    private int shinhan;

    private int city;

    private int hana;

    private int cooperativesBank;

    private int foreignExchange;

    private int etc;

    public Banks toEntity() {
        return Banks.builder()
                .year(year)
                .month(month)
                .housingFund(housingFund)
                .kb(kb)
                .woori(woori)
                .shinhan(shinhan)
                .city(city)
                .hana(hana)
                .cooperativesBank(cooperativesBank)
                .foreignExchange(foreignExchange)
                .etc(etc)
                .build();
    }
}
