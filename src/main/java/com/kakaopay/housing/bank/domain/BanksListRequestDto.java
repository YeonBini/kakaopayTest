package com.kakaopay.housing.bank.domain;

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

    public BanksListRequestDto(Banks entity) {
        this.year = entity.getYear();
        this.housingFund = entity.getHousingFund();
        this.kb = entity.getKb();
        this.woori = entity.getWoori();
        this.shinhan = entity.getShinhan();
        this.city = entity.getCity();
        this.hana = entity.getHana();
        this.cooperativesBank = entity.getCooperativesBank();
        this.foreignExchange = entity.getForeignExchange();
        this.etc = entity.getEtc();
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
