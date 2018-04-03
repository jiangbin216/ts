package cn.ts.rpc.upms.service;

import cn.ts.core.mybatis.BaseServiceImpl;
import cn.ts.rpc.upms.api.UpmsSystemService;
import cn.ts.rpc.upms.model.UpmsSystem;
import cn.ts.rpc.upms.model.UpmsSystemExample;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author Created by YL on 2017/7/15.
 */
@Service(interfaceClass = UpmsSystemService.class)
public class UpmsSystemServiceImpl extends BaseServiceImpl<UpmsSystem, UpmsSystemExample> implements UpmsSystemService {
}
