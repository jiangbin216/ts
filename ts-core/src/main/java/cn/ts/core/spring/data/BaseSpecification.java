package cn.ts.core.spring.data;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 包装用于构建JPA动态查询时所需的对象
 *
 * @author Created by YL on 2017/7/28.
 */
public class BaseSpecification<T> implements Specification<T> {
    
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNull(root.get("")));
        predicates.add(builder.isNotNull(root.get("")));

        Predicate predicate = predicates.isEmpty() ? builder.isTrue(builder.literal(Boolean.valueOf(true))) :
                (predicates.size() == 1 ? predicates.iterator().next() : builder.and((Predicate[]) predicates.toArray
                        (new Predicate[predicates.size()])));
        return predicate;
        // return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
