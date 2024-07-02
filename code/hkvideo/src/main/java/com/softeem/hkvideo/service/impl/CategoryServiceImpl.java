package com.softeem.hkvideo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.hkvideo.entity.Category;
import com.softeem.hkvideo.service.CategoryService;
import com.softeem.hkvideo.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author mrchai
* @description 针对表【category(频道分类表)】的数据库操作Service实现
* @createDate 2024-07-02 10:29:37
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




