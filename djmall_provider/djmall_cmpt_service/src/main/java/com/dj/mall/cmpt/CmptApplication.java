package com.dj.mall.cmpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CmptApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmptApplication.class, args);
//        String text = "尊敬的%s，您的密码已被管理员%s，于%s重置为%s。为了您的账户安全，请及时修改。";
//        String newStr = String.format(text, "张三", "超管", "2020-01-28 11:35:20", "123456");
//        System.out.println(newStr);
    }

}
