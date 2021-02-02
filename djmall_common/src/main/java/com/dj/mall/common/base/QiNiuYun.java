package com.dj.mall.common.base;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * @Author zhengyk
 * @Date 2021/2/1 17:22
 */
public class QiNiuYun {

    /**
     * AK
     */
    private static final String AK = "xEDiJV_tAPS4BGS21HhoIptCZt1dghH8ko_tyngG";

    /**
     * SK
     */
    private static final String SK = "M8z6j1rDSh0hUdzyW97tLN5Ea2MfDZTvW_5jA4ZL";

    /**
     * 外链域名
     */
    public static final String URL = "qnue446o0.hn-bkt.clouddn.com";

    /**
     * 存储空间名称
     */
    private static final String BUCKET = "paceo3forzyk";

    /**
     * 密钥配置
     */
    private static  Auth auth = Auth.create(AK, SK);

    /**
     * 生成token
     */
    private static String token = auth.uploadToken(BUCKET);

    /**
     *创建地区
     */
    private static  Configuration cnf = new Configuration(Region.autoRegion());

    /**
     * 七牛封装的上传工厂
     */
    private static  UploadManager uploadManager = new UploadManager(cnf);

    /**
     * byte[] 上传图片
     */
    public static void uploadFile(byte[] img, String key){
        try {
            uploadManager.put(img, key, token);
            System.out.println(" upload success");
        } catch (QiniuException e) {
            System.out.println(" upload fail");
            e.printStackTrace();
        }
    }

    /**
     * 删除图片
     */
    public static void deleteFile(String key){
        try {
            BucketManager bucketManager = new BucketManager(auth, cnf);
            bucketManager.delete(BUCKET, key);
            System.out.println("del success");
        } catch (QiniuException e) {
            e.printStackTrace();
            System.out.println("del fail");
        }


    }




}
