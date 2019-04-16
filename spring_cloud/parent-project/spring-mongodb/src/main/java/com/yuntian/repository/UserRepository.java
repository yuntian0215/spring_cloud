package com.yuntian.repository;

import com.yuntian.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Package: com.yuntian.repository
 * <p>
 * Description： 自定义数据操作层
 * <p>
 * Author: lvjie
 * <p>
 * Date: Created in 2019/4/16/0016 16:43
 * <p>
 * Version: 0.0.1
 */
public interface UserRepository extends MongoRepository<User, Integer> {
    /**
     * Like（模糊查询）
     * {"username" : name} ( name as regex)
     * */
    List<User> findByUserNameLike(String userName);

    /**
     * Like（模糊查询）
     * {"username" : name}
     * */
    List<User> findByUserName(String userName);

    /**
     * GreaterThan(大于)
     * {"age" : {"$gt" : age}}
     * */
    List<User> findByAgeGreaterThan(int age);
    /**
     * LessThan（小于）
     * {"age" : {"$lt" : age}}
     * */
    List<User> findByAgeLessThan(int age);
    /**
     * Between（在...之间）
     * {{"age" : {"$gt" : from, "$lt" : to}}
     * */
    List<User> findByAgeBetween(int from, int to);

    /**
     * IsNotNull, NotNull（是否非空）
     *  {"username" : {"$ne" : null}}
     * */
    List<User> findByUserNameNotNull();

    /**
     * IsNull, Null（是否为空）
     *   {"username" : null}
     * */
    List<User> findByUserNameNull();


    /**
     * Not（不包含）
     *    {"username" : {"$ne" : name}}
     * */
    List<User> findByUserNameNot(String name);


    @Query("{\"username\":{\"$regex\":?0}, \"age\": ?1}")
    Page<User> findByNameAndAgeRange(String name, int age, Pageable page);

    @Query(value="{\"username\":{\"$regex\":?0},\"age\":{\"$gte\":?1,\"$lte\": ?2}}")
    Page<User> findByNameAndAgeRange2(String name,int ageFrom,int ageTo,Pageable page);

    @Query(value="{\"username\":{\"$regex\":?0},\"age\":{\"$gte\":?1,\"$lte\": ?2}}",fields="{\"username\" : 1, \"age\" : 1}")
    Page<User> findByNameAndAgeRange3(String name,int ageFrom,int ageTo,Pageable page);
}
