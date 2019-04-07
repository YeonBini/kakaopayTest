package com.kakaopay.housing.bank.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BankTopRankedDto {
    private int year;

    private String instituteName;

    private int funds;

    public BankTopRankedDto(int year, String instituteName, int funds) {
        this.year = year;
        this.instituteName = instituteName;
        this.funds = funds;
    }

    @Override
    public String toString() {
        return "BankTopRankedDto{" +
                "year=" + year +
                ", instituteName='" + instituteName + '\'' +
                ", funds=" + funds +
                '}';
    }
}
