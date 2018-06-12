package com.smt.pc.Interface.utils;

import java.util.Locale;

/**
 * 国际化信息读取共通
 * Created by LIJIKAI on 2015/5/11.
 */
public class ResourceUtil {

    /**
     * 根据Key获取资源信息
     *
     * @param key the key
     * @return resource resource
     */
    public static String getResource(String key) {
        return SpringContextUtil.getApplicationContext().getMessage(key, null, Locale.getDefault());
    }

    /**
     * 根据Key获取资源信息
     *
     * @param key  the key
     * @param lang the lang
     * @return resource resource
     */
    public static String getResource(String key, String lang) {
        return SpringContextUtil.getApplicationContext().getMessage(key, null,
                new Locale(lang));
    }

    /**
     * 根据Key获取资源信息
     *
     * @param key  the key
     * @param args the args
     * @return resource by args
     */
    public static String getResourceByArgs(String key, Object... args) {
        return SpringContextUtil.getApplicationContext().getMessage(key, args, Locale.getDefault());
    }




}
