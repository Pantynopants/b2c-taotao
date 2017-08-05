import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * @author shen
 * @desc
 * @date 2016年11月26日
 */
public class JedisTest {
	
	@Test
	public void jedisSingle() {
		Jedis jedis = new Jedis("127.0.0.1", 6379);//连接
		jedis.set("one", "vvv");//设置键值
		String str = jedis.get("one");
		System.out.println(str);
		jedis.close();//关闭连接3
	}
	
	
	//连接池
	@Test
	public void jedisPool() {
		
		JedisPool pool = new JedisPool("127.0.0.1", 6379);//创建池
		
		Jedis jedis = pool.getResource();//从池中获取Jedis对象
		String str = jedis.get("one");
		System.out.println(str);
		jedis.close();
		
		pool.close();
	}
	
	/**
	 * 集群版测试    	 没组redis集群，没测试
	 * <p>Title: testJedisCluster</p>
	 * <p>Description: </p>
	 */
	@Test
	public void testJedisCluster() {
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.153", 7001));
		nodes.add(new HostAndPort("192.168.25.153", 7002));
		nodes.add(new HostAndPort("192.168.25.153", 7003));
		nodes.add(new HostAndPort("192.168.25.153", 7004));
		nodes.add(new HostAndPort("192.168.25.153", 7005));
		nodes.add(new HostAndPort("192.168.25.153", 7006));
		
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("key1", "1000");
		
		String string = cluster.get("key1");
		System.out.println(string);
		cluster.close();
	}
	
	/**
	 * 单机版测试
	 * <p>Title: testSpringJedisSingle</p>
	 * <p>Description: </p>
	 */
	@Test
	public void testSpringJedisSingle() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");//容器
		JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis = pool.getResource();
		String string = jedis.get("oone");
		System.out.println(string);
		//jedis.set("oone", "shen");
		jedis.close();
		pool.close();
	}
	
}
