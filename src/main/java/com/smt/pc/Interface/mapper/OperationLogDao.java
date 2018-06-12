package com.smt.pc.Interface.mapper;

import java.util.List;
import java.util.Map;
import com.smt.pc.Interface.domain.OperationLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户操作日志表
 * @author lijikai
 * @email 
 * @date 2018-04-02 11:25:15
 */
@Mapper
public interface OperationLogDao {

	OperationLogDO get(Integer id);
	
	List<OperationLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OperationLogDO operationLog);
	
	int update(OperationLogDO operationLog);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
