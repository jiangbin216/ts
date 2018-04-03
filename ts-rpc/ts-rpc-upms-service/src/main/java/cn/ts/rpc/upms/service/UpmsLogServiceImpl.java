package cn.ts.rpc.upms.service;

import cn.ts.core.mybatis.BaseServiceImpl;
import cn.ts.rpc.upms.api.UpmsLogService;
import cn.ts.rpc.upms.model.UpmsLog;
import cn.ts.rpc.upms.model.UpmsLogExample;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author Created by YL on 2017/7/15.
 */
@Service(interfaceClass = UpmsLogService.class)
public class UpmsLogServiceImpl extends BaseServiceImpl<UpmsLog, UpmsLogExample> implements UpmsLogService {
}
