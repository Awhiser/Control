package com.sisi.control.repository.impl;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.AbstractEntity;
import com.sisi.control.model.AbstractSearch;
import com.sisi.control.model.PageView;
import com.sisi.control.model.user.UserInfo;
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
        return repo.findAll(specification.and(getTenantIdAndIsDelete(0)));
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
        String tenantId = ContextHolder.getContext().getTenantId();

        Specification<Entity> specification = new Specification<Entity>() {
            @Override
            public Predicate toPredicate(Root<Entity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<>();
                if (ids.size() == 1) {
                    predicates.add(builder.equal(root.get("id"), ids.get(0)));
                }else{
                    CriteriaBuilder.In<Object> in = builder.in(root.get("id"));
                    for (String id : ids) {
                        in.value(id);
                    }
                    predicates.add(in);
                }
                predicates.add(builder.equal(root.get("tenantId"), tenantId));
                predicates.add(builder.equal(root.get("isDelete"), false));

                Predicate[] array = new Predicate[predicates.size()];
                predicates.toArray(array);
                return builder.and(array);
            }
        };
        return specification;
    }

    public Specification<Entity> getTenantIdAndIsDelete(int isDelete){
        Specification<Entity> specification = new Specification<Entity>() {
            @Override
            public Predicate toPredicate(Root<Entity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate predicate02 = builder.equal(root.get("tenantId"),  ContextHolder.getContext().getTenantId());

                if(isDelete == 1){
                    Predicate predicate03 = builder.equal(root.get("isDelete"), true);
                    return builder.and(predicate02, predicate03);
                }else if(isDelete == -1){
                    Predicate predicate03 = builder.equal(root.get("isDelete"), false);
                    return builder.and(predicate02, predicate03);
                }
                return builder.and(predicate02);
            }
        };
        return specification;
    }

    public Page<Entity> findByPage(Specification specification,PageRequest pageRequest){
        var page =repo.findAll(specification.and(getTenantIdAndIsDelete(-1)),pageRequest);
        return page;
    }

    public Page<Entity> findByPageWithDel(Specification specification,PageRequest pageRequest){
        var page = repo.findAll(specification.and(getTenantIdAndIsDelete(0)),pageRequest);
        return page;
    }

    public Entity save(Entity entity){
        return  repo.save(entity);
    }

    public void saveAll(List<Entity> entities){
          repo.saveAll(entities);
    }
}
