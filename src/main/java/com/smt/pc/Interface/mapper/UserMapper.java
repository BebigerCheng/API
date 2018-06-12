package com.smt.pc.Interface.mapper;

import com.smt.pc.Interface.domain.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Package: UserMapper.java
 * @Description: InnoDB free: 4096 kB
 * @Copyright: Copyright (c) 2017
 * All right reserved.
 * Author jon lv
 * @date 2017/05/12 15:24
 * version v1.0.0
 */
//@Mapper
@Repository
public interface UserMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author jon lv
     * @Date 2017/05/12 15:24
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author jon lv
     * @Date 2017/05/12 15:24
     * @param record
     * @return int
     * @throws []
     */
    int insert(User record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author jon lv
     * @Date 2017/05/12 15:24
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(User record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author jon lv
     * @Date 2017/05/12 15:24
     * @param id
     * @return User
     * @throws []
     */
    User selectByPrimaryKey(Integer id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author jon lv
     * @Date 2017/05/12 15:24
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author jon lv
     * @Date 2017/05/12 15:24
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(User record);

//    List<User> queryPage(Page<User> page);

    /**
     * @Description:根据用户名查找记录
     * @Title findByUserName
     * @Author BeBiger
     * @Date 2018/03/23 13:46
     * */
    User findByUserName(String userName);
}