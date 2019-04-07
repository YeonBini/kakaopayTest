package com.kakaopay.housing.banks.repository;

import com.kakaopay.housing.banks.domain.Banks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BanksRepository extends JpaRepository<Banks, Long> {

    Banks findByYearAndMonth(int year, int month);

    @Query("SELECT b.year as year, " +
            "SUM (b.housingFund) as housingFund, " +
            "SUM (b.kb) as kb, " +
            "SUM (b.woori) as woori," +
            "SUM (b.shinhan) as shinhan, " +
            "SUM (b.city) as city, " +
            "SUM (b.hana) as hana, " +
            "SUM (b.cooperativesBank) as cooperativesBank, " +
            "SUM (b.foreignExchange) as foreignExchange, " +
            "SUM (b.etc) as etc " +
            "FROM Banks b " +
            "GROUP BY b.year " +
            "order by b.year asc")
    List<Object[]> findDistinctByYear();
}