package cn.ts.rpc.upms.api;

import cn.ts.core.mybatis.BaseService;
import cn.ts.rpc.upms.model.UpmsUserRole;
import cn.ts.rpc.upms.model.UpmsUserRoleExample;

import java.math.BigDecimal;

/**
 * UpmsUserRoleService接口
 *
 * @author Created by YL on 2017/4/27.
 */
public interface UpmsUserRoleService extends BaseService<UpmsUserRole, UpmsUserRoleExample> {
    /**
     * 用户角色
     *
     * @param roleIds 角色ID
     * @param userId  用户ID
     * @return
     */
    int role(String[] roleIds, BigDecimal userId);
}