package cn.ts.rpc.upms.mapper;

import cn.ts.core.mybatis.mapper.CoreMapper;
import cn.ts.rpc.upms.model.UpmsPermission;

import java.math.BigDecimal;
import java.util.List;

public interface UpmsPermissionMapper extends CoreMapper<UpmsPermission> {
    /**
     * 根据用户id获取所拥有的权限
     *
     * @param userId 用户ID
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUserId(BigDecimal userId);
}