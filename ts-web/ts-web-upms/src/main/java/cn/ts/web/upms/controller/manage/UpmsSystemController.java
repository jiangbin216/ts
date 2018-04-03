package cn.ts.web.upms.controller.manage;

import cn.ts.core.fluent.LengthValidator;
import cn.ts.core.mybatis.pagehelper.Paging;
import cn.ts.core.spring.BaseController;
import cn.ts.rpc.upms.api.UpmsSystemService;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统controller
 *
 * @author Created by YL on 2016/12/18.
 */
@Controller
@Api(value = "系统管理", description = "系统管理")
@RequestMapping("/manage/system")
public class UpmsSystemController extends BaseController {
    @Reference
    private UpmsSystemService upmsSystemService;

    @ApiOperation(value = "系统首页")
    @RequiresPermissions("upms:system:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/system/index.jsp";
    }

    @ApiOperation(value = "系统列表")
    @RequiresPermissions("upms:system:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
            upmsSystemExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            upmsSystemExample.or()
                    .andTitleLike("%" + search + "%");
        }
        Paging<UpmsSystem> paging = upmsSystemService.selectByExample(upmsSystemExample, page, size);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", paging.getList());
        result.put("total", paging.getTotal());
        return result;
    }

    @ApiOperation(value = "新增系统")
    @RequiresPermissions("upms:system:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/system/create.jsp";
    }

    @ApiOperation(value = "新增系统")
    @RequiresPermissions("upms:system:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(UpmsSystem upmsSystem) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsSystem.getTitle(), new LengthValidator(1, 20, "标题"))
                .on(upmsSystem.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        Date time = new Date();
        upmsSystem.setCreateTime(time);
        upmsSystem.setOrders(new BigDecimal(time.getTime()));
        int count = upmsSystemService.insertSelective(upmsSystem);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除系统")
    @RequiresPermissions("upms:system:delete")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        List<BigDecimal> list = new ArrayList<>();
        String[] idArray = ids.split("-", -1);
        for (String s : idArray) {
            BigDecimal bigDecimal = new BigDecimal(s);
            list.add(bigDecimal);
        }
        UpmsSystemExample example = new UpmsSystemExample();
        example.createCriteria().andIdIn(list);
        int count = upmsSystemService.deleteByExample(example);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改系统")
    @RequiresPermissions("upms:system:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") BigDecimal id, ModelMap modelMap) {
        UpmsSystem system = upmsSystemService.selectByPrimaryKey(id);
        modelMap.put("system", system);
        return "/manage/system/update.jsp";
    }

    @ApiOperation(value = "修改系统")
    @RequiresPermissions("upms:system:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") BigDecimal id, UpmsSystem upmsSystem) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsSystem.getTitle(), new LengthValidator(1, 20, "标题"))
                .on(upmsSystem.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        upmsSystem.setId(id);
        int count = upmsSystemService.updateByPrimaryKeySelective(upmsSystem);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}