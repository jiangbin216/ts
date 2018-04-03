package cn.ts.mybatis.generator.plugins.adaptor;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

import java.util.List;
import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * model类生成toString方法
 *
 * @author Created by YL on 2017/5/28.
 */
public class MyToStringPlugin extends PluginAdapter {
    private boolean useToStringFromRoot;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        useToStringFromRoot = isTrue(properties.getProperty("useToStringFromRoot"));
    }

    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass clazz, IntrospectedTable table) {
        createToStringMethod(context, clazz, table, useToStringFromRoot);
        return super.modelBaseRecordClassGenerated(clazz, table);
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass clazz,
                                                      IntrospectedTable table) {
        createToStringMethod(context, clazz, table, useToStringFromRoot);
        return super.modelRecordWithBLOBsClassGenerated(clazz, table);
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass clazz, IntrospectedTable table) {
        createToStringMethod(context, clazz, table, useToStringFromRoot);
        return super.modelPrimaryKeyClassGenerated(clazz, table);
    }

    /**
     * 生成toString方法
     *
     * @param context             当前上下文
     * @param clazz               类名
     * @param table               表名
     * @param useToStringFromRoot
     * @author YL 2017年5月30日
     */
    public static void createToStringMethod(Context context, TopLevelClass clazz,
                                            IntrospectedTable table, boolean useToStringFromRoot) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getStringInstance());
        method.setName("toString");
        if (table.isJava5Targeted()) {
            method.addAnnotation("@Override");
        }
        context.getCommentGenerator().addGeneralMethodComment(method, table);

        method.addBodyLine("StringBuilder sb = new StringBuilder();");
        method.addBodyLine("sb.append(this.getClass().getSimpleName());");
        method.addBodyLine("sb.append(\" [\");");
        StringBuilder sb = new StringBuilder();
        String sp = "";
        for (Field field : clazz.getFields()) {
            String property = field.getName();
            if ("serialVersionUID".equals(property)) {
                continue;
            }
            sb.setLength(0);
            sb.append("sb.append(\"").append(sp).append(property).append("=\")").append(".append(")
                    .append(property).append(");");
            method.addBodyLine(sb.toString());
            sp = ", ";
        }
        method.addBodyLine("sb.append(\"]\");");
        if (useToStringFromRoot && clazz.getSuperClass() != null) {
            method.addBodyLine("sb.append(\", from super class \");");
            method.addBodyLine("sb.append(super.toString());");
        }
        method.addBodyLine("return sb.toString();");
        clazz.addMethod(method);
    }
}
