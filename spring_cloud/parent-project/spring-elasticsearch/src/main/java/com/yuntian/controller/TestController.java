package com.yuntian.controller;

import com.yuntian.esutils.EsClient;
import com.yuntian.esutils.EsDomian;
import com.yuntian.esutils.PageBean;
import com.yuntian.service.ITestService;
import com.yuntian.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>测试elasticsearch查询</p>
 * 2019/3/14/0014 14:57
 *
 * @author lvjie
 */
@RestController
@RequestMapping("/es")
public class TestController {
    @Resource
    private ITestService testService;
    @RequestMapping("/queryEs")
    public R queryEs(String kw,Integer pageNum,Integer pageSize){
        PageBean<EsDomian> page = testService.search(kw,pageNum,pageSize);
        return R.ok().put("data",page);
    }
}
