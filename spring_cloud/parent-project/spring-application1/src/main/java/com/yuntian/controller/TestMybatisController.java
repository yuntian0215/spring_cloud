package com.yuntian.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntian.User;
import com.yuntian.service.ITestMybatisService;
import com.yuntian.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>测试集成mybatis</p>
 * 2019/3/8/0008 10:41
 *
 * @author lvjie
 */
@RestController
@RequestMapping("/tMybatis")
public class TestMybatisController {
    @Resource
    private ITestMybatisService mybatisService;
    /**
    *@Description 分页查询
    *@Param [pageNum 当前页数, pageSize 每页显示条数]
    *@Return com.github.pagehelper.PageInfo
    *@Author lvjie
    *@Date 2019/3/8/0008 10:57
    */
    @RequestMapping("/findPageList")
    public R findPageList(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = mybatisService.selectList();
        PageInfo pageInfo = new PageInfo(list);
        return R.ok().put("data",pageInfo);
    }
}
