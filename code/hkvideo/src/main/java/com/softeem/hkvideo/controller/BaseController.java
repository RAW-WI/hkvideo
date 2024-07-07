package com.softeem.hkvideo.controller;

import com.softeem.hkvideo.dto.R;
import com.softeem.hkvideo.utils.QiniuOssUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class BaseController {

    /**
     * 文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    public R upload(MultipartFile file) {
        try {
            //获取文件输入流
            InputStream is = file.getInputStream();
            //随机生成一个uuid作为文件名（key）
            String key = UUID.randomUUID().toString();
            //创建七牛OSS工具对象
            QiniuOssUtils utils = new QiniuOssUtils();
            //执行上传返回文件在服务端的存储地址
            String url = utils.upload(is, key);
            return R.ok().setData(url).setMsg("上传成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.fail().setMsg("上传失败");
        }
    }

}
