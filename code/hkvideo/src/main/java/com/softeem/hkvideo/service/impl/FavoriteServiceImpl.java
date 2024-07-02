package com.softeem.hkvideo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.hkvideo.entity.Favorite;
import com.softeem.hkvideo.service.FavoriteService;
import com.softeem.hkvideo.mapper.FavoriteMapper;
import org.springframework.stereotype.Service;

/**
* @author mrchai
* @description 针对表【favorite(收藏表)】的数据库操作Service实现
* @createDate 2024-07-02 10:29:38
*/
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite>
    implements FavoriteService{

}




