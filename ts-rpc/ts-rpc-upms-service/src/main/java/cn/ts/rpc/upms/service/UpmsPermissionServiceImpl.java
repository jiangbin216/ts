package cn.ts.rpc.upms.service;

import cn.ts.core.mybatis.BaseServiceImpl;
import cn.ts.rpc.upms.api.UpmsPermissionService;
import cn.ts.rpc.upms.api.UpmsRolePermissionService;
import cn.ts.rpc.upms.api.UpmsSystemService;
import cn.ts.rpc.upms.api.UpmsUserPermissionService;
import cn.ts.rpc.upms.api.UpmsUserService;
import cn.ts.rpc.upms.mapper.UpmsPermissionMapper;
import cn.ts.rpc.upms.model.UpmsPermission;
import cn.ts.rpc.upms.model.UpmsPermissionExample;
import cn.ts.rpc.upms.model.UpmsRolePermission;
import cn.ts.rpc.upms.model.UpmsRolePermissionExample;
import cn.ts.rpc.upms.model.UpmsSystem;
import cn.ts.rpc.upms.model.UpmsSystemExample;
import cn.ts.rpc.upms.model.UpmsUser;
import cn.ts.rpc.upms.model.UpmsUserPermission;
import cn.ts.rpc.upms.model.UpmsUserPermissionExample;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Created by YL on 2017/7/15.
 */
@Service(interfaceClass = UpmsPermissionService.class)
public class UpmsPermissionServiceImpl extends BaseServiceImpl<UpmsPermission, UpmsPermissionExample> implements
        UpmsPermissionService {
    @Autowired
    private UpmsPermissionMapper upmsPermissionMapper;
    @Autowired
    private UpmsUserService upmsUserService;
    @Autowired
    private UpmsSystemService upmsSystemService;
    @Autowired
    private UpmsPermissionService upmsPermissionService;
    @Autowired
    private UpmsUserPermissionService upmsUserPermissionService;
    @Autowired
    private UpmsRolePermissionService upmsRolePermissionService;

    @Override
    public JSONArray getTreeByRoleId(BigDecimal roleId) {
        UpmsRolePermissionExample upmsRolePermissionExample = new UpmsRolePermissionExample();
        upmsRolePermissionExample.createCriteria()
                .andRoleIdEqualTo(roleId);
        List<UpmsRolePermission> rolePermissions = upmsRolePermissionService.selectByExample(upmsRolePermissionExample);
        // 角色已有权限
        // List<UpmsRolePermission> rolePermissions = upmsBaseService.selectUpmsRolePermisstionByUpmsRoleId(roleId);

        JSONArray systems = new JSONArray();
        // 系统
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo(new BigDecimal(1));
        upmsSystemExample.setOrderByClause("orders asc");
        List<UpmsSystem> upmsSystems = upmsSystemService.selectByExample(upmsSystemExample);
        for (UpmsSystem upmsSystem : upmsSystems) {
            JSONObject node = new JSONObject();
            node.put("id", upmsSystem.getId());
            node.put("name", upmsSystem.getTitle());
            node.put("nocheck", true);
            node.put("open", true);
            systems.add(node);
        }

        if (systems.size() > 0) {
            for (Object system : systems) {
                UpmsPermissionExample upmsPermissionExample = new UpmsPermissionExample();
                upmsPermissionExample.createCriteria()
                        .andStatusEqualTo(new BigDecimal(1))
                        .andSystemIdEqualTo(((JSONObject) system).getBigDecimal("id"));
                upmsPermissionExample.setOrderByClause("orders asc");
                List<UpmsPermission> upmsPermissions = upmsPermissionService.selectByExample(upmsPermissionExample);
                if (upmsPermissions.size() == 0) continue;
                // 目录
                JSONArray folders = new JSONArray();
                for (UpmsPermission upmsPermission : upmsPermissions) {
                    if (upmsPermission.getPid().longValue() != 0 || (upmsPermission.getType() != null &&
                            upmsPermission.getType().longValue() != 1)) {
                        continue;
                    }
                    JSONObject node = new JSONObject();
                    node.put("id", upmsPermission.getId());
                    node.put("name", upmsPermission.getName());
                    node.put("open", true);
                    for (UpmsRolePermission rolePermission : rolePermissions) {
                        if (rolePermission.getPermissionId().longValue() == upmsPermission.getId().longValue
                                ()) {
                            node.put("checked", true);
                        }
                    }
                    folders.add(node);
                    // 菜单
                    JSONArray menus = new JSONArray();
                    for (Object folder : folders) {
                        for (UpmsPermission upmsPermission2 : upmsPermissions) {
                            if (upmsPermission2.getPid().longValue() != ((JSONObject) folder).getLongValue("id") ||
                                    (upmsPermission2.getType() != null && upmsPermission2.getType().longValue() != 2))
                                continue;
                            JSONObject node2 = new JSONObject();
                            node2.put("id", upmsPermission2.getId());
                            node2.put("name", upmsPermission2.getName());
                            node2.put("open", true);
                            for (UpmsRolePermission rolePermission : rolePermissions) {
                                if (rolePermission.getPermissionId().longValue() == upmsPermission2.getId()
                                        .longValue()) {
                                    node2.put("checked", true);
                                }
                            }
                            menus.add(node2);
                            // 按钮
                            JSONArray buttons = new JSONArray();
                            for (Object menu : menus) {
                                for (UpmsPermission upmsPermission3 : upmsPermissions) {
                                    if (upmsPermission3.getPid().longValue() != ((JSONObject) menu).getLongValue("id")
                                            || (upmsPermission3.getType() != null && upmsPermission3.getType()
                                            .longValue() != 3))
                                        continue;
                                    JSONObject node3 = new JSONObject();
                                    node3.put("id", upmsPermission3.getId());
                                    node3.put("name", upmsPermission3.getName());
                                    node3.put("open", true);
                                    for (UpmsRolePermission rolePermission : rolePermissions) {
                                        if (rolePermission.getPermissionId().longValue() ==
                                                upmsPermission3.getId().longValue()) {
                                            node3.put("checked", true);
                                        }
                                    }
                                    buttons.add(node3);
                                }
                                if (buttons.size() > 0) {
                                    ((JSONObject) menu).put("children", buttons);
                                    buttons = new JSONArray();
                                }
                            }
                        }
                        if (menus.size() > 0) {
                            ((JSONObject) folder).put("children", menus);
                            menus = new JSONArray();
                        }
                    }
                }
                if (folders.size() > 0) {
                    ((JSONObject) system).put("children", folders);
                }
            }
        }
        return systems;
    }

    @Override
    public JSONArray getTreeByUserId(BigDecimal usereId, BigDecimal type) {
        // 角色权限
        UpmsUserPermissionExample upmsUserPermissionExample = new UpmsUserPermissionExample();
        upmsUserPermissionExample.createCriteria()
                .andUserIdEqualTo(usereId)
                .andTypeEqualTo(type);
        List<UpmsUserPermission> upmsUserPermissions = upmsUserPermissionService.selectByExample
                (upmsUserPermissionExample);

        JSONArray systems = new JSONArray();
        // 系统
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo(new BigDecimal(1));
        upmsSystemExample.setOrderByClause("orders asc");
        List<UpmsSystem> upmsSystems = upmsSystemService.selectByExample(upmsSystemExample);
        for (UpmsSystem upmsSystem : upmsSystems) {
            JSONObject node = new JSONObject();
            node.put("id", upmsSystem.getId());
            node.put("name", upmsSystem.getTitle());
            node.put("nocheck", true);
            node.put("open", true);
            systems.add(node);
        }

        if (systems.size() > 0) {
            for (Object system : systems) {
                UpmsPermissionExample upmsPermissionExample = new UpmsPermissionExample();
                upmsPermissionExample.createCriteria()
                        .andStatusEqualTo(new BigDecimal(1))
                        .andSystemIdEqualTo(((JSONObject) system).getBigDecimal("id"));
                upmsPermissionExample.setOrderByClause("orders asc");
                List<UpmsPermission> upmsPermissions = upmsPermissionService.selectByExample(upmsPermissionExample);
                if (upmsPermissions.size() == 0) continue;
                // 目录
                JSONArray folders = new JSONArray();
                for (UpmsPermission upmsPermission : upmsPermissions) {
                    if (upmsPermission.getPid().longValue() != 0 || (upmsPermission.getType() != null &&
                            upmsPermission.getType().longValue() != 1)) {
                        continue;
                    }
                    JSONObject node = new JSONObject();
                    node.put("id", upmsPermission.getId());
                    node.put("name", upmsPermission.getName());
                    node.put("open", true);
                    for (UpmsUserPermission upmsUserPermission : upmsUserPermissions) {
                        if (upmsUserPermission.getPermissionId().longValue() == upmsPermission.getId()
                                .longValue()) {
                            node.put("checked", true);
                        }
                    }
                    folders.add(node);
                    // 菜单
                    JSONArray menus = new JSONArray();
                    for (Object folder : folders) {
                        for (UpmsPermission upmsPermission2 : upmsPermissions) {
                            if (upmsPermission2.getPid().longValue() != ((JSONObject) folder).getLongValue("id") ||
                                    (upmsPermission2.getType() != null && upmsPermission2.getType().longValue() != 2)) {
                                continue;
                            }
                            JSONObject node2 = new JSONObject();
                            node2.put("id", upmsPermission2.getId());
                            node2.put("name", upmsPermission2.getName());
                            node2.put("open", true);
                            for (UpmsUserPermission upmsUserPermission : upmsUserPermissions) {
                                if (upmsUserPermission.getPermissionId().longValue() ==
                                        upmsPermission2.getId().longValue()) {
                                    node2.put("checked", true);
                                }
                            }
                            menus.add(node2);
                            // 按钮
                            JSONArray buttons = new JSONArray();
                            for (Object menu : menus) {
                                for (UpmsPermission upmsPermission3 : upmsPermissions) {
                                    if (upmsPermission3.getPid().longValue() != ((JSONObject) menu).getLongValue("id")
                                            || (upmsPermission3.getType() != null && upmsPermission3.getType()
                                            .longValue() != 3)) {
                                        continue;
                                    }
                                    JSONObject node3 = new JSONObject();
                                    node3.put("id", upmsPermission3.getId());
                                    node3.put("name", upmsPermission3.getName());
                                    node3.put("open", true);
                                    for (UpmsUserPermission upmsUserPermission : upmsUserPermissions) {
                                        if (upmsUserPermission.getPermissionId().longValue() ==
                                                upmsPermission3.getId().longValue()) {
                                            node3.put("checked", true);
                                        }
                                    }
                                    buttons.add(node3);
                                }
                                if (buttons.size() > 0) {
                                    ((JSONObject) menu).put("children", buttons);
                                    buttons = new JSONArray();
                                }
                            }
                        }
                        if (menus.size() > 0) {
                            ((JSONObject) folder).put("children", menus);
                            menus = new JSONArray();
                        }
                    }
                }
                if (folders.size() > 0) {
                    ((JSONObject) system).put("children", folders);
                }
            }
        }
        return systems;
    }

    @Override
    public List<UpmsPermission> selectUpmsPermissionByUserId(BigDecimal userId) {
        // 用户不存在或锁定状态
        UpmsUser upmsUser = upmsUserService.selectByPrimaryKey(userId);
        if (null == upmsUser || upmsUser.getLocked().longValue() == 1) {
            return null;
        }
        return upmsPermissionMapper.selectUpmsPermissionByUserId(userId);
    }
}
