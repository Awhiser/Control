package com.sisi.control.repository.impl;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.user.UserInfo;
import com.sisi.control.repository.UserRepository;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Repository
public class UserDao extends AbstractDao<UserInfo,UserRepository> {
    public UserDao(UserRepository userRepository) {
        super(userRepository);
    }


    public UserInfo getUserByUserName(String userName){

        Specification<UserInfo> specification = new Specification<UserInfo>() {
            @Override
            public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.and(builder.equal(root.get("name"), userName));
            }
        };

        return findOneBySpecification(specification);
    }



//
//    private UserRepository repository;
//
//    @Autowired
//    public UserRepositoryImpl(UserRepository repository) {
//       this.repository = (repository);
//    }


}
