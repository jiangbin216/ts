package cn.ts.rpc.upms.api;

import cn.ts.core.mybatis.BaseService;
import cn.ts.rpc.upms.model.UpmsUser;
import cn.ts.rpc.upms.model.UpmsUserExample;

/**
 * UpmsUserService接口
 *
 * @author Created by YL on 2017/4/27.
 */
public interface UpmsUserService extends BaseService<UpmsUser, UpmsUserExample> {
    /**
     * 根据 userName 获取 UpmsUser
     *
     * @param userName 用户帐号
     * @return
     */
    UpmsUser selectUpmsUserByUsername(String userName);
}