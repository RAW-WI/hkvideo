package com.softeem.hkvideo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.hkvideo.entity.Video;
import com.softeem.hkvideo.service.VideoService;
import com.softeem.hkvideo.mapper.VideoMapper;
import org.springframework.stereotype.Service;

/**
* @author mrchai
* @description 针对表【video(视频表)】的数据库操作Service实现
* @createDate 2024-07-02 10:29:38
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService{

}




