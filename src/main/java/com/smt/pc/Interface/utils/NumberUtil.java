package com.smt.pc.Interface.utils;

/**
 * NumberUtil
 * 数字工具栏
 * @author LIJIKAI
 * @date 18/3/27
 */
public class NumberUtil {

    /**
     * Random int.
     * 生成6位随机数
     * @return the int
     */
    public static int randomNumber(){
        return (int)((Math.random()*9+1)*100000);


    }



}
