package cn.ts.core.mybatis.mapper;

/**
 * 条件查询
 * <pre>
 *     selectColumns: 配置查询的表字段名（不是配置JavaBean的属性名），其他返回hull
 * </pre>
 *
 * @author Created by YL on 2017/7/17.
 */
public class CoreExample {
    /**
     * 　要查询的表字段，其他将返回null
     */
    protected java.util.Set<String> selectColumns;

    public java.util.Set<String> getSelectColumns() {
        return selectColumns;
    }

    /**
     * 这里要配置表字段名，不是JavaBean的属性名，一般把 ID 主键配置上，有了 ID 后可以做其他一些操作
     */
    public void setSelectColumns(java.lang.String... selectColumns) {
        if (selectColumns != null && selectColumns.length > 0) {
            if (this.selectColumns == null) {
                this.selectColumns = new java.util.LinkedHashSet<>();
            }
            for (String property : selectColumns) {
                this.selectColumns.add(property);
            }
        }
    }
}
