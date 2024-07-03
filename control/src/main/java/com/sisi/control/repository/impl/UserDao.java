package com.sisi.control.repository.impl;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.task.Task;
import com.sisi.control.model.task.TaskSearchParam;
import com.sisi.control.model.user.UserInfo;
import com.sisi.control.model.user.UserSearchParam;
import com.sisi.control.repository.UserRepository;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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


    public Page<UserInfo> getUserList(UserSearchParam param){
        Specification<Task> sp = (root,query,builder) ->{
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.hasText(param.getId())){
                Predicate predicate = builder.equal(root.get("id"), param.getId());
                predicates.add(predicate);
            }
            if(StringUtils.hasText(param.getName())){
                var nameP =builder.like(root.get("name"), "%" + param.getName()+ "%" );
                var displayNameP = builder.like(root.get("displayName"), "%" + param.getName()+ "%" );
                predicates.add(builder.or(nameP , displayNameP))  ;
            }
            if(StringUtils.hasText(param.getDisplayName())){
                predicates.add(builder.like(root.get("displayName"), "%" + param.getDisplayName()+ "%" ));
            }
            Predicate[] arr = new Predicate[predicates.size()];
            return builder.and( predicates.toArray(arr) );
        };

        PageRequest pageRequest = PageRequest.of(param.getPageIndex(), param.getPageSize());

        return findByPage(sp,pageRequest);
    }



//
//    private UserRepository repository;
//
//    @Autowired
//    public UserRepositoryImpl(UserRepository repository) {
//       this.repository = (repository);
//    }


}
