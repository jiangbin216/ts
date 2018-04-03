package cn.ts.rpc.upms.api;

import cn.ts.core.mybatis.BaseService;
import cn.ts.rpc.upms.model.UpmsUserPermission;
import cn.ts.rpc.upms.model.UpmsUserPermissionExample;
import com.alibaba.fastjson.JSONArray;

import java.math.BigDecimal;

/**
 * UpmsUserPermissionService接口
 *
 * @author Created by YL on 2017/4/27.
 */
public interface UpmsUserPermissionService extends BaseService<UpmsUserPermission, UpmsUserPermissionExample> {
    /**
     * 用户权限
     *
     * @param datas  权限
     * @param userId 用户ID
     * @return
     */
    int permission(JSONArray datas, BigDecimal userId);
}