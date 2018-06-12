package com.smt.pc.Interface.service.impl;

import com.smt.pc.Interface.domain.OperationLogDO;
import com.smt.pc.Interface.mapper.OperationLogDao;
import com.smt.pc.Interface.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class OperationLogServiceImpl implements OperationLogService {
	@Autowired
	private OperationLogDao operationLogDao;
	
	@Override
	public OperationLogDO get(Integer id){
		return operationLogDao.get(id);
	}
	
	@Override
	public List<OperationLogDO> list(Map<String, Object> map){
		return operationLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return operationLogDao.count(map);
	}
	
	@Override
	public int save(OperationLogDO operationLog){
		return operationLogDao.save(operationLog);
	}
	
	@Override
	public int update(OperationLogDO operationLog){
		return operationLogDao.update(operationLog);
	}
	
	@Override
	public int remove(Integer id){
		return operationLogDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return operationLogDao.batchRemove(ids);
	}
	
}
