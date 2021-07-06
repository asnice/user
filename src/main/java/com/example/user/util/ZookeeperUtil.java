package com.example.user.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author shuixianbin
 * @date 2021/06/15
 */
@Component
public class ZookeeperUtil {

    private List<LeaderLatch> leaderLatchList = new ArrayList<>();
    private LeaderLatch leaderLatch;
    LeaderLatchListener listener = new LeaderLatchListener() {
        @Override
        public void isLeader() {
            System.out.println("i am master");
        }

        @Override
        public void notLeader() {
            System.out.println("i am salver");
        }
    };
    @Autowired
    private CuratorFramework curatorFramework;

    @Value("${server.port}")
    private String port;

    @PostConstruct
    public void leaderLatch() throws Exception {

        String lockPath = "/leader";
        leaderLatch = new LeaderLatch(curatorFramework,lockPath);
        leaderLatch.addListener(listener);
        leaderLatch.start();
        //leaderLatch.await();
    }

    public boolean isMaster() {
        return leaderLatch == null ? false : leaderLatch.hasLeadership();
    }
    public void testCheckLeader() throws Exception{
        LeaderLatch currentLeader = null;

        for(LeaderLatch tmp : leaderLatchList){
            if(tmp.hasLeadership()){ //判断是否是leader
                currentLeader = tmp;
                break;
            }
        }

        System.out.println("当前leader是： " + currentLeader.getId());
//        System.out.println("当前leader是： " + leaderLatchList.get(0).getLeader().getId());

        /**
         * 从List中移除当前主节点，并从剩下的节点中继续选举leader
         */
        currentLeader.close(); //关闭当前主节点
        leaderLatchList.remove(currentLeader); //从List中移除
        TimeUnit.SECONDS.sleep(5); //等待再次选举

        //再次获取当前leader
        for(LeaderLatch tmp : leaderLatchList){
            if(tmp.hasLeadership()){
                currentLeader = tmp;
                break;
            }
        }

        System.out.println("新leader是： " + currentLeader.getId());
        currentLeader.close(); //关闭当前主节点
        leaderLatchList.remove(currentLeader); //从List中移除

        LeaderLatch firstNode = leaderLatchList.get(0); //获取此时第一个节点
        System.out.println("删除leader后，当前第一个节点： " + firstNode.getId());

        firstNode.await(10, TimeUnit.SECONDS); //阻塞并尝试获取领导权，可能失败

        //再次获取当前leader
        for(LeaderLatch tmp : leaderLatchList){
            if(tmp.hasLeadership()){
                currentLeader = tmp;
                break;
            }
        }

        System.out.println("最终实际leader是： " + currentLeader.getId());

    }
}
