package com.hww.es.service;

import java.util.List;

  
/**  
 *@Title:  
 *@Description:对原Jedis对象进行封装，获得链接，选择DB关闭链接    
 *@Author:付轩  
 *@Since:2016年10月13日  
 *@Version:1.1.0  
 */
public interface JedisService {
	
	String set(String key, String value);
	
	String get(String key);
	
	/**  
	 * @param key
	 * @return  
	 * @Description:根据key可删除String，List，Hash
	 */
	Long del(String key);
	
	/**  
	 * @param key
	 * @return  
	 * @Description:根据key查看redis缓存中是否存在  
	 */
	Boolean exists(String key);
	
	/**  
	 * @param key
	 * @param seconds 
	 *	以秒为单位
	 * @return  
	 * @Description:  根据key设置过期时间
	 */
	Long expire(String key, int seconds);
	
	/**  
	 * @param key
	 * @return  
	 * @Description:  还多少秒过期
	 */
	Long ttl(String key);//还多久过期
	
	/**  
	 * @param key
	 * @return  
	 * @Description:自增  
	 */
	Long incr(String key);
	
	/**  
	 * @param key
	 * @param field
	 * @param value
	 * @return  
	 * @Description:存储为Hash
	 */
	Long hset(String key, String field, String value);

	String hget(String key, String field);
	
	Long hdel(String key, String... field);
	
	/**  
	 * @param key
	 * @param value
	 * @param seconds
	 * @return  
	 * @Description:  set String类型数据时同时设置过期时间
	 */
	String set(String key, String value, int seconds);

	/**  
	 * @param key
	 * @param start
	 * @param end
	 * @return  
	 * @Description:得到指定index的list，如果start==0&&end==-1则查询所有  
	 */
	List<String> lrange(String key, int start, int end);
	
	/**  
	 * @param key
	 * @param field
	 * @return  
	 * @Description: 从List的右边（也就是尾部）添加元素，lpush是从List的左边（头部）添加元素，若需要请参照rpush写 
	 */
	Long rpush(String key, String... field);
	
}
