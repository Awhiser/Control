package com.sisi.control.repository.impl;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.AbstractEntity;
import com.sisi.control.model.AbstractSearch;
import com.sisi.control.utils.jpatool.JPACondition;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class AbstractDao<Entity extends AbstractEntity, Repo extends JpaRepository<Entity, String> & JpaSpecificationExecutor<Entity>> {
    protected Repo repo;

    private String tenantField = "tenantId";

    @PersistenceContext
    EntityManager entityManager;

    protected AbstractDao(Repo repo) {
        this.repo = repo;
    }

    protected Entity findById(String id) {
        return repo.findOne(getIdSpecification(id)).orElse(null);
    }

    protected List<Entity> findAll() {
        return repo.findAll(getTenantIdAndIsDelete(-1));
    }

    protected List<Entity> findByIds(List<String> ids){
        return  repo.findAll(getIdsSpecification(ids));
    }

    protected Entity findOneBySpecification(Specification specification){
        var res = findBySpecification(specification);
        if(res.size() > 0){
            return res.get(0);
        }
        return null;
    }
    protected Entity findOneBySpecificationWithDel(Specification specification){
        var res = findBySpecificationWithDel(specification);
        if(res.size() > 0){
            return res.get(0);
        }
        return null;
    }
    //getTenantIdAndIsDelete 放前面 避免冲掉Sort
    protected List<Entity> findBySpecification(Specification specification){
        return repo.findAll(getTenantIdAndIsDelete(-1).and(specification) );
    }

    protected List<Entity> findBySpecificationWithDel(Specification specification){
        return repo.findAll(getTenantIdAndIsDelete(1).and(specification));
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

    protected Specification<Entity> getIdSpecification(String id) {
        return getIdsSpecification(Arrays.asList(id));
    }

    protected Specification<Entity> getIdsSpecification(List<String> ids) {
        JPACondition<Entity> jpaCondition = new JPACondition<>();
        if (ids.size() == 1) {
            jpaCondition.eq(Entity::getId, ids.get(0));
        } else {
            jpaCondition.in(Entity::getId, ids);
        }
        var specification = jpaCondition.build().and(getTenantIdAndIsDelete(-1));
        return specification;
    }

    protected Specification<Entity> getTenantIdAndIsDelete(int isDelete){
        var sp = new JPACondition<Entity>()
                .eq(Entity::getTenantId,ContextHolder.getContext().tenantId)
                .eq(Entity::getIsDelete,isDelete == 1 ? true : false)
                .build();
        return sp;
    }

    protected Page<Entity> findByPage(Specification specification,AbstractSearch params){
        if(!CollectionUtils.isEmpty(params.getIds())) {
            specification = specification.and(getIdsSpecification(params.getIds()));
        }
        var page =repo.findAll( getTenantIdAndIsDelete(-1).and(specification) ,params.getPageRequest());
        return page;
    }

    protected Page<Entity> findByPageWithDel(Specification specification,PageRequest pageRequest){
        var page = repo.findAll(getTenantIdAndIsDelete(1).and(specification ),pageRequest);
        return page;
    }

    protected Entity saveDB(Entity entity){
        entity.setTenantId(ContextHolder.getContext().getTenantId());
        entity.setIsDelete(false);
        entity.setUpdateTime(new Date());
        return  repo.save(entity);
    }

    protected void saveDBAll(List<Entity> entities){
        entities.forEach(entity -> {
            entity.setTenantId(ContextHolder.getContext().getTenantId());
            entity.setIsDelete(false);
            entity.setUpdateTime(new Date());
        });
        repo.saveAll(entities);
    }



    protected int executeUpdateSql(String sql){
       // entityManager.getTransaction().begin();
// 执行原生 SQL 更新语句
        Query query = entityManager.createNativeQuery(sql);
        int updatedCount = query.executeUpdate();

//        entityManager.getTransaction().commit();
//        entityManager.close();
        return updatedCount;
    }

}
