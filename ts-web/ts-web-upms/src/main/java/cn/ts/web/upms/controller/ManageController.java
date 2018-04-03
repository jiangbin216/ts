package cn.ts.web.upms.controller;

import cn.ts.core.spring.BaseController;
import cn.ts.rpc.upms.api.UpmsPermissionService;
import cn.ts.rpc.upms.api.UpmsSystemService;
import cn.ts.rpc.upms.api.UpmsUserService;
import cn.ts.rpc.upms.model.UpmsPermission;
import cn.ts.rpc.upms.model.UpmsSystem;
import cn.ts.rpc.upms.model.UpmsSystemExample;
import cn.ts.rpc.upms.model.UpmsUser;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.List;

/**
 * 后台controller
 *
 * @author Created by YL on 2017/01/19.
 */
@Controller
@RequestMapping("/manage")
@Api(value = "后台管理", description = "后台管理")
public class ManageController extends BaseController {
    @Reference
    private UpmsUserService upmsUserService;
    @Reference
    private UpmsSystemService upmsSystemService;
    @Reference
    private UpmsPermissionService upmsPermissionService;

    @ApiOperation(value = "后台首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        // 已注册系统
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo(new BigDecimal(1));
        List<UpmsSystem> upmsSystems = upmsSystemService.selectByExample(upmsSystemExample);
        modelMap.put("upmsSystems", upmsSystems);
        // 当前登录用户权限
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        UpmsUser upmsUser = upmsUserService.selectUpmsUserByUsername(username);
        List<UpmsPermission> upmsPermissions = upmsPermissionService.selectUpmsPermissionByUserId(upmsUser.getId());
        modelMap.put("upmsPermissions", upmsPermissions);
        return "manage/index.jsp";
    }
}