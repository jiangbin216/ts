package cn.ts.core.spring.data;

import lombok.Data;

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
@Data
public class BaseSpecificationWraper<T> {

    protected Root<T> root;

    protected CriteriaQuery<?> query;

    protected CriteriaBuilder builder;

    protected List<Predicate> predicates;

    public BaseSpecificationWraper() {
        if (predicates == null) {
            predicates = new ArrayList<>();
        }
    }

    public BaseSpecificationWraper(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate>
            predicates) {
        this.root = root;
        this.query = query;
        this.builder = builder;
        if (predicates == null) {
            predicates = new ArrayList<>();
        }
        this.predicates = predicates;
    }
}
