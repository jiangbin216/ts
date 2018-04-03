package cn.ts.core.spring.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * 自定义 BaseRepositoryFactoryBean 工场，去替代默认的工厂类
 * <p>
 * 我们需要让 spring 在加载的时候找到我们自定义的工场实现 BaseRepositoryFactoryBean，当我们使用 spring-boot 时，只需要在入口类中加入 EnableJpaRepositories
 * 即可，代码如下：
 * </p>
 * <pre>
 *     // 指定自己自定义的工场
 *     @EnableJpaRepositories(basePackages = {"cn.ts.core.spring.data"}, repositoryFactoryBeanClass =
 *     BaseRepositoryFactoryBean.class)
 * </pre>
 *
 * @author Created by YL on 2017/7/23.
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, ID>, T, ID extends Serializable> extends
        JpaRepositoryFactoryBean<R, T, ID> {
    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new BaseRepositoryFactory(entityManager);
    }

    /**
     * 创建一个内部类，该类不用在外部访问
     */
    private static class BaseRepositoryFactory<T, ID extends Serializable> extends JpaRepositoryFactory {
        private final EntityManager entityManager;

        public BaseRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            this.entityManager = entityManager;
        }

        /**
         * 配置具体实现类是 BaseRepositoryImpl
         *
         * @param information
         * @return
         */
        @Override
        protected Object getTargetRepository(RepositoryInformation information) {
            return new BaseRepositoryImpl<T, ID>(
                    (Class<T>) information.getDomainType(), entityManager);
        }

        // @SuppressWarnings("unchecked")
        // protected Object getTargetRepository(RepositoryMetadata metadata) {
        //     return new BaseRepositoryImpl<T, ID>(
        //             (Class<T>) metadata.getDomainType(), entityManager);
        // }


        /**
         * 设置具体的实现类 class
         *
         * @param metadata
         * @return
         */
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
    }
}
