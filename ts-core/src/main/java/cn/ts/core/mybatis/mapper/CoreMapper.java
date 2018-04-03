package cn.ts.core.mybatis.mapper;

import cn.ts.core.mybatis.mapper.common.MySelectByExampleMapper;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.example.DeleteByExampleMapper;
import tk.mybatis.mapper.common.example.SelectCountByExampleMapper;
import tk.mybatis.mapper.common.example.UpdateByExampleMapper;
import tk.mybatis.mapper.common.example.UpdateByExampleSelectiveMapper;

/**
 * 根据 Mybatis Mapper 通用插件自定义接口
 * <p>
 * 注意：@MapperScan不能扫描到该接口，否则启动应用会报错
 * </p>
 *
 * @author Created by YL on 2017/7/4.
 */
public interface CoreMapper<Record> extends BaseMapper<Record>, SelectCountByExampleMapper<Record>,
        DeleteByExampleMapper<Record>, UpdateByExampleMapper<Record>, UpdateByExampleSelectiveMapper<Record>
        // , SelectByExampleMapper<Record>
        //    自定义的 selectByExample 查询方法
        , MySelectByExampleMapper<Record> {
}
