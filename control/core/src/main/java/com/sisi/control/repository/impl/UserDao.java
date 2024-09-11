package com.sisi.control.repository.impl;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.PageResult;
import com.sisi.control.model.user.UserInfo;
import com.sisi.control.model.user.UserInfoDto;
import com.sisi.control.model.user.UserSearchParam;
import com.sisi.control.repository.UserRepository;
import com.sisi.control.utils.jpatool.JPACondition;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class UserDao extends AbstractDao<UserInfo,UserRepository> {
    public UserDao(UserRepository userRepository) {
        super(userRepository);
    }


    public UserInfoDto getUserByUserName(String userName){
        Specification<UserInfo> sp = JPACondition.<UserInfo>builder().eq(UserInfo::getName, userName).build();
        var user = findOneBySpecification(sp);
        return user == null ? null : user.toDto();
    }
    public UserInfoDto loginUserByUserNameAndPassword(String userName,String passWord){
        Specification<UserInfo> sp = JPACondition.<UserInfo>builder().eq(UserInfo::getName, userName).eq(UserInfo::getPassword, passWord).build();
        var user = findOneBySpecification(sp);
        return  user == null ? null : new UserInfoDto(user);
    }



    public PageResult<UserInfoDto> getUserList(UserSearchParam param){
        var jpaCondition =  new JPACondition<UserInfo>();
        if(StringUtils.hasText(param.getName())){
            jpaCondition.like(UserInfo::getName, "%" + param.getName()+ "%" );
        }
        if(StringUtils.hasText(param.getDisplayName())){
            jpaCondition.like(UserInfo::getDisplayName, "%" + param.getDisplayName()+ "%" );
        }
        var res = findByPage(jpaCondition.build(),param);

        PageResult<UserInfoDto> pageResult = new PageResult<>(res);
        pageResult.setDataList(res.stream().map(i->new UserInfoDto(i)).toList());
        return pageResult;
    }

    public List<UserInfoDto> getByIds(List<String> ids) {
        var res = findByIds(ids);
        return res.stream().map(i->new UserInfoDto(i)).toList();
    }

    public UserInfoDto getById(String id) {
        var user = findById(id);
        return  user == null ? null : user.toDto();
    }

    public void saveAll(List<UserInfo> userInfoList) {
        saveDBAll(userInfoList);
    }

    public UserInfoDto save(UserInfo userInfo) {
        var res = saveDB(userInfo);
        return res.toDto();
    }

    @Transactional
    public void updateUserInfo(UserInfo userInfo){
        StringBuilder updateSql = new StringBuilder("UPDATE userinfo SET");
        updateSql.append(" displayname = '"+userInfo.getDisplayName()+"'");
        updateSql.append(" , mail = '"+userInfo.getMail()+"'");
        updateSql.append(" Where id = '"+userInfo.getId()+"'");
        updateSql.append(" and tenantid = '"+ ContextHolder.getContext().tenantId+"'");
        updateSql.append(" and isdelete = false");
        var res = executeUpdateSql(updateSql.toString());
        System.out.println(res);
    }

    @Transactional
    public void testSQL(){
        String sql  = "UPDATE userinfo SET name = 'password123' WHERE id = '1'";
        executeUpdateSql(sql);
        System.out.println("OKK");
    }


}
