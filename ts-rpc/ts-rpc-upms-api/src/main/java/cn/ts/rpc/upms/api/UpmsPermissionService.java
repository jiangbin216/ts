package cn.ts.rpc.upms.api;

import cn.ts.core.mybatis.BaseService;
import cn.ts.rpc.upms.model.UpmsPermission;
import cn.ts.rpc.upms.model.UpmsPermissionExample;
import com.alibaba.fastjson.JSONArray;

import java.math.BigDecimal;
import java.util.List;

/**
 * UpmsPermissionService接口
 *
 * @author Created by YL on 2017/4/27.
 */
public interface UpmsPermissionService extends BaseService<UpmsPermission, UpmsPermissionExample> {

    /**
     * 通过角色 id 获取 角色权限列表
     *
     * @param roleId 角色id
     * @return
     */
    JSONArray getTreeByRoleId(BigDecimal roleId);

    /**
     * 通过用户 id 获取 用户权列表
     *
     * @param usereId 用户id
     * @param type    权限类型(-1:减权限,1:增权限)
     * @return
     */
    JSONArray getTreeByUserId(BigDecimal usereId, BigDecimal type);

    /**
     * 根据用户id获取所拥有的权限
     *
     * @param userId 用户ID
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUserId(BigDecimal userId);
}