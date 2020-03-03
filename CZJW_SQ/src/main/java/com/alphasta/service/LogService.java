package com.alphasta.service;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.SysLog;

/**
 * @description：操作日志
 * @author：liyunhao
 * @date：2015/10/30 10:35
 */
public interface LogService {

    void insertLog(SysLog sysLog);

    void findDataGrid(PageInfo pageInfo);
}
