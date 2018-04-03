package cn.ts.rpc.upms.mapper;

import cn.ts.core.mybatis.mapper.CoreMapper;
import cn.ts.rpc.upms.model.UpmsRole;

import java.math.BigDecimal;
import java.util.List;

public interface UpmsRoleMapper extends CoreMapper<UpmsRole> {
    /**
     * 通过用户ID获取用户角色列表
     *
     * @param userId 用户ID
     * @return
     */
    List<UpmsRole> selectUpmsRoleByUserId(BigDecimal userId);
}