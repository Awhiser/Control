package com.sisi.control.repository;

import com.sisi.control.model.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbastractRepository<UserInfo> {

}
