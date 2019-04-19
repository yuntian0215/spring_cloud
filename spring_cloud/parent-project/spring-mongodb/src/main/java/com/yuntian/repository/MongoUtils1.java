package com.yuntian.repository;

import com.yuntian.model.User;
import org.springframework.stereotype.Repository;

/**
 * <p>数据持久化处理</p>
 * 2019/4/19/0019 16:34
 *
 * @author lvjie
 */
@Repository
public class MongoUtils1 extends MongoUtils1<User> {
    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
