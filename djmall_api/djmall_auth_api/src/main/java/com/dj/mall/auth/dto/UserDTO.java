package com.dj.mall.auth.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private Integer id;

    private String userName;
}
