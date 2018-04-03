package cn.ts.mybatis.generator.plugins.adaptor;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * mybatis mapper 插件生成 Mapper 相关
 *
 * @author Created by yl on 2017/05/30.
 */
public class MapperPluginAdaptor extends PluginAdapter {
    private Set<String> mappers = new LinkedHashSet<>();

    private static final boolean CREATE_METHOD = false;

    public MapperPluginAdaptor() {
        super();
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    // @Override
    // public void setContext(Context context) {
    // super.setContext(context);
    //设置默认的注释生成器
    // CommentGeneratorConfiguration commentCfg = new CommentGeneratorConfiguration();
    // commentCfg.setConfigurationType(MyCommentGenerator.class.getCanonicalName());
    // context.setCommentGeneratorConfiguration(commentCfg);
    //支持oracle获取注释#114
    // context.getJdbcConnectionConfiguration().addProperty("remarksReporting", "true");
    // }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String mappers = this.properties.getProperty("mappers");
        if (StringUtility.stringHasValue(mappers)) {
            for (String mapper : mappers.split(",")) {
                this.mappers.add(mapper);
            }
        } else {
            throw new RuntimeException("MapperPluginAdaptor 插件缺少 mappers 属性");
        }
    }

    /**
     * 生成的对应实体类的 Mapper 接口
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass clazz, IntrospectedTable
            table) {
        //获取实体类
        FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(table.getBaseRecordType());
        //import接口
        for (String mapper : mappers) {
            interfaze.addImportedType(new FullyQualifiedJavaType(mapper));
            interfaze.addSuperInterface(new FullyQualifiedJavaType(mapper + "<" + entityType.getShortName() + ">"));
        }
        //import实体类
        interfaze.addImportedType(entityType);
        return true;
    }

    /**
     * 处理实体类的包和 @Table 注解
     */
    private void processEntityClass(TopLevelClass clazz, IntrospectedTable table) {
        //引入JPA注解
        clazz.addImportedType("javax.persistence.*");
        String tableName = table.getFullyQualifiedTableNameAtRuntime();
        // 是否忽略大小写，对于区分大小写的数据库，会有用
        if (!clazz.getType().getShortName().equals(tableName)) {
            clazz.addAnnotation("@Table(name = \"" + tableName + "\")");
        } else if (!clazz.getType().getShortName().equalsIgnoreCase(tableName)) {
            clazz.addAnnotation("@Table(name = \"" + tableName + "\")");
        } else {
            clazz.addAnnotation("@Table(name = \"" + tableName + "\")");
        }
    }

    /**
     * 生成基础实体类
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        processEntityClass(topLevelClass, introspectedTable);
        return true;
    }

    // /**
    //  * 不生成 Example 类
    //  */
    // @Override
    // public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    //     return CREATE_METHOD;
    // }

    // /**
    //  * 生成实体类注解 KEY 对象
    //  */
    // @Override
    // public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    //     return true;
    // }

    /* --- 下面所有return false的方法都不生成。这些都是基础的CRUD方法，使用通用Mapper实现 --- */

    /**
     * 生成带BLOB字段的对象
     */
    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }


    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                           IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                           IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                    IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                    IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                       IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientSelectAllMethodGenerated(Method method, Interface interfaze, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientSelectAllMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientCountByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientDeleteByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                 IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                    IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, Interface interfaze,
                                                                 IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                 IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                    IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, Interface interfaze,
                                                                    IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                    IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                       IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean providerGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean providerApplyWhereMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean providerInsertSelectiveMethodGenerated(Method method, TopLevelClass clazz,
                                                          IntrospectedTable table) {
        return CREATE_METHOD;
    }

    @Override
    public boolean providerUpdateByPrimaryKeySelectiveMethodGenerated(Method method, TopLevelClass clazz,
                                                                      IntrospectedTable table) {
        return CREATE_METHOD;
    }

    /**
     * 生成 mapper.xml 文件
     */
    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        // return CREATE_METHOD;
        return true;
    }

    /**
     * 在 mapper.xml 文件中生成实体类的 resultMap 映射配置
     */
    @Override
    public boolean sqlMapResultMapWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable
            introspectedTable) {
        // return CREATE_METHOD;
        return true;
    }

    @Override
    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapSelectAllElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(XmlElement element, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapExampleWhereClauseElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapResultMapWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapBaseColumnListElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapBlobColumnListElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return CREATE_METHOD;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable
            introspectedTable) {
        return CREATE_METHOD;
    }
}
