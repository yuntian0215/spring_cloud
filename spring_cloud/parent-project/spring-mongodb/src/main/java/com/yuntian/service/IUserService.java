package com.yuntian.service;

import com.yuntian.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Package: com.yuntian.service
 * <p>
 * Description： 定义service接口层
 * <p>
 * Author: lvjie
 * <p>
 * Date: Created in 2019/4/16/0016 16:45
 * <p>
 * Version: 0.0.1
 */
public interface IUserService {
    public int save(User user);

    public List<User> findList();

    public List<User> findUserByName(String name);

    public Page<User> findByNameAndAgeRange(String name, String age, Pageable page);
}
