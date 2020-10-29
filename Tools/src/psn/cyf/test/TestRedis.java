package psn.cyf.test;

import redis.clients.jedis.Jedis;

import java.util.List;

public class TestRedis {
    public static void main(String[] args) {
        Jedis jedis=new Jedis("192.168.135.200");
        System.out.println(jedis.ping());
        //设置 redis 字符串数据
         jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
//       jedis.lpush("list","list1","list2","list3","list4");
        List<String> list = jedis.lrange("list", 0 ,5);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }

    }
}
