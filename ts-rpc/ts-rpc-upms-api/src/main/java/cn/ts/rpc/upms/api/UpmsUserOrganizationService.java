package cn.ts.rpc.upms.api;

import cn.ts.core.mybatis.BaseService;
import cn.ts.rpc.upms.model.UpmsUserOrganization;
import cn.ts.rpc.upms.model.UpmsUserOrganizationExample;

import java.math.BigDecimal;

/**
 * UpmsUserOrganizationService接口
 *
 * @author Created by YL on 2017/4/27.
 */
public interface UpmsUserOrganizationService extends BaseService<UpmsUserOrganization, UpmsUserOrganizationExample> {

    /**
     * 用户组织
     *
     * @param ids    组织ID
     * @param userId 用户ID
     * @return
     */
    int organization(String[] ids, BigDecimal userId);
}