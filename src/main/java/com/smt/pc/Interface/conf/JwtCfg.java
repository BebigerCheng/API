package com.smt.pc.Interface.conf;

import com.smt.pc.Interface.interceptors.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * JwtCfg
 *
 * @author LIJIKAI
 * @date 18 /4/7
 */
//@Configuration
public class JwtCfg {

    /**
     * Jwt filter filter registration bean.
     *
     * @return the filter registration bean
     */
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        //用户卖方需求列表
        registrationBean.addUrlPatterns("/system/bill/userBill");

        return registrationBean;
    }
}
