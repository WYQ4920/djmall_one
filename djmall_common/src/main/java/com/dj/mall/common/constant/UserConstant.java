package com.dj.mall.common.constant;

/**
 * @Author WYQ
 * @Date 2021/1/16 16:39
 */
public interface UserConstant {

    String USER_SESSION = "user";

    int SEX_NAM = 1;

    int SEX_WONAM = 2;

    String RESOURCE_TYPE = "1";

    String RESOURCE_TYPE_BTN = "2";

    /**
     * 商户的角色id
     */
    Integer MERCHANT_ROLE_ID = 3;


    /**
     * general
     * 普通用户的角色id
     */
    Integer GENERAL_ROLE_ID = 2;

    /**
     * 用户正常状态
     */
    String USER_STATUS_ACTIVE = "NORMAL";

    /**
     * 用户未激活状态
     */
    String USER_STATUS_NOT_ACTIVE = "UNACTIVATED";

    /**
     *
     */
    String SESSION_VERIFY_CODE = "SESSION_VERIFY_CODE";

    /**
     * 用户性别
     */
    String USER_SEX_PARENT_CODE = "USER_SEX";

}
