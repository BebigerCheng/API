package com.smt.pc.Interface.service;

import com.smt.pc.Interface.domain.User;
import com.smt.pc.Interface.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * 用户表
 * 
 * @author lijikai
 * @email 
 * @date 2018-03-23 13:50:12
 */
public interface UserService {
	
	UserDO get(Integer id);
	
	List<UserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	int findUserName(String userName);

	int findPhoneNumber(String phoneNumber);

	UserDO login(UserDO user);

	int checkCode(UserDO user,String code);
}
