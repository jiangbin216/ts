package cn.ts.rpc.upms.service;

import cn.ts.core.mybatis.BaseServiceImpl;
import cn.ts.rpc.upms.api.UpmsRolePermissionService;
import cn.ts.rpc.upms.model.UpmsRolePermission;
import cn.ts.rpc.upms.model.UpmsRolePermissionExample;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by YL on 2017/7/15.
 */
@Service(interfaceClass = UpmsRolePermissionService.class)
public class UpmsRolePermissionServiceImpl extends BaseServiceImpl<UpmsRolePermission, UpmsRolePermissionExample>
        implements UpmsRolePermissionService {

    @Override
    public int rolePermission(JSONArray datas, BigDecimal id) {
        List<BigDecimal> deleteIds = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            JSONObject json = datas.getJSONObject(i);
            if (!json.getBoolean("checked")) {
                deleteIds.add(json.getBigDecimal("id"));
            } else {
                // 新增权限
                UpmsRolePermission upmsRolePermission = new UpmsRolePermission();
                upmsRolePermission.setRoleId(id);
                upmsRolePermission.setPermissionId(json.getBigDecimal("id"));
                this.insertSelective(upmsRolePermission);
            }
        }
        // 删除权限
        if (deleteIds.size() > 0) {
            UpmsRolePermissionExample upmsRolePermissionExample = new UpmsRolePermissionExample();
            upmsRolePermissionExample.createCriteria()
                    .andPermissionIdIn(deleteIds)
                    .andRoleIdEqualTo(id);
            this.deleteByExample(upmsRolePermissionExample);
        }
        return datas.size();
    }
}
