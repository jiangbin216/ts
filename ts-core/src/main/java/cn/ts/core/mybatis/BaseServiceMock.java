package cn.ts.core.mybatis;

import cn.ts.core.mybatis.pagehelper.Paging;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 降级实现BaseService抽象类
 *
 * @author Created by YL on 2017/02/14.
 */
public class BaseServiceMock<Record, Example> implements BaseService<Record, Example> {
    private static final int R = -1;

    @Override
    public List<Record> select(Record record) {
        return null;
    }

    @Override
    public Record selectByPrimaryKey(Object key) {
        return null;
    }

    @Override
    public List<Record> selectAll() {
        return null;
    }

    @Override
    public Record selectOne(Record record) {
        return null;
    }

    @Override
    public int selectCount(Record record) {
        return R;
    }

    @Override
    public int insert(Record record) {
        return R;
    }

    @Override
    public int insertSelective(Record record) {
        return R;
    }

    @Override
    public int updateByPrimaryKey(Record record) {
        return R;
    }

    @Override
    public int updateByPrimaryKeySelective(Record record) {
        return R;
    }

    @Override
    public int delete(Record record) {
        return R;
    }

    @Override
    public int deleteByPrimaryKey(Object key) {
        return R;
    }

    @Override
    public List<Record> selectByExample(Example example) {
        return null;
    }

    @Override
    public int selectCountByExample(Example example) {
        return R;
    }

    @Override
    public int updateByExample(@Param("record") Record record, @Param("example") Example example) {
        return R;
    }

    @Override
    public int updateByExampleSelective(@Param("record") Record record, @Param("example") Example example) {
        return R;
    }

    @Override
    public int deleteByExample(Example example) {
        return R;
    }

    @Override
    public Paging<Record> select(Record record, int page, int size) {
        return null;
    }

    @Override
    public Paging<Record> select(Record record, int page, int size, boolean count) {
        return null;
    }

    @Override
    public Paging<Record> selectByExample(Example example, int page, int size) {
        return null;
    }

    @Override
    public Paging<Record> selectByExample(Example example, int page, int size, boolean count) {
        return null;
    }
}