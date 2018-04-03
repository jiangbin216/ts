package cn.ts.core.mybatis;

import cn.ts.core.mybatis.mapper.CoreMapper;
import cn.ts.core.mybatis.pagehelper.Paging;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 基于 Mybatis Mapper 的 Service 接口实现类
 *
 * @author Created by YL on 2017/6/14.
 */
public abstract class BaseServiceImpl<Record, Example> implements BaseService<Record, Example> {
    @Autowired
    private CoreMapper<Record> coreMapper;

    /*------------------------------------- 基础接口 -------------------------------------*/

    @Override
    public List<Record> select(Record record) {
        return coreMapper.select(record);
    }

    @Override
    public Record selectByPrimaryKey(Object key) {
        return coreMapper.selectByPrimaryKey(key);
    }

    @Override
    public List<Record> selectAll() {
        return coreMapper.selectAll();
    }

    @Override
    public Record selectOne(Record record) {
        return coreMapper.selectOne(record);
    }

    @Override
    public int selectCount(Record record) {
        return coreMapper.selectCount(record);
    }

    @Override
    public int insert(Record record) {
        return coreMapper.insert(record);
    }

    @Override
    public int insertSelective(Record record) {
        return coreMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKey(Record record) {
        return coreMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Record record) {
        return coreMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(Record record) {
        return coreMapper.delete(record);
    }

    @Override
    public int deleteByPrimaryKey(Object key) {
        return coreMapper.deleteByPrimaryKey(key);
    }

    /*------------------------------------- Example方法 -------------------------------------*/

    @Override
    public List<Record> selectByExample(Example example) {
        return coreMapper.selectByExample(example);
    }

    @Override
    public int selectCountByExample(Example example) {
        return coreMapper.selectCountByExample(example);
    }

    @Override
    public int updateByExample(@Param("record") Record record, @Param("example") Example example) {
        return coreMapper.updateByExample(record, example);
    }

    @Override
    public int updateByExampleSelective(@Param("record") Record record, @Param("example") Example example) {
        return coreMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int deleteByExample(Example example) {
        return coreMapper.deleteByExample(example);
    }

    @Override
    public Paging<Record> select(Record record, int page, int size) {
        return this.select(record, page, size, true);
    }

    @Override
    public Paging<Record> select(Record record, int page, int size, boolean count) {
        PageHelper.startPage(page, size, count);
        Page<Record> p = (Page<Record>) this.select(record);
        return new Paging<>(p);
    }

    @Override
    public Paging<Record> selectByExample(Example example, int page, int size) {
        return this.selectByExample(example, page, size, true);
    }

    @Override
    public Paging<Record> selectByExample(Example example, int page, int size, boolean count) {
        PageHelper.startPage(page, size, count);
        Page<Record> p = (Page<Record>) this.selectByExample(example);
        return new Paging<>(p);
    }
}
