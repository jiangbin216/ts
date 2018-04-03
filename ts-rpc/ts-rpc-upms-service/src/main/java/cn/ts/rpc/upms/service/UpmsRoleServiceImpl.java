package cn.ts.rpc.upms.service;

import cn.ts.core.mybatis.BaseServiceImpl;
import cn.ts.rpc.upms.api.UpmsRoleService;
import cn.ts.rpc.upms.mapper.UpmsRoleMapper;
import cn.ts.rpc.upms.model.UpmsRole;
import cn.ts.rpc.upms.model.UpmsRoleExample;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Created by YL on 2017/7/15.
 */
@Service(interfaceClass = UpmsRoleService.class)
public class UpmsRoleServiceImpl extends BaseServiceImpl<UpmsRole, UpmsRoleExample> implements UpmsRoleService {
    @Autowired
    private UpmsRoleMapper upmsRoleMapper;

    @Override
    public List<UpmsRole> selectUpmsRoleByUserId(BigDecimal userId) {
        return upmsRoleMapper.selectUpmsRoleByUserId(userId);
    }
}
