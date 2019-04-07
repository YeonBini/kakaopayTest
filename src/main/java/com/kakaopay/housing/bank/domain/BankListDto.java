package com.kakaopay.housing.bank.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BankListDto {

    private int year;

    private String instituteName;

    private String instituteCode;

    private int funds;

    public BankListDto(int year, String instituteName, String instituteCode, int funds) {
        this.year = year;
        this.instituteName = instituteName;
        this.instituteCode = instituteCode;
        this.funds = funds;
    }

    @Override
    public String toString() {
        return "BankListDto{" +
                "year=" + year +
                ", instituteName='" + instituteName + '\'' +
                ", instituteCode='" + instituteCode + '\'' +
                ", funds=" + funds +
                '}';
    }
}
