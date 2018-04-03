package cn.ts.core.mybatis.mapper.common;

import cn.ts.core.mybatis.mapper.provider.MySelectByExampleProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 自定义通用 Mapper 接口, Example 查询
 *
 * @author Created by YL on 2017/7/17.
 */
public interface MySelectByExampleMapper<T> {
    /**
     * 根据Example条件进行查询
     *
     * @param example
     * @return
     */
    @SelectProvider(type = MySelectByExampleProvider.class, method = "dynamicSQL")
    List<T> selectByExample(Object example);

}
