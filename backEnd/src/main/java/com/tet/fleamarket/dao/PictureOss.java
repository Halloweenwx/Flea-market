package com.tet.fleamarket.dao;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.tet.fleamarket.dao.PictureDao;
import com.tet.fleamarket.entity.Picture;
import javassist.bytecode.ByteArray;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.*;

/**
 * @author Hou Weiying
 * @date 2019-12-03 12:47
 */
@Repository
public class PictureOss {

    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";

    private static String accessKeyId = "LTAI4Fun7nZbfbZsfVs5pW6w";

    private static String accessKeySecret = "ODjuGpxUGeQdrrDrE3vb3j44aWgk0U";

    private static String bucketName = "tet-flea";

    private static OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

    public Picture findByPid(String pid) throws IOException {
        OSSObject ossObject = ossClient.getObject(bucketName, pid);
        InputStream content = ossObject.getObjectContent();
        Picture pictureToFind = new Picture();

        byte[] byt = new byte[content.available()];
        content.read(byt);

        pictureToFind.setFileBin(byt);

        return pictureToFind;
    }

    public PutObjectResult save(Picture picture) {
        String objectName = picture.getPid();
        return ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(picture.getFileBin()));
    }
}
