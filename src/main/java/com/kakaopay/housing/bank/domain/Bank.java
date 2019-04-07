package com.kakaopay.housing.bank.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Bank extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int month;

    private String instituteName;

    private String instituteCode;

    private int funds;

    @Builder
    public Bank(int year, int month, String instituteName, String instituteCode, int funds) {
        this.year = year;
        this.month = month;
        this.instituteName = instituteName;
        this.instituteCode = instituteCode;
        this.funds = funds;
    }
}
