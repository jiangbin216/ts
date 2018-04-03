package cn.ts.web.upms.controller.manage;

import cn.ts.core.fluent.LengthValidator;
import cn.ts.core.mybatis.pagehelper.Paging;
import cn.ts.core.spring.BaseController;
import cn.ts.rpc.upms.api.UpmsRolePermissionService;
import cn.ts.rpc.upms.api.UpmsRoleService;
import cn.ts.rpc.upms.model.UpmsRole;
import cn.ts.rpc.upms.model.UpmsRoleExample;
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
 * 角色controller
 *
 * @author Created by shuzheng on 2017/2/6.
 */
@Controller
@Api(value = "角色管理", description = "角色管理")
@RequestMapping("/manage/role")
public class UpmsRoleController extends BaseController {
    @Reference
    private UpmsRoleService upmsRoleService;
    @Reference
    private UpmsRolePermissionService upmsRolePermissionService;

    @ApiOperation(value = "角色首页")
    @RequiresPermissions("upms:role:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/role/index.jsp";
    }

    @ApiOperation(value = "角色权限")
    @RequiresPermissions("upms:role:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public String permission(@PathVariable("id") BigDecimal id, ModelMap modelMap) {
        UpmsRole role = upmsRoleService.selectByPrimaryKey(id);
        modelMap.put("role", role);
        return "/manage/role/permission.jsp";
    }

    @ApiOperation(value = "角色权限")
    @RequiresPermissions("upms:role:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object permission(@PathVariable("id") BigDecimal id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        int result = upmsRolePermissionService.rolePermission(datas, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, result);
    }

    @ApiOperation(value = "角色列表")
    @RequiresPermissions("upms:role:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsRoleExample upmsRoleExample = new UpmsRoleExample();
        if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
            upmsRoleExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            upmsRoleExample.or()
                    .andTitleLike("%" + search + "%");
        }
        Paging<UpmsRole> paging = upmsRoleService.selectByExample(upmsRoleExample, page, size);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", paging.getList());
        result.put("total", paging.getTotal());
        return result;
    }

    @ApiOperation(value = "新增角色")
    @RequiresPermissions("upms:role:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/role/create.jsp";
    }

    @ApiOperation(value = "新增角色")
    @RequiresPermissions("upms:role:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UpmsRole upmsRole) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsRole.getName(), new LengthValidator(1, 20, "名称"))
                .on(upmsRole.getTitle(), new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        Date time = new Date();
        upmsRole.setCreateTime(time);
        upmsRole.setOrders(new BigDecimal(time.getTime()));
        int count = upmsRoleService.insertSelective(upmsRole);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除角色")
    @RequiresPermissions("upms:role:delete")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        List<BigDecimal> list = new ArrayList<>();
        String[] idArray = ids.split("-", -1);
        for (String s : idArray) {
            BigDecimal bigDecimal = new BigDecimal(s);
            list.add(bigDecimal);
        }
        UpmsRoleExample example = new UpmsRoleExample();
        example.createCriteria().andIdIn(list);
        int count = upmsRoleService.deleteByExample(example);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改角色")
    @RequiresPermissions("upms:role:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") BigDecimal id, ModelMap modelMap) {
        UpmsRole role = upmsRoleService.selectByPrimaryKey(id);
        modelMap.put("role", role);
        return "/manage/role/update.jsp";
    }

    @ApiOperation(value = "修改角色")
    @RequiresPermissions("upms:role:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") BigDecimal id, UpmsRole upmsRole) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsRole.getName(), new LengthValidator(1, 20, "名称"))
                .on(upmsRole.getTitle(), new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        upmsRole.setId(id);
        int count = upmsRoleService.updateByPrimaryKeySelective(upmsRole);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}
