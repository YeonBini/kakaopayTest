package com.kakaopay.housing.bank.repository;

import com.kakaopay.housing.bank.domain.Bank;
import com.kakaopay.housing.bank.domain.BankTopRankedDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query("SELECT b.instituteName " +
            "FROM Bank b " +
            "GROUP BY b.instituteCode " +
            "ORDER BY b.instituteCode")
    List<String> findDistinctByInstituteCode();

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

    @Query(nativeQuery = true, value = "select year as year, " +
            "institute_name as instituteName, " +
            "SUM(funds) as funds " +
            "from bank where year= :year " +
            "group by year, institute_name " +
            "ORDER by funds desc " +
            "limit 1")
    Map<String, Object> findDistinctTopByYearAndInstituteAndInstituteName(@Param("year") int year);

    @Query(nativeQuery = true,
            value = "SELECT year as year, " +
            "ROUND(AVG(CAST(FUNDS AS FLOAT)), 0) as amount " +
                    "FROM BANK " +
                    "WHERE INSTITUTE_CODE = 'B007' " +
                    "GROUP BY YEAR " +
                    "ORDER BY YEAR ")
    List<Object[]> findByInstituteCodeForeignBankGroupByYear();
}
