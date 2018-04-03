package cn.ts.web.upms.controller.manage;

import cn.ts.core.fluent.LengthValidator;
import cn.ts.core.fluent.NotNullValidator;
import cn.ts.core.md5.MD5;
import cn.ts.core.mybatis.pagehelper.Paging;
import cn.ts.core.spring.BaseController;
import cn.ts.rpc.upms.api.UpmsOrganizationService;
import cn.ts.rpc.upms.api.UpmsRoleService;
import cn.ts.rpc.upms.api.UpmsUserOrganizationService;
import cn.ts.rpc.upms.api.UpmsUserPermissionService;
import cn.ts.rpc.upms.api.UpmsUserRoleService;
import cn.ts.rpc.upms.api.UpmsUserService;
import cn.ts.rpc.upms.model.UpmsOrganization;
import cn.ts.rpc.upms.model.UpmsOrganizationExample;
import cn.ts.rpc.upms.model.UpmsRole;
import cn.ts.rpc.upms.model.UpmsRoleExample;
import cn.ts.rpc.upms.model.UpmsUser;
import cn.ts.rpc.upms.model.UpmsUserExample;
import cn.ts.rpc.upms.model.UpmsUserOrganization;
import cn.ts.rpc.upms.model.UpmsUserOrganizationExample;
import cn.ts.rpc.upms.model.UpmsUserRole;
import cn.ts.rpc.upms.model.UpmsUserRoleExample;
import cn.ts.web.upms.common.UpmsResult;
import cn.ts.web.upms.common.UpmsResultConstant;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用户controller
 *
 * @author Created by shuzheng on 2017/2/6.
 */
@Controller
@Api(value = "用户管理", description = "用户管理")
@RequestMapping("/manage/user")
public class UpmsUserController extends BaseController {
    private static Logger _log = LoggerFactory.getLogger(UpmsUserController.class);
    @Reference
    private UpmsUserService upmsUserService;
    @Reference
    private UpmsRoleService upmsRoleService;
    @Reference
    private UpmsOrganizationService upmsOrganizationService;
    @Reference
    private UpmsUserOrganizationService upmsUserOrganizationService;
    @Reference
    private UpmsUserRoleService upmsUserRoleService;
    @Reference
    private UpmsUserPermissionService upmsUserPermissionService;

    @ApiOperation(value = "用户首页")
    @RequiresPermissions("upms:user:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/user/index.jsp";
    }

    @ApiOperation(value = "用户组织")
    @RequiresPermissions("upms:user:organization")
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.GET)
    public String organization(@PathVariable("id") BigDecimal id, ModelMap modelMap) {
        // 所有组织
        List<UpmsOrganization> upmsOrganizations = upmsOrganizationService.selectByExample(new
                UpmsOrganizationExample());
        // 用户拥有组织
        UpmsUserOrganizationExample upmsUserOrganizationExample = new UpmsUserOrganizationExample();
        upmsUserOrganizationExample.createCriteria()
                .andUserIdEqualTo(id);
        List<UpmsUserOrganization> upmsUserOrganizations = upmsUserOrganizationService.selectByExample
                (upmsUserOrganizationExample);
        modelMap.put("upmsOrganizations", upmsOrganizations);
        modelMap.put("upmsUserOrganizations", upmsUserOrganizations);
        return "/manage/user/organization.jsp";
    }

    @ApiOperation(value = "用户组织")
    @RequiresPermissions("upms:user:organization")
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object organization(@PathVariable("id") BigDecimal id, HttpServletRequest request) {
        String[] organizationIds = request.getParameterValues("organizationId");
        upmsUserOrganizationService.organization(organizationIds, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, "");
    }

    @ApiOperation(value = "用户角色")
    @RequiresPermissions("upms:user:role")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public String role(@PathVariable("id") BigDecimal id, ModelMap modelMap) {
        // 所有角色
        List<UpmsRole> upmsRoles = upmsRoleService.selectByExample(new UpmsRoleExample());
        // 用户拥有角色
        UpmsUserRoleExample upmsUserRoleExample = new UpmsUserRoleExample();
        upmsUserRoleExample.createCriteria()
                .andUserIdEqualTo(id);
        List<UpmsUserRole> upmsUserRoles = upmsUserRoleService.selectByExample(upmsUserRoleExample);
        modelMap.put("upmsRoles", upmsRoles);
        modelMap.put("upmsUserRoles", upmsUserRoles);
        return "/manage/user/role.jsp";
    }

    @ApiOperation(value = "用户角色")
    @RequiresPermissions("upms:user:role")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object role(@PathVariable("id") BigDecimal id, HttpServletRequest request) {
        String[] roleIds = request.getParameterValues("roleId");
        upmsUserRoleService.role(roleIds, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, "");
    }

    @ApiOperation(value = "用户权限")
    @RequiresPermissions("upms:user:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public String permission(@PathVariable("id") BigDecimal id, ModelMap modelMap) {
        UpmsUser user = upmsUserService.selectByPrimaryKey(id);
        modelMap.put("user", user);
        return "/manage/user/permission.jsp";
    }

    @ApiOperation(value = "用户权限")
    @RequiresPermissions("upms:user:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object permission(@PathVariable("id") BigDecimal id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        upmsUserPermissionService.permission(datas, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, datas.size());
    }

    @ApiOperation(value = "用户列表")
    @RequiresPermissions("upms:user:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
            upmsUserExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            upmsUserExample.or()
                    .andRealnameLike("%" + search + "%");
            upmsUserExample.or()
                    .andUsernameLike("%" + search + "%");
        }
        Paging<UpmsUser> paging = upmsUserService.selectByExample(upmsUserExample, page, size);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", paging.getList());
        result.put("total", paging.getTotal());
        return result;
    }

    @ApiOperation(value = "新增用户")
    @RequiresPermissions("upms:user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/user/create.jsp";
    }

    @ApiOperation(value = "新增用户")
    @RequiresPermissions("upms:user:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UpmsUser upmsUser) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsUser.getUsername(), new LengthValidator(5, 20, "帐号"))
                .on(upmsUser.getPassword(), new LengthValidator(8, 32, "密码"))
                .on(upmsUser.getRealname(), new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria()
                .andUsernameEqualTo(upmsUser.getUsername());
        long count = upmsUserService.selectCountByExample(upmsUserExample);
        if (count > 0) {
            return new UpmsResult(UpmsResultConstant.FAILED, "帐号名已存在！");
        }
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        upmsUser.setSalt(salt);
        upmsUser.setPassword(MD5.UTF8.md5UpperCase(upmsUser.getPassword() + upmsUser.getSalt()));
        upmsUser.setCreateTime(new Date());
        upmsUserService.insert(upmsUser);
        _log.info("新增用户，主键：userId={}", upmsUser.getId());
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "删除用户")
    @RequiresPermissions("upms:user:delete")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        List<BigDecimal> list = new ArrayList<>();
        String[] idArray = ids.split("-", -1);
        for (String s : idArray) {
            BigDecimal bigDecimal = new BigDecimal(s);
            list.add(bigDecimal);
        }
        UpmsUserExample example = new UpmsUserExample();
        example.createCriteria().andIdIn(list);
        int count = upmsUserService.deleteByExample(example);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改用户")
    @RequiresPermissions("upms:user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") BigDecimal id, ModelMap modelMap) {
        UpmsUser user = upmsUserService.selectByPrimaryKey(id);
        modelMap.put("user", user);
        return "/manage/user/update.jsp";
    }

    @ApiOperation(value = "修改用户")
    @RequiresPermissions("upms:user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") BigDecimal id, UpmsUser upmsUser) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsUser.getUsername(), new LengthValidator(5, 20, "帐号"))
                .on(upmsUser.getRealname(), new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        // 不允许直接改密码
        upmsUser.setPassword(null);
        upmsUser.setId(id);
        int count = upmsUserService.updateByPrimaryKeySelective(upmsUser);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}