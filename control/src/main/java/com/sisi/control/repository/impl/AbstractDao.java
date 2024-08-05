package com.sisi.control.repository.impl;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.AbstractEntity;
import com.sisi.control.model.AbstractSearch;
import com.sisi.control.model.PageView;
import com.sisi.control.model.task.Task;
import com.sisi.control.model.user.UserInfo;
import com.sisi.control.utils.jpatool.JPACondition;
import com.sisi.control.utils.log.LogHelper;
import io.micrometer.common.KeyValues;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AbstractDao<Entity extends AbstractEntity, Repo extends JpaRepository<Entity, String> & JpaSpecificationExecutor<Entity>> {
    public Repo repo;

    public String tenantField = "tenantId";

    public AbstractDao(Repo repo) {
        this.repo = repo;
    }

    public Entity findById(String id) {
        return repo.findOne(getIdSpecification(id)).orElse(null);
    }

    public List<Entity> findAll() {
        return repo.findAll(getTenantIdAndIsDelete(-1));
    }

    public List<Entity> findByIds(List<String> ids){
        return  repo.findAll(getIdsSpecification(ids));
    }

    public Entity findOneBySpecification(Specification specification){
        var res = findBySpecification(specification);
        if(res.size() > 0){
            return res.get(0);
        }
        return null;
    }
    public Entity findOneBySpecificationWithDel(Specification specification){
        var res = findBySpecificationWithDel(specification);
        if(res.size() > 0){
            return res.get(0);
        }
        return null;
    }

    public List<Entity> findBySpecification(Specification specification){
        return repo.findAll(specification.and(getTenantIdAndIsDelete(-1)));
    }

    public List<Entity> findBySpecificationWithDel(Specification specification){
        return repo.findAll(specification.and(getTenantIdAndIsDelete(1)));
    }

    public void deleteById(String id) {
        var entity = findById(id);
        entity.setIsDelete(true);
        repo.save(entity);
    }


    public void deleteByIdWithRealRemove(String id) {
        repo.delete(getIdSpecification(id));
    }

    public void delete(Entity entity) {
        entity.setIsDelete(true);
        repo.save(entity);
    }

    public Specification<Entity> getIdSpecification(String id) {
        return getIdsSpecification(Arrays.asList(id));
    }

    public Specification<Entity> getIdsSpecification(List<String> ids) {
        JPACondition<Entity> jpaCondition = new JPACondition<>();
        if (ids.size() == 1) {
            jpaCondition.eq(Entity::getId, ids.get(0));
        } else {
            jpaCondition.in(Entity::getId, ids);
        }
        var specification = jpaCondition.build().and(getTenantIdAndIsDelete(-1));
        return specification;
    }

    public Specification<Entity> getTenantIdAndIsDelete(int isDelete){
        var sp = new JPACondition<Entity>()
                .eq(Entity::getTenantId,ContextHolder.getContext().tenantId)
                .eq(Entity::getIsDelete,isDelete == 1 ? true : false)
                .build();
        return sp;
    }

    public Page<Entity> findByPage(Specification specification,AbstractSearch params){
        if(!CollectionUtils.isEmpty(params.getIds())) {
            specification = specification.and(getIdsSpecification(params.getIds()));
        }
        var page =repo.findAll(specification.and(getTenantIdAndIsDelete(-1)),params.getPageRequest());
        return page;
    }

    public Page<Entity> findByPageWithDel(Specification specification,PageRequest pageRequest){
        var page = repo.findAll(specification.and(getTenantIdAndIsDelete(1)),pageRequest);
        return page;
    }

    public Entity save(Entity entity){
        entity.setTenantId(ContextHolder.getContext().getTenantId());
        entity.setIsDelete(false);
        return  repo.save(entity);
    }

    public void saveAll(List<Entity> entities){
        entities.forEach(entity -> {
            entity.setTenantId(ContextHolder.getContext().getTenantId());
            entity.setIsDelete(false);
        });
        repo.saveAll(entities);
    }



}
