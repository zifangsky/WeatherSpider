package cn.zifangsky.test.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试连接zookeeper
 *
 * @author zifangsky
 * @date 2018/6/12
 * @since 1.0.0
 */
public class TestApacheCurator {
    //会话超时时间
    private final int SESSION_TIMEOUT = 30 * 1000;

    //连接超时时间
    private final int CONNECTION_TIMEOUT = 3 * 1000;

    //ZooKeeper服务地址
    private static final String SERVER = "192.168.197.130:2100,192.168.197.130:2101,192.168.197.130:2102";

    //创建连接实例
    private CuratorFramework client = null;

    /**
     * baseSleepTimeMs：初始的重试等待时间
     * maxRetries：最多重试次数
     */
    RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

    @Before
    public void init(){
        //创建 CuratorFrameworkImpl实例
        client = CuratorFrameworkFactory.newClient(SERVER, SESSION_TIMEOUT, CONNECTION_TIMEOUT, retryPolicy);

        //启动
        client.start();
    }

    /**
     * 测试创建节点
     * @throws Exception
     */
    @Test
    public void testCreate() throws Exception{
        //创建永久节点
        client.create().forPath("/curator","/curator data".getBytes());

        //创建永久有序节点
        client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/curator_sequential","/curator_sequential data".getBytes());

        //创建临时节点
        client.create().withMode(CreateMode.EPHEMERAL)
                .forPath("/curator/ephemeral","/curator/ephemeral data".getBytes());

        //创建临时有序节点
        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                .forPath("/curator/ephemeral_path1","/curator/ephemeral_path1 data".getBytes());

        client.create().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                .forPath("/curator/ephemeral_path2","/curator/ephemeral_path2 data".getBytes());
    }

    /**
     * 测试获取和设置节点数据
     * @throws Exception
     */
    @Test
    public void testGetAndSet() throws Exception{
        //获取某个节点的所有子节点
        System.out.println(client.getChildren().forPath("/"));

        //获取某个节点数据
        System.out.println(new String(client.getData().forPath("/curator")));

        //设置某个节点数据
        client.setData().forPath("/curator","/curator modified data".getBytes());
    }

}
