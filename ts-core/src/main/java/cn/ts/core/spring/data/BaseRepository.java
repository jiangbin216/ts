package cn.ts.core.spring.data;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * spring data jpa：基础 repository 接口，继承了 JpaRepository 接口
 * <p>
 * NoRepositoryBean，表示该接口不会创建实例，由 spring data jpa 的基础组件会自动帮我们创建一个实例对象
 * </p>
 * <p>
 * <pre>
 *     Repository： 仅仅是一个标识，表明任何继承它的均为仓库接口类，方便Spring自动扫描识别
 *     CrudRepository： 继承Repository，实现了一组CRUD相关的方法
 *     PagingAndSortingRepository： 继承CrudRepository，实现了一组分页排序相关的方法
 *     JpaRepository： 继承PagingAndSortingRepository，实现一组JPA规范相关的方法。将返回类型 Iterable 转换成了 List
 *     JpaSpecificationExecutor： 比较特殊，不属于Repository体系，实现一组JPA Criteria查询相关的方法
 * </pre>
 *
 * @author Created by YL on 2017/7/23.
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    List<Map<String, Object>> listBySQL(String sql);

    List<Map<String, Object>> listBySQL(String sql, Object[] vals);

    Page<Map<String, Object>> listBySQL(String sql, Object[] vals, int page, int size);
}
