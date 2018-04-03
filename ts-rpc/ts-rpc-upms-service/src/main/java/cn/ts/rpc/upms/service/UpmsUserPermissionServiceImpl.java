package cn.ts.rpc.upms.service;

import cn.ts.core.mybatis.BaseServiceImpl;
import cn.ts.rpc.upms.api.UpmsUserPermissionService;
import cn.ts.rpc.upms.model.UpmsUserPermission;
import cn.ts.rpc.upms.model.UpmsUserPermissionExample;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

/**
 * @author Created by YL on 2017/7/15.
 */
@Service(interfaceClass = UpmsUserPermissionService.class)
public class UpmsUserPermissionServiceImpl extends BaseServiceImpl<UpmsUserPermission, UpmsUserPermissionExample>
        implements UpmsUserPermissionService {

    @Override
    public int permission(JSONArray datas, BigDecimal id) {
        for (int i = 0; i < datas.size(); i++) {
            JSONObject json = datas.getJSONObject(i);
            if (json.getBoolean("checked")) {
                // 新增权限
                UpmsUserPermission upmsUserPermission = new UpmsUserPermission();
                upmsUserPermission.setUserId(id);
                upmsUserPermission.setPermissionId(json.getBigDecimal("id"));
                upmsUserPermission.setType(json.getBigDecimal("type"));
                this.insertSelective(upmsUserPermission);
            } else {
                // 删除权限
                UpmsUserPermissionExample upmsUserPermissionExample = new UpmsUserPermissionExample();
                upmsUserPermissionExample.createCriteria()
                        .andPermissionIdEqualTo(json.getBigDecimal("id"))
                        .andTypeEqualTo(json.getBigDecimal("type"));
                this.deleteByExample(upmsUserPermissionExample);
            }
        }
        return datas.size();
    }
}
