package com.dj.mall.cmpt;

/**
 * @Author zhengyk
 * @Date 2021/2/2 11:17
 */
public interface PhoneApi {
    boolean sendSms(String phoneNum,String code) throws Exception;
}
