package com.smt.pc.Interface.mapper;


import java.util.List;
import java.util.Map;

import com.smt.pc.Interface.domain.Sms;
import com.smt.pc.Interface.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表
 * @author lijikai
 * @email 
 * @date 2018-03-23 13:50:12
 */
@Mapper
public interface UserDao {

	UserDO get(Integer id);
	
	List<UserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	UserDO findByUserName(String userName);

	UserDO findPhoneNumber(String phoneNumber);

	Sms findSmsCodeByPhoneNumber(String phoneNumber);

	void addSmsByUser(@Param("phoneNumber") String phoneNumber, @Param("userId")int userId);
}
