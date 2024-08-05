package com.sisi.control.repository.impl;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.task.Task;
import com.sisi.control.model.task.TaskSearchParam;
import com.sisi.control.model.user.UserInfo;
import com.sisi.control.model.user.UserSearchParam;
import com.sisi.control.repository.UserRepository;
import com.sisi.control.utils.jpatool.JPACondition;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
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
        Specification<UserInfo> sp = JPACondition.<UserInfo>builder().eq(UserInfo::getName, userName).build();
        return findOneBySpecification(sp);
    }


    public Page<UserInfo> getUserList(UserSearchParam param){
        var jpaCondition =  new JPACondition<UserInfo>();
        if(StringUtils.hasText(param.getName())){
            jpaCondition.like(UserInfo::getName, "%" + param.getName()+ "%" );
        }
        if(StringUtils.hasText(param.getDisplayName())){
            jpaCondition.like(UserInfo::getDisplayName, "%" + param.getDisplayName()+ "%" );
        }

        return findByPage(jpaCondition.build(),param);
    }

}
