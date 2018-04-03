package cn.ts.rpc.upms.api;

import cn.ts.core.mybatis.BaseService;
import cn.ts.rpc.upms.model.UpmsRolePermission;
import cn.ts.rpc.upms.model.UpmsRolePermissionExample;
import com.alibaba.fastjson.JSONArray;

import java.math.BigDecimal;

/**
 * UpmsRolePermissionService接口
 *
 * @author Created by YL on 2017/4/27.
 */
public interface UpmsRolePermissionService extends BaseService<UpmsRolePermission, UpmsRolePermissionExample> {
    /**
     * 角色权限
     *
     * @param datas
     * @param id
     * @return
     */
    int rolePermission(JSONArray datas, BigDecimal id);
}