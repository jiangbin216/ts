package cn.ts.core.spring.data;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * BaseRepository 接口的实现
 * <p>
 * 继承 SimpleJpaRepository，SimpleJpaRepository 已经实现了 JpaRepository 中的方法。
 * BaseRepository 中自定义的方法要我们自己实现
 * </p>
 *
 * @author Created by YL on 2017/7/23.
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements
        BaseRepository<T, ID> {

    private final JpaEntityInformation<T, ?> entityInformation;

    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager), entityManager);
    }

    @Override
    public List<Map<String, Object>> listBySQL(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        // 将Object[] 转换为 Map 集合
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    @Override
    public List<Map<String, Object>> listBySQL(String sql, Object[] vals) {
        Query query = entityManager.createNativeQuery(sql);
        // 将Object[] 转换为 Map 集合
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        // 处理参数
        if (vals != null) {
            for (int i = 0; i < vals.length; i++) {
                // 从 1 开始
                query.setParameter(i + 1, vals[i]);
            }
        }
        return query.getResultList();
    }

    @Override
    public Page<Map<String, Object>> listBySQL(String sql, Object[] vals, int page, int size) {
        Query query = entityManager.createNativeQuery(sql);
        // 将Object[] 转换为 Map 集合
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        // 处理参数
        if (vals != null) {
            for (int i = 0; i < vals.length; i++) {
                query.setParameter(i + 1, vals[i]);
            }
        }
        if (page <= 0 || size <= 0) {
            return new PageImpl(query.getResultList());
        }
        // 使用这 setFirstResult 和 setMaxResults 进行分页屏蔽了底层数据库的差异性
        query.setFirstResult(page);
        query.setMaxResults(size);

        // Long total = executeCountQuery(this.getCountQuery(spec, domainClass));
        // List content = total.longValue() > (long) pageable.getOffset() ? query.getResultList() : Collections
        //         .emptyList();
        // return new PageImpl(content, pageable, total.longValue());
        return null;
    }

    // @Override
    // public int updateByPrimaryKeySelective(T t) {
    //     Class<T> clazz = (Class<T>) t.getClass();
    //     CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    //     CriteriaUpdate<T> update = builder.createCriteriaUpdate(clazz);
    //     Root<T> root = update.from(clazz);
    //     Field[] fields = t.getClass().getDeclaredFields();
    //     try {
    //         for (Field field : fields) {
    //             String name = field.getName();
    //             boolean flag = field.isAnnotationPresent(Id.class);
    //             String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
    //             Method method = t.getClass().getDeclaredMethod(methodName);
    //             Object obj = method.invoke(t);
    //             if (obj != null) {
    //                 if (flag) {
    //                     Predicate predicate = builder.equal(root.get(name), obj);
    //                     update.where(predicate);
    //                 } else {
    //                     System.out.println(name + " ---> " + root.get(name) + ": " + obj);
    //                     update.set(root.get(name), obj);
    //                 }
    //             }
    //         }
    //         Query query = entityManager.createQuery(update);
    //         return query.executeUpdate();
    //     } catch (Exception e) {
    //         throw new RuntimeException(e.getMessage(), e);
    //     }
    // }
}
