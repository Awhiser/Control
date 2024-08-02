package com.sisi.control.utils.jpatool;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JPACondition<T> {

    public List<Condition> conditions ;

    public JPACondition(){
        conditions = new ArrayList<>();
    }

    public static <T> JPACondition<T> builder() {
        return new JPACondition<T>();
    }

    public Specification<T> build(){
        if(CollectionUtils.isEmpty(conditions)) {
            return (root, query, builder) -> builder.and();
        }
        Specification sp = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for(var condition : conditions) {
                var field = condition.fieldFn == null ?  condition.field : JPAFieldConvert.convertToFieldName(condition.fieldFn);
                var type = condition.conditionType;
                if(type == ConditionType.eq){
                    predicates.add(builder.equal(root.get(field), condition.value))  ;
                }
                if(type == ConditionType.like){
                    predicates.add(builder.like(root.get("name"), (String) condition.value ));
                }
                if(type == ConditionType.in){
                    CriteriaBuilder.In<Object> in = builder.in(root.get(field));
                    for (var id : (List)condition.value) {
                        in.value(id);
                    }
                    predicates.add(in);
                }
            }
            Predicate[] predicateArray = new Predicate[predicates.size()];
            return builder.and( predicates.toArray(predicateArray) );
        };
        return sp;
    }

    public JPACondition<T> eq(String field, Object value){
        conditions.add(new Condition(ConditionType.eq,field,value));
        return this;
    }

    public JPACondition<T> eq(IJPAFieldGetter<T> field , Object value){
        conditions.add(new Condition(ConditionType.eq,field,value));
        return this;
    }

    public JPACondition<T> in(String field, List values){
        if(values.size() == 1){
            return eq(field,values.get(0));
        }
        conditions.add(new Condition(ConditionType.in,field,values));
        return this;
    }

    public JPACondition<T> like(String field, Object value){
        conditions.add(new Condition(ConditionType.like,field,value));
        return this;
    }
    public JPACondition<T> like(IJPAFieldGetter<T> field, Object value){
        conditions.add(new Condition(ConditionType.like,field,value));
        return this;
    }



    private class Condition {
        public ConditionType conditionType;
        public String field;
        public IJPAFieldGetter<T> fieldFn = null;
        public Object value;

        public Condition(){

        }

        public Condition( ConditionType conditionType,String field,Object value){
            this.conditionType = conditionType;
            this.field = field;
            this.value = value;
        }
        public Condition( ConditionType conditionType,IJPAFieldGetter<T> field,Object value){
            this.conditionType = conditionType;
            this.fieldFn = field;
            this.value = value;
        }
    }

    private enum ConditionType{
        eq,
        like,
        in
    }


}
