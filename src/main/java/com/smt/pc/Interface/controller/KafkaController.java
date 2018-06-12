package com.smt.pc.Interface.controller;

import com.smt.pc.Interface.kafka.MsgProducer;
import com.smt.pc.Interface.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author leroy
 * @Package KafkaController
 * @Description: KafkaController
 * @Copyright: Copyright (c) 2016
 * Author lijikai
 * @date 2017/5/19 10:32
 * version V1.0.0
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private MsgProducer msgProducer;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public ResultDO<String> get(@RequestParam("content") String content){
        ResultDO<String> resultDO = new ResultDO<>();

        msgProducer.send(content);

        resultDO.setSuccess(true);
        resultDO.setData(content);
        return resultDO;
    }
}
