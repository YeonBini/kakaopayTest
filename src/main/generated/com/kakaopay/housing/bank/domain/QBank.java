package com.kakaopay.housing.bank.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBank is a Querydsl query type for Bank
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBank extends EntityPathBase<Bank> {

    private static final long serialVersionUID = -975453663L;

    public static final QBank bank = new QBank("bank");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Integer> funds = createNumber("funds", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath instituteCode = createString("instituteCode");

    public final StringPath instituteName = createString("instituteName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> month = createNumber("month", Integer.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QBank(String variable) {
        super(Bank.class, forVariable(variable));
    }

    public QBank(Path<? extends Bank> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBank(PathMetadata metadata) {
        super(Bank.class, metadata);
    }

}

