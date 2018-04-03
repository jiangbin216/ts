package cn.ts.core.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * spring data jpa：基础 Service 接口实现
 *
 * @author Created by YL on 2017/7/23.
 */
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {
    @Autowired
    private BaseRepository<T, ID> baseRepository;

    @Override
    public T findOne(ID var1) {
        return baseRepository.findOne(var1);
    }

    @Override
    public <S extends T> S findOne(Example<S> var1) {
        return baseRepository.findOne(var1);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public List<T> findAll(Sort var1) {
        return baseRepository.findAll(var1);
    }

    @Override
    public Page<T> findAll(Pageable var1) {
        return baseRepository.findAll(var1);
    }

    @Override
    public List<T> findAll(Iterable<ID> var1) {
        return baseRepository.findAll(var1);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> var1) {
        return baseRepository.findAll(var1);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> var1, Sort var2) {
        return baseRepository.findAll(var1, var2);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> var1, Pageable var2) {
        return baseRepository.findAll(var1, var2);
    }

    @Override
    public T getOne(ID var1) {
        return baseRepository.getOne(var1);
    }

    @Override
    public boolean exists(ID var1) {
        return baseRepository.exists(var1);
    }

    @Override
    public <S extends T> boolean exists(Example<S> var1) {
        return baseRepository.exists(var1);
    }

    @Override
    public long count() {
        return baseRepository.count();
    }

    @Override
    public <S extends T> long count(Example<S> var1) {
        return baseRepository.count(var1);
    }

    @Override
    public <S extends T> S save(S var1) {
        return baseRepository.save(var1);
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> var1) {
        return baseRepository.save(var1);
    }

    @Override
    public void flush() {
        baseRepository.flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S var1) {
        return baseRepository.saveAndFlush(var1);
    }

    @Override
    public void delete(ID var1) {
        baseRepository.delete(var1);
    }

    @Override
    public void delete(T var1) {
        baseRepository.delete(var1);
    }

    @Override
    public void delete(Iterable<? extends T> var1) {
        baseRepository.delete(var1);
    }

    @Override
    public void deleteAll() {
        baseRepository.deleteAll();
    }

    @Override
    public void deleteInBatch(Iterable<T> var1) {
        baseRepository.deleteInBatch(var1);
    }

    @Override
    public void deleteAllInBatch() {
        baseRepository.deleteAllInBatch();
    }

    @Override
    public T findOne(Specification<T> var1) {
        return baseRepository.findOne(var1);
    }

    @Override
    public List<T> findAll(Specification<T> var1) {
        return baseRepository.findAll(var1);
    }

    @Override
    public Page<T> findAll(Specification<T> var1, Pageable var2) {
        return baseRepository.findAll(var1, var2);
    }

    @Override
    public List<T> findAll(Specification<T> var1, Sort var2) {
        return baseRepository.findAll(var1, var2);
    }

    @Override
    public long count(Specification<T> var1) {
        return baseRepository.count();
    }

    @Override
    public List<Map<String, Object>> listBySQL(String sql) {
        return baseRepository.listBySQL(sql);
    }

    @Override
    public List<Map<String, Object>> listBySQL(String sql, Object[] vals) {
        return baseRepository.listBySQL(sql, vals);
    }

    @Override
    public Page<Map<String, Object>> listBySQL(String sql, Object[] vals, int page, int size) {
        return baseRepository.listBySQL(sql, vals, page, size);
    }
}
