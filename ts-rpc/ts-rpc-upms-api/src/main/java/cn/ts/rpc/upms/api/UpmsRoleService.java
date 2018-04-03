package cn.ts.rpc.upms.api;

import cn.ts.core.mybatis.BaseService;
import cn.ts.rpc.upms.model.UpmsRole;
import cn.ts.rpc.upms.model.UpmsRoleExample;

import java.math.BigDecimal;
import java.util.List;

/**
 * UpmsRoleService接口
 *
 * @author Created by YL on 2017/4/27.
 */
public interface UpmsRoleService extends BaseService<UpmsRole, UpmsRoleExample> {
    /**
     * 根据用户id获取所属的角色
     *
     * @param userId 用户ID
     * @return
     */
    List<UpmsRole> selectUpmsRoleByUserId(BigDecimal userId);
}