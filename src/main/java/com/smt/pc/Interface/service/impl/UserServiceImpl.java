package com.smt.pc.Interface.service.impl;

import com.smt.pc.Interface.domain.Sms;
import com.smt.pc.Interface.domain.UserDO;
import com.smt.pc.Interface.mapper.UserDao;
import com.smt.pc.Interface.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * The type User service.
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDO get(Integer id){
		return userDao.get(id);
	}
	
	@Override
	public List<UserDO> list(Map<String, Object> map){
		return userDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userDao.count(map);
	}
	
	@Override
	public int save(UserDO user){

		int userId = userDao.save(user);
		//将userId插入Sms表中
		userDao.addSmsByUser(user.getPhoneNumber(),userId);
		return userId;
	}
	
	@Override
	public int update(UserDO user){
		return userDao.update(user);
	}
	
	@Override
	public int remove(Integer id){
		return userDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return userDao.batchRemove(ids);
	}

	@Override
	public int findUserName(String userName) {
		UserDO userDO = userDao.findByUserName(userName);
		if (userDO == null){
			return 0;
		}
		return 1;
	}

	@Override
	public int findPhoneNumber(String phoneNumber) {
		UserDO userDO = userDao.findPhoneNumber(phoneNumber);
		if (userDO == null){
			return 0;
		}
		return 1;
	}

	@Override
	public UserDO login(UserDO user) {
		//用户登录，null- 用户不存在  password error- 密码错误  userDo- 登录成功
		UserDO userDo =userDao.findPhoneNumber(user.getPhoneNumber());
		if (userDo == null){
			return null;
		}
		if (userDo.getPassWord().equals(user.getPassWord())){
			return userDo;
		}
		userDo.setPassWord("error");
		return userDo;
	}

	@Override
	public int checkCode(UserDO user, String code) {
		String phoneNumber = user.getPhoneNumber();
		//从数据库中查出验证码 1- 正确   2- 不正确 3- 失效
	 	Sms sms = userDao.findSmsCodeByPhoneNumber(phoneNumber);
		if (sms != null){
	 		if (sms.getCheckCode().equals(code)){
	 			// 判断验证码是否有效
				Date createTime = sms.getCreateTime();


				Long diff = System.currentTimeMillis() - createTime.getTime();
				if (diff > 300000){
					return 3;
				}
	 			return 1;
			}

		}
		return 2;
	}


}
