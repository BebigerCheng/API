package com.smt.pc.Interface.mapper;

import com.smt.pc.Interface.domain.Sms;
import org.apache.ibatis.annotations.Mapper;

/**
 * 信息表
 * @author chengliang
 * @email
 * @date 2018-03-27
 */
@Mapper
public interface SmsDao {

   void save(Sms sms);
}
