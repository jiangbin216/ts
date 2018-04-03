// package cn.ts.mybatis.generator.plugins.adaptor;
//
// import org.mybatis.generator.api.IntrospectedTable;
// import org.mybatis.generator.api.PluginAdapter;
// import org.mybatis.generator.api.dom.java.Interface;
// import org.mybatis.generator.api.dom.java.Method;
//
// import java.util.List;
//
// /**
//  * 为Mapper类中生成的方法添加注释
//  *
//  * @author Created by yl on 2017/05/30.
//  */
// public class MapperCommentPluginAdaptor extends PluginAdapter {
//
//     @Override
//     public boolean validate(List<String> warnings) {
//         return true;
//     }
//
//     // if (introspectedTable.isJava5Targeted()) {
//     // // 如果支持Java5，就在方法上面生成一个@Override标签；
//     // method.addAnnotation("@Override"); //$NON-NLS-1$
//     // }
//
//
//     /**
//      * 生成 countByExample
//      */
//     public boolean clientCountByExampleMethodGenerated(Method method, Interface interfaze,
//             IntrospectedTable introspectedTable) {
//         method.addJavaDocLine("/**");
//         method.addJavaDocLine(" * 通过条件统计总数");
//         method.addJavaDocLine(" */");
//         context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
//         return true;
//     }
//
//     /**
//      * 生成 deleteByExample
//      */
//     public boolean clientDeleteByExampleMethodGenerated(Method method, Interface interfaze,
//             IntrospectedTable introspectedTable) {
//         method.addJavaDocLine("/**");
//         method.addJavaDocLine(" * 通过条件删除数据");
//         method.addJavaDocLine(" */");
//         context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
//         return true;
//     }
//
//     /**
//      * 生成 deleteByPrimaryKey
//      */
//     public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze,
//             IntrospectedTable introspectedTable) {
//         method.addJavaDocLine("/**");
//         method.addJavaDocLine(" * 通过主键删除数据");
//         method.addJavaDocLine(" */");
//         context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
//         return true;
//     }
//
//     /**
//      * 生成 insert
//      */
//     public boolean clientInsertMethodGenerated(Method method, Interface interfaze,
//             IntrospectedTable introspectedTable) {
//         method.addJavaDocLine("/**");
//         method.addJavaDocLine(" * 插入数据（包括Blob、Clob字段）");
//         method.addJavaDocLine(" */");
//         context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
//         return true;
//     }
//
//     /**
//      * 生成 InsertSelective
//      */
//     public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze,
//             IntrospectedTable introspectedTable) {
//         method.addJavaDocLine("/**");
//         method.addJavaDocLine(" * 插入数据，如果对应的值为空，则不会更新");
//         method.addJavaDocLine(" */");
//         context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
//         return true;
//     }
//
//     /**
//      * 生成 selectByExampleWithBLOBs
//      */
//     public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze,
//             IntrospectedTable introspectedTable) {
//         method.addJavaDocLine("/**");
//         method.addJavaDocLine(" * 通过条件查询（包括Blob、Clob字段）");
//         method.addJavaDocLine(" */");
//         context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
//         return true;
//     }
//
//     /**
//      * 生成 selectByExample
//      */
//     public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method,
//             Interface interfaze, IntrospectedTable introspectedTable) {
//         method.addJavaDocLine("/**");
//         method.addJavaDocLine(" * 通过条件查询（不包括Blob、Clob字段）");
//         method.addJavaDocLine(" */");
//         context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
//         return true;
//     }
//
//     /**
//      * 生成 selectByPrimaryKey
//      */
//     public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze,
//             IntrospectedTable introspectedTable) {
//         method.addJavaDocLine("/**");
//         method.addJavaDocLine(" * 通过主键查询（包括Blob、Clob字段）");
//         method.addJavaDocLine(" */");
//         context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
//         return true;
//     }
//
//     /**
//      * 生成 updateByExampleSelective
//      */
//     public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, Interface interfaze,
//             IntrospectedTable introspectedTable) {
//         method.addJavaDocLine("/**");
//         method.addJavaDocLine(" * 通过条件更新数据（包括Blob、Clob字段），如果对应的值为空，则不会更新");
//         method.addJavaDocLine(" */");
//         context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
//         return true;
//     }
//
//     /**
//      * 生成 updateByExampleWithBLOBs
//      */
//     public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze,
//             IntrospectedTable introspectedTable) {
//         method.addJavaDocLine("/**");
//         method.addJavaDocLine(" * 通过条件更新数据（包括Blob、Clob字段）");
//         method.addJavaDocLine(" */");
//         context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
//         return true;
//     }
//
//     /**
//      * 生成 updateByExample
//      */
//     public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method,
//             Interface interfaze, IntrospectedTable introspectedTable) {
//         method.addJavaDocLine("/**");
//         method.addJavaDocLine(" * 通过条件更新数据（不包括Blob、Clob字段）");
//         method.addJavaDocLine(" */");
//         context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
//         return true;
//     }
//
//     /**
//      * 生成 updateByPrimaryKeySelective
//      */
//     public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method,
//             Interface interfaze, IntrospectedTable introspectedTable) {
//         method.addJavaDocLine("/**");
//         method.addJavaDocLine(" * 通过主键更新数据（包括Blob、Clob字段），如果对应的值为空，则不会更新");
//         method.addJavaDocLine(" */");
//         context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
//         return true;
//     }
//
//     /**
//      * 生成 updateByPrimaryKeyWithBLOBs
//      */
//     public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method,
//             Interface interfaze, IntrospectedTable introspectedTable) {
//         method.addJavaDocLine("/**");
//         method.addJavaDocLine(" * 通过主键更新数据（包括Blob、Clob字段）");
//         method.addJavaDocLine(" */");
//         context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
//         return true;
//     }
//
//     /**
//      * 生成 updateByPrimaryKey
//      */
//     public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method,
//             Interface interfaze, IntrospectedTable introspectedTable) {
//         method.addJavaDocLine("/**");
//         method.addJavaDocLine(" * 通过主键更新数据（不包括Blob、Clob字段）");
//         method.addJavaDocLine(" */");
//         context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
//         return true;
//     }
//
//     /**
//      * 生成 selectAll
//      */
//     public boolean clientSelectAllMethodGenerated(Method method, Interface interfaze,
//             IntrospectedTable introspectedTable) {
//         return true;
//     }
// }
