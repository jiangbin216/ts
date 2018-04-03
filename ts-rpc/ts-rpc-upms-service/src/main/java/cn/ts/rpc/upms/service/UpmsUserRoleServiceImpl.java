package cn.ts.rpc.upms.service;

import cn.ts.core.mybatis.BaseServiceImpl;
import cn.ts.rpc.upms.api.UpmsUserRoleService;
import cn.ts.rpc.upms.model.UpmsUserRole;
import cn.ts.rpc.upms.model.UpmsUserRoleExample;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

/**
 * @author Created by YL on 2017/7/15.
 */
@Service(interfaceClass = UpmsUserRoleService.class)
public class UpmsUserRoleServiceImpl extends BaseServiceImpl<UpmsUserRole, UpmsUserRoleExample> implements
        UpmsUserRoleService {

    @Override
    public int role(String[] roleIds, BigDecimal userId) {
        int result = 0;
        // 删除旧记录
        UpmsUserRoleExample upmsUserRoleExample = new UpmsUserRoleExample();
        upmsUserRoleExample.createCriteria()
                .andUserIdEqualTo(userId);
        this.deleteByExample(upmsUserRoleExample);
        // 增加新记录
        if (null != roleIds) {
            for (String roleId : roleIds) {
                if (StringUtils.isBlank(roleId)) {
                    continue;
                }
                UpmsUserRole upmsUserRole = new UpmsUserRole();
                upmsUserRole.setUserId(userId);
                upmsUserRole.setRoleId(new BigDecimal(roleId));
                result = this.insertSelective(upmsUserRole);
            }
        }
        return result;
    }
}
