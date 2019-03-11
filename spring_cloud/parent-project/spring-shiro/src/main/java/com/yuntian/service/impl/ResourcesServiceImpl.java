package com.yuntian.service.impl;

import com.yuntian.Resources;
import com.yuntian.mapper.ResourcesMapper;
import com.yuntian.service.IResourcesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>内容</p>
 * 2019/3/8/0008 16:39
 *
 * @author lvjie
 */
@Service
public class ResourcesServiceImpl implements IResourcesService {
    @Resource
    private ResourcesMapper resourcesMapper;

    @Override
    public List<Resources> findResourcesByUserId(Integer userId) {
        return resourcesMapper.selectResourcesByUserId(userId);
    }
}
