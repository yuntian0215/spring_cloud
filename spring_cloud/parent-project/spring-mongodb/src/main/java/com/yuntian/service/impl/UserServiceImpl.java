package com.yuntian.service.impl;

import com.yuntian.model.User;
import com.yuntian.repository.MongoUtils1;
import com.yuntian.repository.UserRepository;
import com.yuntian.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
    //第一种：继承MongoRepository接口实现操作mongo数据库
    @Resource
    private UserRepository userRepository;
    //第二种：使用SpringBoot自带的MongoTemplate API实现对数据库的CRUD操作
    @Resource
    private MongoTemplate mongoTemplate;
    //把基本的MongoTemplate实现CURD操作做好了封装，直接继承调用就行
    @Resource
    private MongoUtils1 userDao;

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

    @Override
    public Page<User> findByNameAndAgeRange(String name, String age, Pageable page) {
        return userRepository.findByNameAndAgeRange(name, age, page);
    }

    /**
     * -=-=-=-=-=-=-下面是使用mongoTemplate操作mongo-=-=-=-=-=-=-=-=-=-=
     */
    /**
     * <p>添加</p>
     * @param user
     * @return
     * 2019年4月17日下午2:08:58
     * @author lvjie
     */
    public int saveTem(User user){
        mongoTemplate.save(user);
        return 1;
    }
    /**
     * <p>删除</p>
     * @param name
     * @return
     * 2019年4月17日下午2:13:55
     * @author lvjie
     */
    public int deleteTem(String name){
        // 删除userName = zhangsan的数据
        Criteria criteria = Criteria.where("userName").is("zhangsan");
//	   	 删除年龄 > 17
//	    Criteria.where("age").gt(17);
//	             删除年龄 <= 19
//	    Criteria.where("age").lte(19)
        Query query = Query.query(criteria);

        mongoTemplate.remove(query, User.class);
//	   	 一样的效果
//	    mongoTemplate.remove(query, "user");
        return 1;
    }
    /**
     * <p>修改</p>
     * @param user
     * @return
     * 2019年4月17日下午2:19:26
     * @author lvjie
     */
    public int updateTem(User user){
        // 匹配条件name = 张三
        Criteria criteria = Criteria.where("userName").is("zhangsan");
        Query query = Query.query(criteria);
        // 修改年龄 = 20
        Update update = new Update();
        update.set("age", "20");
        //修改匹配到的第一个数据
        mongoTemplate.updateFirst(query, update, User.class);
        //修改所有满足条件的数据
        mongoTemplate.updateMulti(query, update, User.class);
        return 1;
    }
    /**
     * <p>查询</p>
     * @return
     * 2019年4月17日下午2:21:57
     * @author lvjie
     */
    public List<User> queryTem(){
        // 匹配条件userName = zhangsan
        Criteria criteria = Criteria.where("userName").is("zhangsan");
        Query query = Query.query(criteria);
        List<User> list = mongoTemplate.find(query, User.class);

        return list;
    }
}
