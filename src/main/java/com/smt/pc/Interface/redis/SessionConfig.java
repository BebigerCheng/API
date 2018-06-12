package com.smt.pc.Interface.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author leroy
 * @Package SessionConfig
 * @Copyright: Copyright (c) 2016
 * Author lijikai
 * @date 2017/5/18 14:14
 * version V1.0.0
 */
@Configuration
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30) 30天
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class SessionConfig {
    // maxInactiveIntervalInSeconds: 设置Session失效时间，使用Redis Session之后，原Boot的server.session.timeout属性不再生效
}
