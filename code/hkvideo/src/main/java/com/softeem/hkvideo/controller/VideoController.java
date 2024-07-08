package com.softeem.hkvideo.controller;


import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiniu.common.QiniuException;
import com.softeem.hkvideo.dto.R;
import com.softeem.hkvideo.entity.*;
import com.softeem.hkvideo.service.AdminService;
import com.softeem.hkvideo.service.CategoryService;
import com.softeem.hkvideo.service.UserService;
import com.softeem.hkvideo.service.VideoService;
import com.softeem.hkvideo.utils.AliOssUtils;
import com.softeem.hkvideo.utils.QiniuOssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/video")
@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AdminService adminService;

    /**
     * 分页查询视频列表
     *
     * @param page  当前页码，默认为1
     * @param limit 每页显示的数量，默认为5
     * @return 返回包含视频列表和总条数的响应对象
     * <p>
     * 该方法通过调用videoService的list和count方法，获取指定页码和每页数量的视频列表，
     * 以及总视频条数，然后将这些数据包装在响应对象R中返回。
     */
    @RequestMapping("/list")
    public R list(@RequestParam(defaultValue = "1") Integer page,
                  @RequestParam(defaultValue = "5") Integer limit,
                  @RequestParam(required = false, defaultValue = "") String keyword) {
        //创建查询条件
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        if (!keyword.isEmpty()) {
            queryWrapper.lambda().like(Video::getTitle, keyword);
        }
        // 根据当前页码和每页数量查询视频列表
        //执行分页查询
        List<Video> list = videoService.list(new Page<>(page, limit),queryWrapper);
        //循环遍历所有视频对象
        list.forEach(v -> {
            //获取视频的作者id
            Integer uid = v.getUid();
            //获取视频的频道id
            Integer cid = v.getCid();
            //获取审核员id
            Integer aid = v.getAid();
            //根据用户id和频道id分别查询用户和频道
            //设置用户和频道到视频对象中
            if (uid != null) {
                User user = userService.getById(uid);
                v.setUser(user);
            }
            if (cid != null) {
                Category category = categoryService.getById(cid);
                v.setCategory(category);
            }
            if (aid != null) {
                Admin admin = adminService.getById(aid);
                v.setAdmin(admin);
            }
        });
        // 查询视频总条数
        long count = videoService.count();
        // 构建响应对象，设置查询结果列表和总条数
        return R.ok().setData(list).setCount(count);
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/uploadImage")
    public R uploadImage(MultipartFile file) {
        try {
            //获取文件输入流
            if (file == null) {
                return R.fail().setMsg("文件为空");
            }
            InputStream is = file.getInputStream();
            //创建AliOSS工具对象
            String path = "image/";
            AliOssUtils utils = new AliOssUtils();
            //执行上传返回文件在服务端的存储地址
            String url = utils.uploadimage(is, file.getOriginalFilename(), path);
            return R.ok().setData(url).setMsg("上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail().setMsg("上传失败");
        }
    }

    @RequestMapping("/uploadVideo")
    public R uploadVideo(MultipartFile file) {
        try {
            //获取文件输入流
            if (file == null) {
                return R.fail().setMsg("文件为空");
            }
            InputStream is = file.getInputStream();
//            //创建七牛OSS工具对象
//            QiniuOssUtils utils = new QiniuOssUtils();
            //创建AliOSS工具对象
            AliOssUtils utils = new AliOssUtils();
            String path = "video/";
            //执行上传返回文件在服务端的存储地址
            String url = utils.uploadimage(is, file.getOriginalFilename(), path);
            return R.ok().setData(url).setMsg("上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail().setMsg("上传失败");
        }
    }

    /**
     * 查询所有频道
     *
     * @return
     */
    @RequestMapping("/categories")
    public R listCategories() {
        List<Category> list = categoryService.list();
        return R.ok().setData(list);
    }

    @RequestMapping("/update")
    public R listCategories(@RequestBody Map<String, Object> requestData) {
        Map<String, Object> videoMap = (Map<String, Object>) requestData.get("video");
        Map<String, Object> categoryMap = (Map<String, Object>) requestData.get("category");
        try {
            Video video = new Video();
            video.setId((Integer) videoMap.get("id"));
            video.setSummary((String) videoMap.get("summary"));
            video.setTitle((String) videoMap.get("title"));
            video.setTags((String) videoMap.get("tags"));
            videoService.updateById(video);
            Category category = new Category();
            category.setId((Integer) categoryMap.get("id"));
            category.setName((String) categoryMap.get("name"));
            categoryService.updateById(category);
            List<Category> list = categoryService.list();
            return R.ok().setCode(0).setMsg("更新成功");
        } catch (Exception e) {
            return R.fail().setMsg("更新失败").setDetails(e.toString());
        }

    }


    //实现删除 限制post
    @RequestMapping("/delete")
    public R delete(Integer id) {
        try {
            boolean b = videoService.removeById(id);
            return b ? R.ok().setMsg("删除成功").setCode(0) : R.fail().setMsg("删除失败").setCode(1);
        } catch (Exception e) {
            return R.fail().setMsg("此数据无法实现删除,控制台显示详细信息").setDetails(e.toString());
        }
    }

    /**
     * 视频添加
     *
     * @param video
     * @return
     */
    @RequestMapping("/add")
    public R add(Video video) {
        boolean b = videoService.save(video);
        return b ? R.ok().setMsg("添加成功") : R.fail().setMsg("添加失败");
    }

}
