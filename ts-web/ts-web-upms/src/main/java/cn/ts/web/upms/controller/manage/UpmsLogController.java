package cn.ts.web.upms.controller.manage;

import cn.ts.core.mybatis.pagehelper.Paging;
import cn.ts.core.spring.BaseController;
import cn.ts.rpc.upms.api.UpmsLogService;
import cn.ts.rpc.upms.model.UpmsLog;
import cn.ts.rpc.upms.model.UpmsLogExample;
import cn.ts.web.upms.common.UpmsResult;
import cn.ts.web.upms.common.UpmsResultConstant;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志controller
 *
 * @author Created by YL on 2017/3/14.
 */
@Controller
@RequestMapping("/manage/log")
@Api(value = "日志管理", description = "日志管理")
public class UpmsLogController extends BaseController {
    // private static Logger _log = LoggerFactory.getLogger(UpmsLogController.class);
    @Reference
    private UpmsLogService upmsLogService;

    @ApiOperation(value = "日志首页")
    @RequiresPermissions("upms:log:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/log/index.jsp";
    }

    @ApiOperation(value = "日志列表")
    @RequiresPermissions("upms:log:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsLogExample upmsLogExample = new UpmsLogExample();
        if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
            upmsLogExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            upmsLogExample.or()
                    .andDescriptionLike("%" + search + "%");
        }
        Paging<UpmsLog> paging = upmsLogService.selectByExample(upmsLogExample, page, size);
        System.out.println(paging);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", paging.getList());
        result.put("total", paging.getTotal());
        return result;
    }

    @ApiOperation(value = "删除日志")
    @RequiresPermissions("upms:log:delete")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        List<BigDecimal> list = new ArrayList<>();
        String[] idArray = ids.split("-", -1);
        for (String s : idArray) {
            BigDecimal bigDecimal = new BigDecimal(s);
            list.add(bigDecimal);
        }
        UpmsLogExample example = new UpmsLogExample();
        example.createCriteria().andIdIn(list);
        int count = upmsLogService.deleteByExample(example);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }
}