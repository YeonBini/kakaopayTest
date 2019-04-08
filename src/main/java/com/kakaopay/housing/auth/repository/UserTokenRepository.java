package com.kakaopay.housing.auth.repository;

import com.kakaopay.housing.auth.domain.UserToken;
import org.springframework.data.repository.CrudRepository;

public interface UserTokenRepository extends CrudRepository<UserToken, Long> {

    UserToken findByUsername(String username);
}
