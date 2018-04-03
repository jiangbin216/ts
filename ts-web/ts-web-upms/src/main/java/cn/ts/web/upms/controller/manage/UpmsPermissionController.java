package cn.ts.web.upms.controller.manage;

import cn.ts.core.fluent.LengthValidator;
import cn.ts.core.mybatis.pagehelper.Paging;
import cn.ts.core.spring.BaseController;
import cn.ts.rpc.upms.api.UpmsPermissionService;
import cn.ts.rpc.upms.api.UpmsSystemService;
import cn.ts.rpc.upms.model.UpmsPermission;
import cn.ts.rpc.upms.model.UpmsPermissionExample;
import cn.ts.rpc.upms.model.UpmsSystem;
import cn.ts.rpc.upms.model.UpmsSystemExample;
import cn.ts.web.upms.common.UpmsResult;
import cn.ts.web.upms.common.UpmsResultConstant;
import com.alibaba.dubbo.config.annotation.Reference;
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

/**
 * 权限controller
 *
 * @author Created by shuzheng on 2017/2/6.
 */
@Controller
@Api(value = "权限管理", description = "权限管理")
@RequestMapping("/manage/permission")
public class UpmsPermissionController extends BaseController {
    private static Logger _log = LoggerFactory.getLogger(UpmsPermissionController.class);
    @Reference
    private UpmsPermissionService upmsPermissionService;
    @Reference
    private UpmsSystemService upmsSystemService;

    @ApiOperation(value = "权限首页")
    @RequiresPermissions("upms:permission:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/permission/index.jsp";
    }

    @ApiOperation(value = "权限列表")
    @RequiresPermissions("upms:permission:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, defaultValue = "0", value = "type") int type,
            @RequestParam(required = false, defaultValue = "0", value = "systemId") int systemId,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsPermissionExample upmsPermissionExample = new UpmsPermissionExample();
        UpmsPermissionExample.Criteria criteria = upmsPermissionExample.createCriteria();
        if (0 != type) {
            criteria.andTypeEqualTo(new BigDecimal(type));
        }
        if (0 != systemId) {
            criteria.andSystemIdEqualTo(new BigDecimal(systemId));
        }
        if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
            upmsPermissionExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            upmsPermissionExample.or()
                    .andNameLike("%" + search + "%");
        }
        Paging<UpmsPermission> paging = upmsPermissionService.selectByExample(upmsPermissionExample, page, size);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", paging.getList());
        result.put("total", paging.getTotal());
        return result;
    }

    @ApiOperation(value = "角色权限列表")
    @RequiresPermissions("upms:permission:read")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object role(@PathVariable("id") BigDecimal id) {
        return upmsPermissionService.getTreeByRoleId(id);
    }

    @ApiOperation(value = "用户权限列表")
    @RequiresPermissions("upms:permission:read")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object user(@PathVariable("id") BigDecimal id, HttpServletRequest request) {
        return upmsPermissionService.getTreeByUserId(id, new BigDecimal(request.getParameter("type")));
    }

    @ApiOperation(value = "新增权限")
    @RequiresPermissions("upms:permission:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo(new BigDecimal(1));
        List<UpmsSystem> upmsSystems = upmsSystemService.selectByExample(upmsSystemExample);
        modelMap.put("upmsSystems", upmsSystems);
        return "/manage/permission/create.jsp";
    }

    @ApiOperation(value = "新增权限")
    @RequiresPermissions("upms:permission:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UpmsPermission upmsPermission) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsPermission.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        Date time = new Date();
        upmsPermission.setCreateTime(time);
        upmsPermission.setOrders(new BigDecimal(time.getTime()));
        int count = upmsPermissionService.insertSelective(upmsPermission);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除权限")
    @RequiresPermissions("upms:permission:delete")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        List<BigDecimal> list = new ArrayList<>();
        String[] idArray = ids.split("-", -1);
        for (String s : idArray) {
            BigDecimal bigDecimal = new BigDecimal(s);
            list.add(bigDecimal);
        }
        UpmsPermissionExample example = new UpmsPermissionExample();
        example.createCriteria().andIdIn(list);
        int count = upmsPermissionService.deleteByExample(example);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改权限")
    @RequiresPermissions("upms:permission:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") BigDecimal id, ModelMap modelMap) {
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo(new BigDecimal(1));
        List<UpmsSystem> upmsSystems = upmsSystemService.selectByExample(upmsSystemExample);
        UpmsPermission permission = upmsPermissionService.selectByPrimaryKey(id);
        modelMap.put("permission", permission);
        modelMap.put("upmsSystems", upmsSystems);
        return "/manage/permission/update.jsp";
    }

    @ApiOperation(value = "修改权限")
    @RequiresPermissions("upms:permission:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") BigDecimal id, UpmsPermission upmsPermission) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsPermission.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        upmsPermission.setId(id);
        int count = upmsPermissionService.updateByPrimaryKeySelective(upmsPermission);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}
