package com.kakaopay.housing.banks.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBanks is a Querydsl query type for Banks
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBanks extends EntityPathBase<Banks> {

    private static final long serialVersionUID = 64631057L;

    public static final QBanks banks = new QBanks("banks");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final NumberPath<Integer> city = createNumber("city", Integer.class);

    public final NumberPath<Integer> cooperativesBank = createNumber("cooperativesBank", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Integer> etc = createNumber("etc", Integer.class);

    public final NumberPath<Integer> foreignExchange = createNumber("foreignExchange", Integer.class);

    public final NumberPath<Integer> hana = createNumber("hana", Integer.class);

    public final NumberPath<Integer> housingFund = createNumber("housingFund", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> kb = createNumber("kb", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> month = createNumber("month", Integer.class);

    public final NumberPath<Integer> shinhan = createNumber("shinhan", Integer.class);

    public final NumberPath<Integer> woori = createNumber("woori", Integer.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QBanks(String variable) {
        super(Banks.class, forVariable(variable));
    }

    public QBanks(Path<? extends Banks> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBanks(PathMetadata metadata) {
        super(Banks.class, metadata);
    }

}

