package com.sisi.control.repository;

import com.sisi.control.model.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbastractRepository<T> extends JpaRepository<T,String>, JpaSpecificationExecutor<T> {
}
