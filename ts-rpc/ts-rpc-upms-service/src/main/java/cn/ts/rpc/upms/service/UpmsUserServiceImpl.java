package cn.ts.rpc.upms.service;

import cn.ts.core.mybatis.BaseServiceImpl;
import cn.ts.rpc.upms.api.UpmsUserService;
import cn.ts.rpc.upms.model.UpmsUser;
import cn.ts.rpc.upms.model.UpmsUserExample;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @author Created by YL on 2017/7/15.
 */
@Service(interfaceClass = UpmsUserService.class)
public class UpmsUserServiceImpl extends BaseServiceImpl<UpmsUser, UpmsUserExample> implements UpmsUserService {

    @Override
    public UpmsUser selectUpmsUserByUsername(String userName) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria()
                .andUsernameEqualTo(userName);
        List<UpmsUser> upmsUsers = this.selectByExample(upmsUserExample);
        if (null != upmsUsers && upmsUsers.size() > 0) {
            return upmsUsers.get(0);
        }
        return null;
    }
}
