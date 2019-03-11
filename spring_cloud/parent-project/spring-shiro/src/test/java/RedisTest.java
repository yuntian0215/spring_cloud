import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * <p>内容</p>
 * 2019/3/11/0011 9:02
 *
 * @author lvjie
 */
public class RedisTest {
    @Test
    public void testRedis(){
        Jedis jedis = new Jedis("192.168.227.5");
        jedis.auth("123456");
        System.out.println("Redis连接成功！");
        System.out.println("测试连接："+jedis.ping());
    }
}
