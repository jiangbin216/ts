package cn.ts.mybatis.generator.plugins.adaptor;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.Properties;

/**
 * Example类和model类实现序列化插件
 *
 * @author Created by YL on 2017/5/28.
 */
public class MySerializablePlugin extends PluginAdapter {
    private FullyQualifiedJavaType serializable;
    private FullyQualifiedJavaType gwtSerializable;
    private boolean addGWTInterface;
    private boolean suppressJavaInterface;

    public MySerializablePlugin() {
        super();
        serializable = new FullyQualifiedJavaType("java.io.Serializable"); //$NON-NLS-1$
        gwtSerializable =
                new FullyQualifiedJavaType("com.google.gwt.user.client.rpc.IsSerializable"); //$NON-NLS-1$
    }

    public boolean validate(List<String> warnings) {
        return true;
    }

    public void setProperties(Properties properties) {
        super.setProperties(properties);
        this.addGWTInterface =
                Boolean.valueOf(properties.getProperty("addGWTInterface")).booleanValue();
        this.suppressJavaInterface =
                Boolean.valueOf(properties.getProperty("suppressJavaInterface")).booleanValue();
    }

    public boolean modelBaseRecordClassGenerated(TopLevelClass clazz, IntrospectedTable table) {
        this.makeSerializable(clazz, table);
        return true;
    }

    public boolean modelPrimaryKeyClassGenerated(TopLevelClass clazz, IntrospectedTable table) {
        this.makeSerializable(clazz, table);
        return true;
    }

    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass clazz,
                                                      IntrospectedTable table) {
        this.makeSerializable(clazz, table);
        return true;
    }

    protected void makeSerializable(TopLevelClass clazz, IntrospectedTable table) {
        if (this.addGWTInterface) {
            clazz.addImportedType(this.gwtSerializable);
            clazz.addSuperInterface(this.gwtSerializable);
        }
        if (!this.suppressJavaInterface) {
            clazz.addImportedType(this.serializable);
            clazz.addSuperInterface(this.serializable);
            Field field = new Field();
            field.setFinal(true);
            field.setInitializationString("1L");
            field.setName("serialVersionUID");
            field.setStatic(true);
            field.setType(new FullyQualifiedJavaType("long"));
            field.setVisibility(JavaVisibility.PRIVATE);
            this.context.getCommentGenerator().addFieldComment(field, table);
            clazz.addField(field);
        }
    }

    /**
     * 添加给Example类序列化的方法
     */
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass clazz, IntrospectedTable table) {
        makeSerializable(clazz, table);
        for (InnerClass innerClass : clazz.getInnerClasses()) {
            if ("GeneratedCriteria".equals(innerClass.getType().getShortName())) {
                innerClass.addSuperInterface(serializable);
            }
            if ("Criteria".equals(innerClass.getType().getShortName())) {
                innerClass.addSuperInterface(serializable);
            }
            if ("Criterion".equals(innerClass.getType().getShortName())) {
                innerClass.addSuperInterface(serializable);
            }
        }
        return true;
    }
}
