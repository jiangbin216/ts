package cn.ts.rpc.upms.service;

import cn.ts.core.mybatis.BaseServiceImpl;
import cn.ts.rpc.upms.api.UpmsUserOrganizationService;
import cn.ts.rpc.upms.model.UpmsUserOrganization;
import cn.ts.rpc.upms.model.UpmsUserOrganizationExample;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

/**
 * @author Created by YL on 2017/7/15.
 */
@Service(interfaceClass = UpmsUserOrganizationService.class)
public class UpmsUserOrganizationServiceImpl extends BaseServiceImpl<UpmsUserOrganization,
        UpmsUserOrganizationExample> implements UpmsUserOrganizationService {

    @Override
    public int organization(String[] ids, BigDecimal userId) {
        int result = 0;
        // 删除旧记录
        UpmsUserOrganizationExample upmsUserOrganizationExample = new UpmsUserOrganizationExample();
        upmsUserOrganizationExample.createCriteria()
                .andUserIdEqualTo(userId);
        this.deleteByExample(upmsUserOrganizationExample);
        // 增加新记录
        if (null != ids) {
            for (String organizationId : ids) {
                if (StringUtils.isBlank(organizationId)) {
                    continue;
                }
                UpmsUserOrganization upmsUserOrganization = new UpmsUserOrganization();
                upmsUserOrganization.setUserId(userId);
                upmsUserOrganization.setOrganizationId(new BigDecimal(organizationId));
                result = this.insertSelective(upmsUserOrganization);
            }
        }
        return result;
    }
}
