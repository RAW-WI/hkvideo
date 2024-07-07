package com.softeem.hkvideo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.softeem.hkvideo.dto.R;
import com.softeem.hkvideo.entity.Category;
import com.softeem.hkvideo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController extends BaseController{

    @Autowired
    private CategoryService categoryService;


    /**
     * 频道添加
     */
    @RequestMapping("/add")
    public R add(Category category) {
        //根据频道名称查询频道是否存在
        boolean b = categoryService.exists(Wrappers.lambdaQuery(Category.class).eq(Category::getName, category.getName()));
        if(b){
            return R.fail().setMsg("频道已存在");
        }
        return categoryService.save(category) ? R.ok().setMsg("添加成功") : R.fail().setMsg("添加失败");
    }

    /**
     * 分页查询频道
     */
    @RequestMapping("/list")
    public R list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        List<Category> list = categoryService.list(new Page<Category>(page, limit));
        long count = categoryService.count();
        return R.ok().setData(list).setCount(count);
    }
}
