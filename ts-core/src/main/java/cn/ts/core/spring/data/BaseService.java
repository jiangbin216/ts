package cn.ts.core.spring.data;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * spring data jpa：基础 Service 接口
 *
 * @author Created by YL on 2017/7/23.
 */
public interface BaseService<T, ID extends Serializable> {
    // -------------------------------------------------------------------------------
    // 默认的 CrudRepository 方法
    // -------------------------------------------------------------------------------

    /**
     * 保存单个实体
     * <pre>
     *     注意：
     *     1、如果使用了 @DynamicUpdate 注解，需要注意的是，当你select后，显式的把某些字段set为NULL，jpa会认为你修改了该字段，同样会生成到update语句中
     *     一般先 select 实体出来，再 save 的话，只会 update 该实体被修改的字段。
     *     否则会 update 所有表字段。
     *     添加该注解可以减少被 update 的字段，从而提高 update 的性能
     *     2、如果使用了 @DynamicInsert 注解，并且数据库配置了默认值，当 insert，并且new 实体时，该属性没有 set 值的话，会使用数据库默认值。
     *     否则会使用实体的值
     * </pre>
     *
     * @param var1 　实体对象
     * @param <S>
     * @return
     */
    <S extends T> S save(S var1);

    /**
     * 保存实体集合
     * <pre>
     *     注意：
     *     1、如果使用了 @DynamicUpdate 注解，一般先 select 实体出来，再 save 的话，只会 update 该实体被修改的字段。
     *     否则会 update 所有表字段。
     *     添加该注解可以减少被 update 的字段，从而提高 update 的性能
     *     2、如果使用了 @DynamicInsert 注解，并且数据库配置了默认值，当 insert，并且new 实体时，该属性没有 set 值的话，会使用数据库默认值。
     *     否则会使用实体的值
     * </pre>
     *
     * @param var1 　实体对象集合
     * @param <S>
     * @return
     */
    <S extends T> List<S> save(Iterable<S> var1);

    /**
     * 通过主键查询
     *
     * @param var1 　主键
     * @return
     */
    T findOne(ID var1);

    /**
     * 通过主键判断是否存在
     *
     * @param var1 　主键
     * @return
     */
    boolean exists(ID var1);

    /**
     * 查询所有（建议不用、慎用）
     *
     * @return
     */
    List<T> findAll();

    /**
     * 通过主键集合查询
     *
     * @param var1 　主键集合
     * @return
     */
    List<T> findAll(Iterable<ID> var1);

    /**
     * 查询总数
     *
     * @return
     */
    long count();

    /**
     * 通过主键删除
     *
     * @param var1 　主键
     */
    void delete(ID var1);

    /**
     * 通过实体对象删除记录
     *
     * @param var1 　实体对象
     */
    void delete(T var1);

    /**
     * 删除实体集合
     *
     * @param var1 　实体集合
     */
    void delete(Iterable<? extends T> var1);

    /**
     * 删除所有（建议不用、慎用）
     */
    void deleteAll();

    // -------------------------------------------------------------------------------
    // 默认的 PagingAndSortingRepository 方法
    // -------------------------------------------------------------------------------

    /**
     * 排序查询
     *
     * @param var1 　排序
     * @return
     */
    List<T> findAll(Sort var1);
    // Iterable<T> findAll(Sort var1);

    /**
     * 分页查询（含排序查询）
     *
     * @param var1 　分页参数
     * @return
     */
    Page<T> findAll(Pageable var1);

    // -------------------------------------------------------------------------------
    // 默认的 QueryByExampleExecutor 方法
    // -------------------------------------------------------------------------------

    /**
     * 条件查询
     *
     * @param var1 　条件
     * @param <S>
     * @return
     */
    <S extends T> S findOne(Example<S> var1);

    /**
     * 条件查询
     *
     * @param var1 　查询条件
     * @param <S>
     * @return
     */
    <S extends T> List<S> findAll(Example<S> var1);
    // <S extends T> Iterable<S> findAll(Example<S> var1);

    /**
     * 条件查询
     *
     * @param var1 　查询条件
     * @param var2 　排序
     * @param <S>
     * @return
     */
    <S extends T> List<S> findAll(Example<S> var1, Sort var2);
    // <S extends T> Iterable<S> findAll(Example<S> var1, Sort var2);

    /**
     * 条件查询，分页返回数据
     *
     * @param var1 　查询条件
     * @param var2 　分页参数
     * @param <S>
     * @return
     */
    <S extends T> Page<S> findAll(Example<S> var1, Pageable var2);

    /**
     * 条件查询统计记录数
     *
     * @param var1 　查询条件
     * @param <S>
     * @return
     */
    <S extends T> long count(Example<S> var1);

    /**
     * 通过条件判断数据是否存在
     *
     * @param var1 　条件
     * @param <S>
     * @return
     */
    <S extends T> boolean exists(Example<S> var1);

    // -------------------------------------------------------------------------------
    // 默认的 JpaSpecificationExecutor 方法
    // -------------------------------------------------------------------------------

    T findOne(Specification<T> var1);

    List<T> findAll(Specification<T> var1);

    Page<T> findAll(Specification<T> var1, Pageable var2);

    List<T> findAll(Specification<T> var1, Sort var2);

    long count(Specification<T> var1);

    // -------------------------------------------------------------------------------
    // 默认的 JpaRepository 方法
    // -------------------------------------------------------------------------------

    /**
     * 进行缓存域数据库同步
     */
    void flush();

    /**
     * 强制执行持久化
     *
     * @param var1 实体对象
     * @param <S>
     * @return
     */
    <S extends T> S saveAndFlush(S var1);

    /**
     * 删除一个实体集合
     *
     * @param var1 　实体对象集合
     */
    void deleteInBatch(Iterable<T> var1);

    void deleteAllInBatch();

    /**
     * 通过主键获取数据
     *
     * @param var1 　主键
     * @return
     */
    T getOne(ID var1);

    // -------------------------------------------------------------------------------
    // 自定义的方法
    // -------------------------------------------------------------------------------

    List<Map<String, Object>> listBySQL(String sql);

    List<Map<String, Object>> listBySQL(String sql, Object[] vals);

    Page<Map<String, Object>> listBySQL(String sql, Object[] vals, int page, int size);
}
