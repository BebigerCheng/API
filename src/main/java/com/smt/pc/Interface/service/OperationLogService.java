package com.smt.pc.Interface.service;

import com.smt.pc.Interface.domain.OperationLogDO;
import java.util.List;
import java.util.Map;

/**
 * 用户操作日志表
 * 
 * @author lijikai
 * @email 
 * @date 2018-04-02 11:25:15
 */
public interface OperationLogService {
	
	OperationLogDO get(Integer id);
	
	List<OperationLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OperationLogDO operationLog);
	
	int update(OperationLogDO operationLog);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
