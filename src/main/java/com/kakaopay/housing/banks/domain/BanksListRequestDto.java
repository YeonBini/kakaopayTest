package com.kakaopay.housing.banks.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class BanksListRequestDto {
    private int year;

    private int housingFund;

    private int kb;

    private int woori;

    private int shinhan;

    private int city;

    private int hana;

    private int cooperativesBank;

    private int foreignExchange;

    private int etc;

//    public BanksListRequestDto(Banks entity) {
//        this.year = entity.getYear();
//        this.housingFund = entity.getHousingFund();
//        this.kb = entity.getKb();
//        this.woori = entity.getWoori();
//        this.shinhan = entity.getShinhan();
//        this.city = entity.getCity();
//        this.hana = entity.getHana();
//        this.cooperativesBank = entity.getCooperativesBank();
//        this.foreignExchange = entity.getForeignExchange();
//        this.etc = entity.getEtc();
//    }


    public BanksListRequestDto(int year, int housingFund, int kb, int woori, int shinhan, int city, int hana, int cooperativesBank, int foreignExchange, int etc) {
        this.year = year;
        this.housingFund = housingFund;
        this.kb = kb;
        this.woori = woori;
        this.shinhan = shinhan;
        this.city = city;
        this.hana = hana;
        this.cooperativesBank = cooperativesBank;
        this.foreignExchange = foreignExchange;
        this.etc = etc;
    }

    public Banks toEntity() {
        return Banks.builder()
                .year(year)
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

    @Override
    public String toString() {
        return "BanksListRequestDto{" +
                "year=" + year +
                ", housingFund=" + housingFund +
                ", kb=" + kb +
                ", woori=" + woori +
                ", shinhan=" + shinhan +
                ", city=" + city +
                ", hana=" + hana +
                ", cooperativesBank=" + cooperativesBank +
                ", foreignExchange=" + foreignExchange +
                ", etc=" + etc +
                '}';
    }
}
