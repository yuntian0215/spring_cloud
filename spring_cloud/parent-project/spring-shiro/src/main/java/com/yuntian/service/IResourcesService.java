package com.yuntian.service;

import com.yuntian.Resources;

import java.util.List;

/**
 * Package: com.yuntian.service
 * <p>
 * Description： TODO
 * <p>
 * Author: lvjie
 * <p>
 * Date: Created in 2019/3/8/0008 16:38
 * <p>
 * Version: 0.0.1
 */
public interface IResourcesService {
    public List<Resources> findResourcesByUserId(Integer userId);
}
