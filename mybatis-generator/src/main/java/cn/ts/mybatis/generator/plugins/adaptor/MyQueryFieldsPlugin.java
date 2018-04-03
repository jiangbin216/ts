package cn.ts.mybatis.generator.plugins.adaptor;

import cn.ts.core.mybatis.mapper.CoreExample;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * 自定义查询字段插件：比如一个表有10个字段，我们只配置查询其中的id，则其他字段全部返回null，这个可以减少实体类占用内存的大小
 *
 * @author Created by yl on 2017/05/28.
 */
public class MyQueryFieldsPlugin extends PluginAdapter {

    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass clazz, IntrospectedTable table) {
        // 导包
        clazz.addImportedType(new FullyQualifiedJavaType(CoreExample.class.getName()));
        // 继承类
        clazz.setSuperClass(new FullyQualifiedJavaType(CoreExample.class.getSimpleName()));
        // 实现接口
        // clazz.addSuperInterface(new FullyQualifiedJavaType(CoreExample.class.getSimpleName()));
        return true;
    }

    // @Override
    // public boolean modelExampleClassGenerated(TopLevelClass clazz, IntrospectedTable table) {
    //     createSelectColumns(clazz, "selectColumns");
    //     return true;
    // }
    //
    // private void createSelectColumns(TopLevelClass clazz, String fieldName) {
    //     FullyQualifiedJavaType instanceSet = new FullyQualifiedJavaType("java.util.Set<String>");
    //     FullyQualifiedJavaType instanceStr = new FullyQualifiedJavaType("java.lang.String...");
    //
    //     // 生成类属性
    //     Field field = new Field();
    //     field.setVisibility(JavaVisibility.PROTECTED);
    //     field.setType(instanceSet);
    //     field.setName(fieldName);
    //     field.addJavaDocLine("/**");
    //     field.addJavaDocLine(" *　要查询的表字段，其他将返回null");
    //     field.addJavaDocLine(" */");
    //     clazz.addField(field);
    //
    //     char c = fieldName.charAt(0);
    //     String camel = Character.toUpperCase(c) + fieldName.substring(1);
    //
    //     // 生成getter方法
    //     Method getter = new Method();
    //     getter.setVisibility(JavaVisibility.PUBLIC);
    //     getter.setReturnType(instanceSet);
    //     getter.setName("get" + camel);
    //     getter.addBodyLine("return " + fieldName + ";");
    //     clazz.addMethod(getter);
    //
    //     // 生成setter方法
    //     Method setter = new Method();
    //     setter.setVisibility(JavaVisibility.PUBLIC);
    //     setter.setName("set" + camel);
    //     setter.addParameter(new Parameter(instanceStr, fieldName));
    //     setter.addBodyLine("if (" + fieldName + " != null && " + fieldName + ".length > 0) {");
    //     setter.addBodyLine("if (this." + fieldName + " == null) {");
    //     setter.addBodyLine("this." + fieldName + " = new java.util.LinkedHashSet<String>();");
    //     setter.addBodyLine("}");
    //     setter.addBodyLine("for (String property : " + fieldName + ") {");
    //     setter.addBodyLine("this." + fieldName + ".add(property);");
    //     setter.addBodyLine("}");
    //     setter.addBodyLine("}");
    //
    //     setter.addJavaDocLine("/**");
    //     setter.addJavaDocLine(" * 这里要配置表字段名，不是JavaBean的属性名");
    //     setter.addJavaDocLine(" */");
    //
    //     clazz.addMethod(setter);
    // }
}
