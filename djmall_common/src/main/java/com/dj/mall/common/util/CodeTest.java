package com.dj.mall.common.util;

import java.util.Random;
/**
 * 
 * @author qby
 * 2020年12月10日下午9:23:03
 */
public class CodeTest {
	//num为想要获取验证码的个数
	public static String getCode(Integer num) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder sb = new StringBuilder(num);
		for (int i = 0; i < num; i++) {
			char ch = str.charAt(new Random().nextInt(str.length()));
			sb.append(ch);
		}
		return sb.toString();
	}
}
