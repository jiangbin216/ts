package cn.ts.rpc.upms.service;

import cn.ts.core.mybatis.BaseServiceImpl;
import cn.ts.rpc.upms.api.UpmsOrganizationService;
import cn.ts.rpc.upms.model.UpmsOrganization;
import cn.ts.rpc.upms.model.UpmsOrganizationExample;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author Created by YL on 2017/7/15.
 */
@Service(interfaceClass = UpmsOrganizationService.class)
public class UpmsOrganizationServiceImpl extends BaseServiceImpl<UpmsOrganization, UpmsOrganizationExample>
        implements UpmsOrganizationService {
}
