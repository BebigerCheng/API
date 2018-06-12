package com.smt.pc.Interface.dubbo;

/**
 * @Package DubboTestService
 * @Copyright: Copyright (c) 2016
 * Author lijikai
 * @date 2017/5/22 10:24
 * version V1.0.0
 */
public interface DubboTestService {
    void say(String words);

    String info(String content);
}
