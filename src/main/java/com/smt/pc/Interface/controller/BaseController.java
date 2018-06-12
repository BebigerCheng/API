package com.smt.pc.Interface.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.apache.commons.lang3.exception.ExceptionUtils.getThrowables;

/**
 * BaseController
 *
 * @author LIJIKAI
 * @date 18/3/27
 */
public class BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * ThreadLocal确保高并发下每个请求的request，response都是独立的
     */
    private static ThreadLocal<ServletRequest> currentRequest = new ThreadLocal<>();
    private static ThreadLocal<ServletResponse> currentResponse = new ThreadLocal<>();
//    private static ThreadLocal<CommonParams> currentParams = new ThreadLocal<>();


    /**
     * 线程安全初始化reque，respose对象
     *
     * @param request
     * @param response
     * @date 2015年11月24日
     * @author 漂泊者及其影子
     */
    @ModelAttribute
    public void initReqAndRep(HttpServletRequest request, HttpServletResponse response) {
        currentRequest.set(request);
        currentResponse.set(response);
    }



    /**
     * 获取app传递的params参数
     *
     * @return
     * @date 2015年11月24日
     * @author 漂泊者及其影子
     */
//    public CommonParams getParams() {
//        return currentParams.get();
//    }

    /**
     * 线程安全
     *
     * @return
     * @date 2015年11月24日
     * @author 漂泊者及其影子
     */
    public HttpServletRequest request() {
        return (HttpServletRequest) currentRequest.get();
    }

    public static String getFullStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        Throwable[] ts = getThrowables(throwable);
        for (Throwable t : ts) {
            t.printStackTrace(pw);

        }
        return sw.getBuffer().toString();
    }
}
