package com.kakaopay.housing.bank.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor()
public class Banks extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
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

    @Builder
    public Banks(int year, int month, int housingFund, int kb, int woori, int shinhan, int city, int hana, int cooperativesBank, int foreignExchange, int etc) {
        this.year = year;
        this.month = month;
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
}
