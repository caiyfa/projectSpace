package psn.cyf.test;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import psn.cyf.base.vo.BaseVO;
import psn.cyf.utils.sax.xml.util.vo.Status2x2VO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestZookeeper {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
        System.out.println(Status2x2VO.class.asSubclass(BaseVO.class));
        System.out.println(Status2x2VO.class.newInstance() instanceof BaseVO);
        Map map=new HashMap(5);
        System.out.println(map.size());

    }
}
