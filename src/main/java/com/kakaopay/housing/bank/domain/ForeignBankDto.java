package com.kakaopay.housing.bank.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ForeignBankDto {

    private int year;

    private int amount;

    public ForeignBankDto(int year, int amount) {
        this.year = year;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ForeignBankDto{" +
                "year=" + year +
                ", amount=" + amount +
                '}';
    }
}
