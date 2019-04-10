package com.kakaopay.housing.bank.repository;

import com.kakaopay.housing.bank.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query("SELECT b.year, " +
            "SUM (b.funds) " +
            "FROM Bank b " +
            "GROUP BY b.year " +
            "ORDER BY b.year")
    List<Integer[]> findDistinctByYear();

    @Query("SELECT b.year as year, " +
            "b.instituteName, " +
            "b.instituteCode, " +
            "SUM (b.funds) as funds " +
            "FROM  Bank b " +
            "GROUP BY b.year, b.instituteCode " +
            "ORDER BY b.year")
    List<Object[]> findDistinctByYearAndInstituteCode();

}
