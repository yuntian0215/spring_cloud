package com.yuntian.service.impl;

import com.yuntian.model.User;
import com.yuntian.repository.UserRepository;
import com.yuntian.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>定义业务层</p>
 * 2019/4/16/0016 16:45
 *
 * @author lvjie
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserRepository userRepository;

    @Override
    public int save(User user) {
        userRepository.save(user);
        return 1;
    }
    @Override
    public List<User> findList(){
        return userRepository.findAll();
    }
    @Override
    public List<User> findUserByName(String name){
        return userRepository.findByUserNameLike(name);
    }
}
