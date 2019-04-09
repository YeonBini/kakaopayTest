package com.kakaopay.housing.bank.repository;

import com.kakaopay.housing.bank.domain.Bank;
import com.kakaopay.housing.bank.domain.QBank;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BankRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public BankRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Bank.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Map<Integer, Integer> findForeignBankMinMax() {
        QBank qBank = QBank.bank;
        return jpaQueryFactory
                .from(qBank)
                .where(qBank.instituteCode.eq("B007").and(qBank.year.lt(2017)))
                .transform(GroupBy.groupBy(qBank.year).as(GroupBy.sum(qBank.funds)));
    }

    public List<String> findBankList() {
        QBank qBank = QBank.bank;
        return jpaQueryFactory
                .from(qBank)
                .select(qBank.instituteName)
                .distinct()
                .fetch();
    }

    public Map<Integer, String> findLargestFundsBankByYear(int year) {
        QBank qBank = QBank.bank;
        return jpaQueryFactory
                .from(qBank)
                .where(qBank.year.eq(year))
                .groupBy(qBank.instituteName)
                .orderBy(qBank.funds.sum().desc())
                .limit(1)
                .transform(GroupBy.groupBy(qBank.year).as(qBank.instituteName));
    }

}
